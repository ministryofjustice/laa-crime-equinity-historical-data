package uk.gov.justice.laa.crime.equinity.historicaldata.exception;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import jakarta.validation.ConstraintViolationException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class EquinityExceptionResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(EquinityExceptionResponseHandler.class);

    /**
     * 400 Bad Request
     */
    @ExceptionHandler({ConstraintViolationException.class, DateRangeConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            StartDateConstraintViolationException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(Exception exception) {
        return ResponseEntity.status(BAD_REQUEST).body(exception.getMessage());
    }

    /**
     * 401 Unauthorized
     */
    @ExceptionHandler(UnauthorizedUserProfileException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ResponseEntity<String> handleUnauthorizedUserProfileException(UnauthorizedUserProfileException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(exception.getMessage());
    }

    /**
     * 404 Not Found
     */
    @ExceptionHandler({NoResourceFoundException.class, ResourceNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(Exception exception) {
        return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
    }

    /**
     * 416 Range Not Satisfiable
     */
    @ExceptionHandler(NotEnoughSearchParametersException.class)
    @ResponseStatus(REQUESTED_RANGE_NOT_SATISFIABLE)
    public ResponseEntity<String> handleNotEnoughSearchParametersException(NotEnoughSearchParametersException exception) {
        return ResponseEntity.status(REQUESTED_RANGE_NOT_SATISFIABLE).body(exception.getMessage());
    }

    /**
     * 500 Internal Server Error
     */
    @ExceptionHandler({JSONException.class, ClassCastException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleCrmImageFilmException(Exception exception) {
        String logMessage = logError("There was a problem reading CRM image file.", exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(logMessage);
    }

    @ExceptionHandler(SQLServerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleSQLServerException(SQLServerException exception) {
        String logMessage = logError("There was an unexpected problem with the database.", exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(logMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        String logMessage = logError("There was an unexpected problem with the application.", exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(logMessage);
    }

    private String logError(String errorMessage, Exception exception) {
        String logMessage = String.format("%s %s :: %s ", errorMessage, exception.getClass(), exception.getMessage());
        log.error(logMessage);
        return logMessage;
    }
}
