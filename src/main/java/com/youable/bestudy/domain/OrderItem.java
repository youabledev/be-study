package com.youable.bestudy.domain;

import com.youable.bestudy.domain.items.Item;
import com.youable.bestudy.util.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice; // 주문가격
    private int count;

    // == 생성 메소드 == //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) throws NotEnoughStockException {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 주문이 생기는 거라, 주문한 수량만큼 상품의 재고를 줄임
        return orderItem;
    }

    // == 비즈니스 로직 == //
    /** 주문 취소 */
    public void cancel() {
        getItem().addStock(count); // 주문이 취소되면 재고 수량이 늘어남
    }

    // == 조회 로직 == //
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
