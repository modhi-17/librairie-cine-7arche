package com.demos.librairiecine7arche.service;

import com.demos.librairiecine7arche.exception.StockException;
import com.demos.librairiecine7arche.model.Commande;
import com.demos.librairiecine7arche.model.LigneCommande;
import com.demos.librairiecine7arche.model.Stock;
import com.demos.librairiecine7arche.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    @Transactional
    public Commande createCommande(Commande commande) {
        // Décrémente le stock pour chaque ligne de commande
        for (LigneCommande ligneCommande : commande.getLignesCommande()) {
            // Objet Stock associé à l'article de la ligne de commande
            Stock stock = ligneCommande.getArticle().getStock();
            if (stock != null) {
                try {
                    // Décrémente le stock de la quantité
                    stock.decremente(ligneCommande.getQuantite());
                } catch (StockException e) {
                    // Gérer les exceptions de type StockException si nécessaire
                    // Lance une nouvelle exception StockException avec un message d'erreur personnalisé
                    throw new StockException("Stock insuffisant pour l'article: " + ligneCommande.getArticle().getDescription(), "Stock insuffisant");
                }
            } else {
                throw new StockException("Stock indisponible pour l'article numérique: " + ligneCommande.getArticle().getDescription(), "Stock indisponible");
            }
        }
        // Enregistre la commande
        return commandeRepository.save(commande);
    }

   /* @Override
    @Transactional
    public Commande createCommande(Commande commande) {
        // Décrémente le stock pour chaque ligne
        for (LigneCommande ligneCommande : commande.getLignesCommande()) {
            // objet Stock associé à l'article de la ligne de commande
            Stock stock = ligneCommande.getArticle().getStock();
            try {
                //  décrémente le stock de la quantité
                stock.decremente(ligneCommande.getQuantite());
            } catch (StockException e) {
                //  exception de type StockException  peut être levée lors de la décrémentation du stock
                // Lance une nouvelle exception StockException avec un message d'erreur personnalisé
                throw new StockException("Stock insuffisant pour l'article: " + ligneCommande.getArticle().getDescription(), "Stock insuffisant");
            }
        }
        // Enregistre la commande dans la base de données
        return commandeRepository.save(commande);


    }*/

    @Override
    public Iterable<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande findById(Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        return optionalCommande.orElse(null);
    }

    @Override
    public Commande findByRef(String ref) {
        return commandeRepository.findByNumComm(ref);
    }


}
