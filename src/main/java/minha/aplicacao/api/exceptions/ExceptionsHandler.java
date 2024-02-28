package minha.aplicacao.api.exceptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String dataConflictException(DataIntegrityViolationException e) throws JsonProcessingException {
        String error = "Falha ao enviar os dados ao banco.";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardErrorDTO standardErrorDTO = new StandardErrorDTO(status.value(), error, e.getMostSpecificCause().getMessage());
        return standardErrorDTO.toJson();
    }
}
