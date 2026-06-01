package com.likelion.pbl.repository;

import com.likelion.pbl.role.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final List<Role> members = new ArrayList<>();
    private Long sequence = 0L;

    @Override
    public void save(Role member) {
        member.setId(++sequence);
        members.add(member);
    }

    @Override
    public void updateById(Long id, Role member) {
        for (int index = 0; index < members.size(); index++) {
            if (members.get(index).getId().equals(id)) {
                member.setId(id);
                members.set(index, member);
                return;
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return members.removeIf(member -> member.getId().equals(id));
    }

    @Override
    public Role findById(Long id) {
        for (Role member : members) {
            if (member.getId().equals(id)) {
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
    public boolean existsById(Long id) {
        for (Role member : members) {
            if (member.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}