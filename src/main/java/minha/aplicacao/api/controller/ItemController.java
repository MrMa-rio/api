package minha.aplicacao.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import minha.aplicacao.api.DTO.ItemUpdateDTO;
import minha.aplicacao.api.models.Item;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"itens","itens/"})
public class ItemController {

    @Autowired
    private ItemServices itemServices;
    @PostMapping
    public ResponseEntity setItem(@RequestBody @Valid ItemCreateDTO itemCreateDTO)  {

        try{
            return ResponseEntity.ok(itemServices.setItem(itemCreateDTO));
        }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<String> getItem(@PathVariable String idItem){
        //TODO
        return ResponseEntity.ok("");
    }
    @GetMapping
    public ResponseEntity<String> getItens(){
        //TODO
        return ResponseEntity.ok("");
    }
    @PutMapping
    public ResponseEntity<String> updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
        //TODO
        return ResponseEntity.ok("");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteItem(String idItem){
        //TODO
        return ResponseEntity.ok("");
    }
}
