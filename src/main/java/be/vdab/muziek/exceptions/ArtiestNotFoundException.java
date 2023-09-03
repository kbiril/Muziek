package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtiestNotFoundException extends RuntimeException{
    public ArtiestNotFoundException(long id) {
        super("Artiest met de volgende id is niet gevonden: " + id);
    }
}
