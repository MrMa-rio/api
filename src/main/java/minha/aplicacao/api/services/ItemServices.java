package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.Item.ItemCreateDTO;
import minha.aplicacao.api.DTO.Item.ItemUpdateDTO;
import minha.aplicacao.api.exceptions.itemExceptions.ItemNotFoundException;
import minha.aplicacao.api.exceptions.itemExceptions.ItemsNotFoundException;
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

    public Item setItem(ItemCreateDTO itemCreateDTO) {
        try {
            Item item = new Item(itemCreateDTO);
            iItemRepository.save(item);
            return item;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = (ArrayList<Item>) iItemRepository.findAll();
        if(items.isEmpty()) throw new ItemsNotFoundException();
        return items;
    }
    public Item getItemPorId(Integer idItem) {
        Optional<Item> item = iItemRepository.findById(idItem);
        if(item.isEmpty()) throw new ItemNotFoundException();
        return item.get();
    }
    public Item updateItem(ItemUpdateDTO itemUpdateDTO) {
        Item item = getItemPorId(itemUpdateDTO.idItem());
        try{
            item.updateItem(itemUpdateDTO);
            iItemRepository.save(item);
            return item;
        } catch (RuntimeException e){throw new RuntimeException(e);}
    }
    public Item deleteitem(Integer idItem) {
        Item item = getItemPorId(idItem);
        iItemRepository.deleteById(idItem);
        return item;
    }
}