package com.naver.generator;

import com.naver.entity.Member;
import com.naver.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class MemberGenerator {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberGenerator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long randomMemberId() {
        Random random = new Random();
        Long count = memberRepository.countAll();
        return random.nextLong(count)+1;
    }

    public Long randomMemberIdExcept(Long exceptId) {
        Long memberId;
        while (Objects.equals(memberId = randomMemberId(), exceptId)){}
        return  memberId;
    }

    public Member randomMember() {
        long memberID = randomMemberId();
        return memberRepository.findMemberByMemberId(memberID);
    }

    public Member randomMemberExcept(Long exceptId) {
        long memberID = randomMemberIdExcept(exceptId);
        return memberRepository.findMemberByMemberId(memberID);
    }
}
