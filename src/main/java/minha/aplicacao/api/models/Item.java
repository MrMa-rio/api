package minha.aplicacao.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minha.aplicacao.api.DTO.ItemUpdateDTO;

@Table(name = "tb_item")
@Entity
@Getter
@NoArgsConstructor
public class Item {

    private String nome;
    @Column(name = "preco_unitario")
    private Double precoUnitario;
    @Column(name = "imagem_64")
    private String imagem64;
    @Id @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void updateItem(ItemUpdateDTO itemUpdateDTO){
        if(itemUpdateDTO.nome() != null) {this.nome = itemUpdateDTO.nome();}
        if(itemUpdateDTO.imagem64() != null) {this.imagem64 = itemUpdateDTO.imagem64();}
        if(itemUpdateDTO.precoUnitario() != null) {this.precoUnitario = itemUpdateDTO.precoUnitario();}

    }
}
