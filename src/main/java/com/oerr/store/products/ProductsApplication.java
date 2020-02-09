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

        items.add(new ItemsEntity("Camiseta Capitán America","Camiseta con logo de Avengers y escudo de Capitán América.",10));
        items.add(new ItemsEntity("Camiseta Ironspider","Camiseta para gym de Spiderman.",20));
        items.add(new ItemsEntity("Teseracto","Cubo cósmico con propiedades interdimensionales.",1));
        items.add(new ItemsEntity("Casco Batman","Casco de Batman para la motocicleta.",30));
        items.add(new ItemsEntity("Libreta de IronMan","Libreta con dibujo de IronMan en la portada.",0));

        for (ItemsEntity item : items) {
            itemsService.save(item);
        }

    }


}
