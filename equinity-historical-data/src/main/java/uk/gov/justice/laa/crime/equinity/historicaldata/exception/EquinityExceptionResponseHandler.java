package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import jakarta.validation.ConstraintViolationException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EquinityExceptionResponseHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(DateRangeConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDateRangeConstraintViolationException(
            DateRangeConstraintViolationException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(NotEnoughSearchParametersException.class)
    @ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
    public ResponseEntity<String> handleNotEnoughSearchParametersException(
            NotEnoughSearchParametersException exception
    ) {
        return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(exception.getMessage());
    }

    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleJsonException(
            JSONException exception
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was a problem reading CRM image file. " + exception.getMessage());
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleClassCastException(
            ClassCastException exception
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was a problem reading CRM image file. " + exception.getMessage());
    }

}
