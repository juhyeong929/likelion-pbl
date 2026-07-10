package com.likelion.likelionpbl.assignment.repository;

import com.likelion.likelionpbl.assignment.domain.Assignment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByMemberId(Long memberId);

    List<Assignment> findByTitleContaining(String keyword);
}
