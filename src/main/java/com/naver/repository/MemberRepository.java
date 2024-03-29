package com.naver.repository;

import com.naver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT COUNT(m) FROM Member m")
    long countAll();

    Member findMemberByMemberId(Long memberId);
}
