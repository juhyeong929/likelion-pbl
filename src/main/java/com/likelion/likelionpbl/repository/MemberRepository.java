package com.likelion.likelionpbl.repository;

import com.likelion.likelionpbl.role.Role;
import java.util.List;

public interface MemberRepository {

    void save(Role member);

    void updateByName(String name, Role member);

    boolean deleteByName(String name);

    Role findByName(String name);

    List<Role> findAll();

    boolean existsByName(String name);
}