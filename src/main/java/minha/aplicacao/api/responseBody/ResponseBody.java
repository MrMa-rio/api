package minha.aplicacao.api.responseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class ResponseBody {
    private int status;
    private String message;

    public ResponseBody(int status, String message){
        this.message = message;
        this.status = status;
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
