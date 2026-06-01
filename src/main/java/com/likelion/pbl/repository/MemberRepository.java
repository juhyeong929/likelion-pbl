package com.likelion.pbl.repository;

import com.likelion.pbl.role.Role;
import java.util.List;

public interface MemberRepository {

    void save(Role member);

    void updateById(Long id, Role member);

    boolean deleteById(Long id);

    Role findById(Long id);

    List<Role> findAll();

    boolean existsById(Long id);
}