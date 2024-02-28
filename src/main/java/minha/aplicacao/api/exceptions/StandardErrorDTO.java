package minha.aplicacao.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StandardErrorDTO {
    @JsonProperty
    private int value;
    @JsonProperty
    private String error;
    @JsonProperty
    private String message;

    public StandardErrorDTO(
            int value,
            String error,
            String message) {
        this.value = value;
        this.error = error;
        this.message = message;
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
