package com.youable.bestudy.service;

import com.youable.bestudy.domain.items.Item;
import com.youable.bestudy.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemServiceTest {
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 물건등록() throws Exception {
        // Given
        Item item = new Item();
        item.setName("상추");
        item.setPrice(1000);
        item.setStockQuantity(10);

        // When
        itemService.saveItem(item);

        // Then
        assertEquals(item, itemService.findOne(item.getId()));
    }
}