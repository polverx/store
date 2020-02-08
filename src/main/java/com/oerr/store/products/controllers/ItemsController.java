package com.oerr.store.products.controllers;

import com.oerr.store.products.models.items.ItemsEntity;
import com.oerr.store.products.models.items.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping()
    public Iterable<ItemsEntity> getAll() {
        return itemsService.getAll();
    }

    @GetMapping("/{itemId}")
    public ItemsEntity getById(@PathVariable Long itemId) {
        ItemsEntity item = itemsService.getById(itemId).orElseThrow();
        return item;
    }

    @PostMapping()
    public ItemsEntity save(@RequestBody final ItemsEntity item) {
        return itemsService.save(item);
    }

    @DeleteMapping("/{itemId}")
    public HttpStatus delete(@PathVariable Long itemId){
        return itemsService.delete(itemId);
    }

    @PutMapping("/{itemId}")
    public HttpStatus updateById(@PathVariable Long itemId,@RequestBody ItemsEntity item) {
        return itemsService.update(itemId, item);
    }

    @PutMapping("/{itemId}/remove/{amount}")
    public HttpStatus removeFromStockByItemId(@PathVariable Long itemId, @PathVariable Long amount) {
        return itemsService.removeFromStock(itemId, amount);
    }

    @PutMapping("/{itemId}/add/{amount}")
    public HttpStatus addToStockByItemId(@PathVariable Long itemId, @PathVariable Long amount) {
        return itemsService.addToStock(itemId, amount);
    }

}
