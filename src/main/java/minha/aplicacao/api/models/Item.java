package minha.aplicacao.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.ItemCreateDTO;

@Table(name = "tb_item")
@Getter
@Entity
@NoArgsConstructor
public class Item {

    private String nome;
    @Column(name = "preco_unitario")
    private Double precoUnitario;
    @Column(name = "imagem_64")
    private String imagem64;
    @Id
    private Integer idItem;

    public Item(ItemCreateDTO itemCreateDTO){
        this.idItem = itemCreateDTO.idItem();
        this.precoUnitario = itemCreateDTO.precoUnitario();
        this.imagem64 = itemCreateDTO.imagem64();
        this.nome = itemCreateDTO.nome();
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
