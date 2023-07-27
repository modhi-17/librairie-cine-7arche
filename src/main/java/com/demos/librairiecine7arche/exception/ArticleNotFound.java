package com.demos.librairiecine7arche.exception;

// prend un message en tant que paramètre et le transmet à la classe parente,
// lorsque l'exception est levée, le message spécifié sera associé à l'exception.
public class ArticleNotFound extends Exception {
    public ArticleNotFound(String message) {
        super(message);
    }

}
