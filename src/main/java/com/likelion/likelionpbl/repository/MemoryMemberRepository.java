package com.likelion.likelionpbl.repository;

import com.likelion.likelionpbl.role.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final List<Role> members = new ArrayList<>();

    @Override
    public void save(Role member) {
        members.add(member);
    }

    @Override
    public void updateByName(String name, Role member) {
        for (int index = 0; index < members.size(); index++) {
            if (members.get(index).getName().equals(name)) {
                members.set(index, member);
                return;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        return members.removeIf(member -> member.getName().equals(name));
    }

    @Override
    public Role findByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(members);
    }

    @Override
    public boolean existsByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}