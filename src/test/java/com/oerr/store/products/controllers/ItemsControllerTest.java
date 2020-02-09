package com.oerr.store.products.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oerr.store.products.models.items.ItemsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemsControllerTest {

    @MockBean
    private String ResourceUrl = "http://localhost:8080/items/";
    private TestRestTemplate testRestTemplate;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
        mapper = new ObjectMapper();
    }

    @Test
    void methodFindAllShouldGetAllItems() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(ResourceUrl, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.get(0).path("name");

        assertNotNull(name.asText());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void methodFindByIdShouldFindItem3Name() {
        ItemsEntity response = testRestTemplate.getForObject(ResourceUrl + "3", ItemsEntity.class);

        assertNotNull(response.getItemName());
        assertEquals("Teseracto", response.getItemName());
    }

    @Test
    void methodSaveShouldSaveANewItem() {
        HttpEntity<ItemsEntity> request = new HttpEntity<>(new ItemsEntity(
                "Nombre de Prueba",
                "Descripción de prueba",
                20
        ));

        ItemsEntity response = testRestTemplate.postForObject(ResourceUrl, request, ItemsEntity.class);

        assertNotNull(response);
        assertEquals("Nombre de Prueba", response.getItemName());
        assertEquals("Descripción de prueba", response.getItemDescription());
    }

    @Test
    void methodDeleteShouldDeleteItem2() {
        testRestTemplate.delete(ResourceUrl + "2");
        ItemsEntity response = testRestTemplate.getForObject(ResourceUrl + "2", ItemsEntity.class);
        assertNull(response.getItemName());
        assertNull(response.getItemDescription());

    }

    @Test
    void methodUpdateShouldUpdateExistingItem1() {
        HttpEntity<ItemsEntity> request = new HttpEntity<>(new ItemsEntity(
                "Nombre de Prueba",
                "Descripción de prueba",
                20
        ));

        testRestTemplate.put(ResourceUrl+1, request);

        ItemsEntity response = testRestTemplate.getForObject(ResourceUrl + "1", ItemsEntity.class);

        assertNotNull(response);
        assertEquals("Nombre de Prueba", response.getItemName());
        assertEquals("Descripción de prueba", response.getItemDescription());
    }

    @Test
    void methodAddToStockShouldUpdateExistingItem5To13Stock() {
        HttpEntity<ItemsEntity> request = new HttpEntity<>(new ItemsEntity());

        testRestTemplate.put(ResourceUrl+"5/add/13",request);

        ItemsEntity response = testRestTemplate.getForObject(ResourceUrl + 5, ItemsEntity.class);

        assertNotNull(response);
        assertEquals(13, response.getItemStockNumber());
        assertEquals("Libreta con dibujo de IronMan en la portada.", response.getItemDescription());
    }

    @Test
    void methodRemoveFromStockShouldUpdateExistingItem4To2Stock() {
        HttpEntity<ItemsEntity> request = new HttpEntity<>(new ItemsEntity());

        testRestTemplate.put(ResourceUrl+"4/remove/28",request);

        ItemsEntity response = testRestTemplate.getForObject(ResourceUrl + 4, ItemsEntity.class);

        assertNotNull(response);
        assertEquals(2, response.getItemStockNumber());
        assertEquals("Casco de Batman para la motocicleta.", response.getItemDescription());
    }

}
