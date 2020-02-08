package com.oerr.store.products;

import com.oerr.store.products.models.items.ItemsEntity;
import com.oerr.store.products.models.items.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Autowired
    private ItemsService itemsService;

    @Override
    public void run (String... args) throws Exception {

        List<ItemsEntity> items = new ArrayList<>();

        items.add(new ItemsEntity("Item 1","Descripción de prueba",10,true));
        items.add(new ItemsEntity("Item 2","Descripción de prueba",20,true));
        items.add(new ItemsEntity("Item 3","Descripción de prueba",30,true));
        items.add(new ItemsEntity("Item 4","Descripción de prueba",100,true));
        items.add(new ItemsEntity("Item 5","Descripción de prueba",0,true));


        for (ItemsEntity item : items) {
            itemsService.save(item);
        }

    }


}
