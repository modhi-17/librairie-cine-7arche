package com.demos.librairiecine7arche;

import com.demos.librairiecine7arche.exception.StockException;
import com.demos.librairiecine7arche.model.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibrairieCine7archeApplicationTests {

    @Test
    void contextLoads() {
    }
    public static class StockTest {

        @Test
        public void testDecrementStock() throws StockException {
            // Créer un exemple de stock avec une quantité initiale de 10
            Stock stock = new Stock();
            stock.setNombre(10);

            // Appeler la méthode decremente du stock
            stock.decremente(3);

            // Vérifier si la quantité du stock a été décrémentée correctement
            Assertions.assertEquals(7, stock.getNombre());
        }


        @Test
        public void testIncrementStock() {
            // Créer un exemple de stock avec une quantité initiale de 5
            Stock stock = new Stock();
            stock.setNombre(5);

            // Appeler la méthode incremente du stock
            stock.incremente(2);

            // Vérifier si la quantité du stock a été incrémentée correctement
            Assertions.assertEquals(7, stock.getNombre());
        }

        @Test
        public void testDecrementStock_InsufficientStock() {
            // Créer un exemple de stock avec une quantité initiale de 5
            Stock stock = new Stock();
            stock.setNombre(5);

            // Appeler la méthode decremente du stock avec une quantité supérieure à la quantité disponible
            Assertions.assertThrows(StockException.class, () -> stock.decremente(7));

            // Vérifier si la quantité du stock n'a pas été modifiée
            Assertions.assertEquals(5, stock.getNombre());
        }
    }



}
