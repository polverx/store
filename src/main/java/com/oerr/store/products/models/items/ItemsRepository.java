package com.oerr.store.products.models.items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository <ItemsEntity, Long> {
}
