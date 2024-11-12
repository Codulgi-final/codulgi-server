package com.team5.codulgiserver.member.repository;

import com.team5.codulgiserver.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
