package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.Item.ItemCreateDTO;
import minha.aplicacao.api.DTO.Item.ItemUpdateDTO;
import minha.aplicacao.api.exceptions.itemExceptions.ItemNotFoundException;
import minha.aplicacao.api.exceptions.itemExceptions.ItemsNotFoundException;
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
    public ResponseEntity<?> setItem(@RequestBody @Valid ItemCreateDTO itemCreateDTO)  {
        try{
            return ResponseEntity.ok(itemServices.setItem(itemCreateDTO));
        }catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
    }
    @GetMapping("/{idItem}")
    public ResponseEntity<?> getItem(@PathVariable String idItem){
        try{
            return ResponseEntity.ok(itemServices.getItemPorId(Integer.valueOf(idItem)));
        } catch (ItemNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (NumberFormatException e){
            ResponseBody responseBody = new ResponseBody(400, "ITEM INVALIDO");
            return ResponseEntity.badRequest().body(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @GetMapping
    public ResponseEntity<?> getItens(){
        try{
            return ResponseEntity.ok(itemServices.getItems());
        } catch (ItemsNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
       try{
           return ResponseEntity.ok(itemServices.updateItem(itemUpdateDTO));
       }catch (ItemNotFoundException e){
           ResponseBody responseBody = new ResponseBody(404, e.getMessage());
           return ResponseEntity.ok(responseBody);
       }
       catch (RuntimeException e){
           ResponseBody responseBody = new ResponseBody(400, e.getMessage());
           return ResponseEntity.badRequest().body(responseBody);
       }
    }
    @DeleteMapping("/{idItem}")
    public ResponseEntity<?> deleteItem(@PathVariable String idItem){
        try{
            return ResponseEntity.ok(itemServices.deleteitem(Integer.valueOf(idItem)));
        }catch (ItemNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        }
        catch (RuntimeException e){
            ResponseBody responseBody = new ResponseBody(400, e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
