package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import minha.aplicacao.api.DTO.ItemUpdateDTO;
import minha.aplicacao.api.models.Item;
import minha.aplicacao.api.responseBody.ResponseBody;
import minha.aplicacao.api.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping({"itens","itens/"})
public class ItemController {

    @Autowired
    private ItemServices itemServices;
    @PostMapping
    public ResponseEntity<?> setItem(@RequestBody @Valid ItemCreateDTO itemCreateDTO)  {
        try{
            return ResponseEntity.ok(itemServices.setItem(itemCreateDTO));
        }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping("/{idItem}")
    public ResponseEntity<Item> getItem(@PathVariable String idItem){
        return ResponseEntity.ok(itemServices.getItem(Integer.valueOf(idItem)));
    }
    @GetMapping
    public ResponseEntity<ArrayList<Item>> getItens(){
        return ResponseEntity.ok(itemServices.getItems());
    }
    @PutMapping
    public ResponseEntity<Item> updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
        return ResponseEntity.ok(itemServices.updateItem(itemUpdateDTO));
    }
    @DeleteMapping("/{idItem}")
    public ResponseEntity<Item> deleteItem(@PathVariable String idItem){
        return ResponseEntity.ok(itemServices.deleteitem(Integer.valueOf(idItem)));
    }
}
