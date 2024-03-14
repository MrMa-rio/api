package minha.aplicacao.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import minha.aplicacao.api.DTO.ItemCreateDTO;
import minha.aplicacao.api.DTO.ItemUpdateDTO;
import minha.aplicacao.api.models.Item;
import minha.aplicacao.api.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
    public ArrayList<Item> getItems(){
        ArrayList<Item> items = (ArrayList<Item>) iItemRepository.findAll();
        return items;
    }
    public Item getItem(Integer idItem){
        Optional<Item> item = iItemRepository.findById(idItem);
        return item.orElseThrow();
    }
    public Item updateItem(ItemUpdateDTO itemUpdateDTO){
        Item item = getItem(itemUpdateDTO.idItem());
        item.updateItem(itemUpdateDTO);
        iItemRepository.save(item);
        return item;
    }
    public Item deleteitem(Integer idItem){
        Item item = getItem(idItem);
        iItemRepository.deleteById(idItem);
        return item;
    }
}
