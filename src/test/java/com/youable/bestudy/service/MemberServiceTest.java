package com.youable.bestudy.service;

import com.youable.bestudy.domain.Member;
import com.youable.bestudy.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("kim killdong");

        // When
        Long saveId = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test()
    @Rollback(value = false)
    public void 중복_회원_예외() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        memberService.join(member1);

        // Then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}