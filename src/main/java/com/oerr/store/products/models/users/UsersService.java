package com.oerr.store.products.models.users;

import org.springframework.http.HttpStatus;
import java.util.Optional;

public interface UsersService {

    Iterable <UsersEntity> getAll();

    Optional<UsersEntity> getById(Long userId);

    UsersEntity save(UsersEntity user);

    HttpStatus delete(Long userId);

    HttpStatus update(Long userId, UsersEntity user);

}