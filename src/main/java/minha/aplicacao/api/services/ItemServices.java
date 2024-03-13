package minha.aplicacao.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import minha.aplicacao.api.DTO.ItemUpdateDTO;
import minha.aplicacao.api.models.Item;
import minha.aplicacao.api.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServices {

    @Autowired
    private IItemRepository iItemRepository;
    public Item setItem(ItemCreateDTO itemCreateDTO){
        Item item = new Item(itemCreateDTO);
        iItemRepository.save(item);
        try {
            return item;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public String getItems(){
        //TODO
        return "";
    }
    public String getItem(Integer idItem){
        //TODO
        return "";
    }
    public String updateItem(ItemUpdateDTO itemUpdateDTO){
        //TODO
        return "";
    }
    public String deleteitem(Integer idItem){
        //TODO
        return "";
    }


}
