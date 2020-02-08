package com.oerr.store.products.models.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import reactor.test.publisher.TestPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemsServiceTest {

    private ItemsService itemsService;

    ItemsEntity item1;
    ItemsEntity item2;
    ItemsEntity item3;

    @Mock
    private ItemsRepository itemsRepository;
    TestPublisher<ItemsEntity> testPublisher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        itemsService = new ItemsImpl(itemsRepository);

        testPublisher = TestPublisher.create();

        item1 = new ItemsEntity(
                "Producto 1",
                "Descripción de producto 1.",
                50,
                true
        );

        item2 = new ItemsEntity(
                "Producto 2",
                "Descripción de producto 2.",
                100,
                true
        );

        item3 = new ItemsEntity(
                "Producto 3",
                "Descripción de producto 3.",
                0,
                false
        );



    }

    @Test
    void methodFindAllShouldReturnAllItems() {
        List<ItemsEntity> itemList = Arrays.asList(item1, item2, item3);
        when(itemsRepository.findAll()).thenReturn(itemList);

        Iterable<ItemsEntity> allItems = itemsService.getAll();

        assert allItems.iterator().hasNext();
        verify(itemsRepository).findAll();

    }

    @Test
    void methodItemById2ShouldReturnItem2() {
        ItemsEntity item = new ItemsEntity();
        when(itemsRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<ItemsEntity> itemById = itemsService.getById(1L);

        Assert.notNull(itemById.orElseThrow(), "El objeto no puede ser null");
        verify(itemsRepository).findById(1L);
    }

    @Test
    void methodShouldReturnASavedUser() {
        ItemsEntity pet = new ItemsEntity();
        when(itemsRepository.save(pet)).thenReturn(pet);

        ItemsEntity savedPet = itemsService.save(pet);

        Assert.notNull(savedPet, "El objeto no puede ser null");
        verify(itemsRepository).save(pet);
    }

    @Test
    void methodShouldReturnAnUpdatedUser() {
        ItemsEntity item = new ItemsEntity("Producto 1", "Descripción de producto.", 50, true);
        when(itemsRepository.save(item)).thenReturn(item);

        HttpStatus updatedStatus = itemsService.update(1L, item);

        assertEquals(updatedStatus, HttpStatus.ACCEPTED);
        verify(itemsRepository).findById(1L);
        //verify(petRepository).save(pet);
    }

    @Test
    void methodShouldDeleteUser() {
        HttpStatus deletedStatus = itemsService.delete(0L);

        assertEquals(deletedStatus, HttpStatus.ACCEPTED);
        verify(itemsRepository).findById(0L);
    }

}
