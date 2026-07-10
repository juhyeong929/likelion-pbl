package com.likelion.likelionpbl.member.repository;

import com.likelion.likelionpbl.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    List<Member> findByPart(String part);
}
