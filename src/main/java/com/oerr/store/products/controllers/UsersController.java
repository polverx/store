package com.oerr.store.products.controllers;

import com.oerr.store.products.models.users.UsersEntity;
import com.oerr.store.products.models.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public Iterable<UsersEntity> getAll() {
        return usersService.getAll();
    }

    @GetMapping("/{userId}")
    public UsersEntity getById(@PathVariable Long userId) {
        UsersEntity user = usersService.getById(userId).orElseThrow();
        return user;
    }

    @PostMapping()
    public UsersEntity save(@RequestBody final UsersEntity user) {
        return usersService.save(user);
    }

    @DeleteMapping("/{userId}")
    public HttpStatus delete(@PathVariable Long userId){
        return usersService.delete(userId);
    }

    @PutMapping("/{userId}")
    public HttpStatus updateById(@PathVariable Long userId,@RequestBody UsersEntity user) {
        return usersService.update(userId, user);
    }


}

