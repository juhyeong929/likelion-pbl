package class5.package1;

import class5.role.Role;
import java.util.List;

public class MemberService {

    private final MemberRepository repository = new MemberRepository();

    public boolean registerMember(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public boolean isDuplicateName(String name) {
        return repository.existsByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public Role findMemberByName(String name) {
        return repository.findByName(name);
    }
}
