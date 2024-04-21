package com.youable.bestudy.repository;

import com.youable.bestudy.domain.items.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager em;

    // 새로운 아이템을 저장하거나 변경된 아이템의 데이터를 저장한다.
    // 하나의 메소드로 클라이언트의 로직을 단순화 시킨다
    public void save(Item item) {
        if (item.getId() == null) { // 식별자가 없는 경우
            em.persist(item); // 새로운 엔티티는 영속화. persist가 호출되면 식별자 값이 자동 할당 됨
        } else {
            em.merge(item); // 식별자 값이 있는 경우 수정(병합). 준영속 상태의 엔티티를 수정할 때 사용한다.
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
