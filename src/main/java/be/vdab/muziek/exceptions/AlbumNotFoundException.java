package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException{
    public AlbumNotFoundException(long id) {
        super("Album met de volgende ID is niet gevonden: " + id);
    }
}
