package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import minha.aplicacao.api.DTO.ItemUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping({"itens","itens/"})
public class ItemController {
    
    @PostMapping
    public ResponseEntity<String> setItem(@RequestBody @Valid ItemCreateDTO itemCreateDTO){
        //TODO
        return ResponseEntity.ok("");
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
