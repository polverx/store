package com.oerr.store.products.models.items;

import com.oerr.store.products.models.users.UsersRepository;
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

        if(idSearched.isPresent()) {
            idSearched.ifPresent(result -> {
                itemsRepository.delete(result);
            });
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus update(Long itemId, ItemsEntity item) {

        Optional<ItemsEntity> idSearched = itemsRepository.findById(itemId);

        if(idSearched.isPresent()) {
            idSearched.ifPresent(itemResult -> {
                item.setItemId(itemId);
                itemsRepository.save(item);
            });
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }
}
