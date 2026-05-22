package class5.role;

import class5.policy.StaffSubmissionPolicy;
import class5.policy.SubmissionPolicy;

public class Staff extends Role {

    private final String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    protected SubmissionPolicy getSubmissionPolicy() {
        return new StaffSubmissionPolicy();
    }

    @Override
    public String getDetails() {
        return "[운영진 정보]\n" +
                "이름: " + getName() + "\n" +
                "전공: " + getMajor() + "\n" +
                "기수: " + getGeneration() + "기\n" +
                "파트: " + getPart() + "\n" +
                "직책: " + position;
    }
}
