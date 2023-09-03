package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ScoreNotValidException extends RuntimeException {
    public ScoreNotValidException() {
        super("Score moet tussen 0 en 10 liggen");
    }
}
