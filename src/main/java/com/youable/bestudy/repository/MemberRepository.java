package com.youable.bestudy.repository;

import com.youable.bestudy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 자동 등록.
public class MemberRepository {

    @PersistenceContext // 컨테이너가 엔티티 매니저를 관리하고 제공. 엔티티 매니저 주입
    EntityManager em;

    public void save(Member member) {
        em.persist(member); // 멤버 엔티티 영속화
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
