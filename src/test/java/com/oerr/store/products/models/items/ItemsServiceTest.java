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
    ItemsEntity item3Updated;

    @Mock
    private ItemsRepository itemsRepository;
    TestPublisher<ItemsEntity> testPublisher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        itemsService = new ItemsImpl(itemsRepository);

        testPublisher = TestPublisher.create();

        item1 = new ItemsEntity(
                1,
                "Producto 1",
                "Descripción de producto 1.",
                50,
                true
        );

        item2 = new ItemsEntity(
                2,
                "Producto 2",
                "Descripción de producto 2.",
                100,
                true
        );

        item3 = new ItemsEntity(
                3,
                "Producto 3",
                "Descripción de producto 3.",
                0,
                false
        );
        item3Updated = new ItemsEntity(
                3,
                "Producto 3",
                "Nueva descripción.",
                1,
                true
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

        when(itemsRepository.findById(2L)).thenReturn(Optional.of(item2));

        Optional<ItemsEntity> itemById = itemsService.getById(2L);

        Assert.notNull(itemById.orElseThrow(), "El objeto no puede ser null");
        verify(itemsRepository).findById(2L);
    }

    @Test
    void methodSaveShouldReturnASavedItem() {
        ItemsEntity testItem = item1;
        when(itemsRepository.save(testItem)).thenReturn(testItem);

        ItemsEntity savedItem = itemsService.save(testItem);

        Assert.notNull(savedItem, "El objeto no puede ser null");
        verify(itemsRepository).save(testItem);
    }

    @Test
    void methodUpdateShouldReturnAnUpdatedItem() {

        when(itemsRepository.save(item1)).thenReturn(item1);

        HttpStatus updatedStatus = itemsService.update(1L, item1);

        assertEquals(HttpStatus.NOT_FOUND, updatedStatus);
        verify(itemsRepository).findById(1L);
    }

    @Test
    void methodDeleteShouldDeleteItem() {
        HttpStatus deletedStatus = itemsService.delete(0L);

        assertEquals(HttpStatus.NOT_FOUND, deletedStatus);
        verify(itemsRepository).findById(0L);
    }

    @Test
    void methodRemoveFromStockShouldRemoveItemsFromStock() {
        when(itemsRepository.save(item1)).thenReturn(item1);

        HttpStatus updatedStatus = itemsService.removeFromStock(1L, 50L);

        assertEquals(HttpStatus.NOT_FOUND, updatedStatus);
        verify(itemsRepository).findById(1L);
    }

    @Test
    void methodAddToStockShouldAddItemsToStock() {
        when(itemsRepository.save(item1)).thenReturn(item1);

        HttpStatus updatedStatus = itemsService.addToStock(1L, 50L);

        assertEquals(HttpStatus.NOT_FOUND, updatedStatus);
        verify(itemsRepository).findById(1L);
    }

}
