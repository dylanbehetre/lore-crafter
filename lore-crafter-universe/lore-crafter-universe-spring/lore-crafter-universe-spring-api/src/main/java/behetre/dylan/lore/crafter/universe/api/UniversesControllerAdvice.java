package behetre.dylan.lore.crafter.universe.api;

import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {UniversesController.class})
public class UniversesControllerAdvice {

    @ExceptionHandler(NoUniverseNameException.class)
    public ResponseEntity<ProblemDetail> handleNoUniverseNameException(NoUniverseNameException e) {
        return ResponseEntity.badRequest()
                             .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Universe name property cannot be null"));
    }

    @ExceptionHandler(EmptyUniverseNameException.class)
    public ResponseEntity<ProblemDetail> handleEmptyUniverseNameException(EmptyUniverseNameException e) {
        return ResponseEntity.badRequest()
                             .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Universe name property cannot be empty"));
    }

}
