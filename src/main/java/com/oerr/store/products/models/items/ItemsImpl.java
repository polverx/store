package com.oerr.store.products.models.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemsImpl implements ItemsService {

    private ItemsRepository itemsRepository;

    @Autowired
    public ItemsImpl(ItemsRepository repository) {
        this.itemsRepository = repository;
    }

    @Override
    public Iterable<ItemsEntity> getAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Optional<ItemsEntity> getById(Long itemId) {

        Optional<ItemsEntity> item = itemsRepository.findById(itemId);

        return item;


    }

    @Override
    public ItemsEntity save(ItemsEntity item) {
        return itemsRepository.save(item);
    }

    @Override
    public HttpStatus delete(Long itemId) {

        Optional<ItemsEntity> idSearched = itemsRepository.findById(itemId);

        if (idSearched.isPresent()) {

            idSearched.ifPresent(item -> itemsRepository.delete(item));
            return HttpStatus.ACCEPTED;

        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus update(Long itemId, ItemsEntity item) {

        Optional<ItemsEntity> idSearched = itemsRepository.findById(itemId);

        if (idSearched.isPresent()) {

            item.setItemId(itemId);
            itemsRepository.save(item);
            return HttpStatus.ACCEPTED;

        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus removeFromStock(Long itemId, Long amount) {

        Optional<ItemsEntity> idSearched = itemsRepository.findById(itemId);

        if (idSearched.isPresent()) {

            idSearched.get().setItemId(itemId);

            if ( idSearched.get().getItemStockNumber() <= 0 || idSearched.get().getItemStockNumber() < amount) {
                return HttpStatus.NOT_ACCEPTABLE;
            }

            idSearched.get().setItemStockNumber(idSearched.get().getItemStockNumber() - amount);
            itemsRepository.save(idSearched.get());
            return HttpStatus.ACCEPTED;
        }


        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus addToStock(Long itemId, Long amount) {

        Optional<ItemsEntity> idSearched = itemsRepository.findById(itemId);

        if (idSearched.isPresent()) {
            idSearched.ifPresent(itemResult -> {
                idSearched.get().setItemId(itemId);
                idSearched.get().setItemStockNumber(idSearched.get().getItemStockNumber() + amount);
                itemsRepository.save(idSearched.get());
            });
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }
}
