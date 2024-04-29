package com.youable.bestudy.service;

import com.youable.bestudy.domain.Delivery;
import com.youable.bestudy.domain.Member;
import com.youable.bestudy.domain.Order;
import com.youable.bestudy.domain.OrderItem;
import com.youable.bestudy.domain.items.Item;
import com.youable.bestudy.repository.MemberRepository;
import com.youable.bestudy.repository.OrderRepository;
import com.youable.bestudy.util.NotEnoughStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemService itemService; // 왜 이건 service이지?

    /** 주문 */
    public Long order(Long memberId, Long itemId, int count) throws NotEnoughStockException {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /** 주문 취소 */
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }
}
