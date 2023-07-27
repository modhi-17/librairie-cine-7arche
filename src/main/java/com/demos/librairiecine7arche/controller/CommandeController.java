package com.demos.librairiecine7arche.controller;

import com.demos.librairiecine7arche.exception.ErrorResponse;
import com.demos.librairiecine7arche.exception.StockException;
import com.demos.librairiecine7arche.model.Commande;
import com.demos.librairiecine7arche.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Commande>> getAllCommandes() {
        Iterable<Commande> commandes = commandeService.getAllCommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }


    // Définit une méthode de contrôleur pour gérer une requête POST vers l'endpoint "/save"
    @PostMapping("/save")
    public ResponseEntity<?> ajoutCommande(@RequestBody Commande commande) {
        try {
            // créer une nouvelle commande avec l'objet commande reçu en paramètre
            Commande createdCommande = commandeService.createCommande(commande);
            // Retourne une réponse HTTP avec le code de statut 200 OK et le corps de la réponse contenant la commande créée
            return ResponseEntity.ok(createdCommande);
        } catch (StockException e) {
            // Capture une exception de type StockException qui peut être levée par la méthode createCommande
            // Crée un objet ErrorResponse avec le code d'erreur et le message de l'exception
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());
            // Retourne une réponse HTTP avec le code de statut 400 Bad Request et le corps de la réponse contenant l'objet ErrorResponse
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            System.out.println(e);
            // Capture toute autre exception qui peut être levée
            // Retourne une réponse HTTP avec le code de statut 500 Internal Server Error et un corps vide
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable("id") Long id) {
        Commande commande = commandeService.findById(id);
        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<Commande> getCommandeByRef(@PathVariable("ref") String ref) {
        Commande commande = commandeService.findByRef(ref);
        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
