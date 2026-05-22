package class5.package2;

import class5.role.Lion;
import class5.role.Role;
import class5.role.Staff;
import java.util.ArrayList;
import java.util.List;

public class MockMemberRepository implements MemberRepository {

    private final List<Role> members = List.of(
            new Lion("TestLion", "Computer Science", 12, "Backend", "20231234"),
            new Staff("TestStaff", "Design", 12, "Design", "Leader")
    );

    @Override
    public void save(Role member) {
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
        return findByName(name) != null;
    }
}
