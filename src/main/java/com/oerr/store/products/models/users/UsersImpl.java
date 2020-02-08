package com.oerr.store.products.models.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersImpl implements UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersImpl(UsersRepository repository) {
        this.usersRepository = repository;
    }

    @Override
    public Iterable<UsersEntity> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<UsersEntity> getById(Long userId) {

        Optional<UsersEntity> user = usersRepository.findById(userId);

        return user;
    }


    @Override
    public UsersEntity save(UsersEntity user) {
        return usersRepository.save(user);
    }

    @Override
    public HttpStatus delete(Long userId) {

        Optional<UsersEntity> user = usersRepository.findById(userId);
        if (!user.isEmpty()) {
            user.ifPresent(result -> {
                usersRepository.delete(result);
            });
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public HttpStatus update(Long userId, UsersEntity user) {

        Optional<UsersEntity> u = usersRepository.findById(userId);
        if(!u.isEmpty()) {
            u.ifPresent(userResult -> {
                user.setUserId(userId);
                usersRepository.save(user);
            });
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }
}
