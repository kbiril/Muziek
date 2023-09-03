package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtiestHeeftDezeAlbumAlException extends RuntimeException{
    public ArtiestHeeftDezeAlbumAlException() {
        super("Artiest heeft deze album al");
    }
}
