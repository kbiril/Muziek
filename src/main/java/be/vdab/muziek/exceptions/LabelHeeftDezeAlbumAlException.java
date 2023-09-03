package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LabelHeeftDezeAlbumAlException extends RuntimeException {
    public LabelHeeftDezeAlbumAlException() {
        super("Label heeft deze album al");
    }
}
