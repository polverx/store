package com.oerr.store.products.models.items;

import org.springframework.http.HttpStatus;
import java.util.Optional;

public interface ItemsService {

    Iterable <ItemsEntity> getAll();

    Optional<ItemsEntity> getById(Long itemId);

    ItemsEntity save(ItemsEntity item);

    HttpStatus delete(Long itemId);

    HttpStatus update(Long itemId, ItemsEntity item);
}
