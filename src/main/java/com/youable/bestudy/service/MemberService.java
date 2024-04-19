package com.youable.bestudy.service;

import com.youable.bestudy.domain.Member;
import com.youable.bestudy.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링빈에 의해 자동 등록
@Transactional // 트랜잭션 적용. 외부에서 이 클래스의 메소드 호출시 트랜잭션을 시작하고 메소드 종료시 트랜잭션을 커밋. 만약 예외 발생시 트랜잭션 롤백
public class MemberService {
    @Autowired // 적절한 스프링 빈을 주입해 줌
    MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
