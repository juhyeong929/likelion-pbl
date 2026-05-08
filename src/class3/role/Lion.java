package class3.role;

import class3.policy.LionSubmissionPolicy;
import class3.policy.SubmissionPolicy;

public class Lion extends Member {

    private final String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    protected SubmissionPolicy getSubmissionPolicy() {
        return new LionSubmissionPolicy();
    }

    @Override
    public String getDetails() {
        return "[아기사자 정보]\n" +
                "이름: " + getName() + "\n" +
                "전공: " + getMajor() + "\n" +
                "기수: " + getGeneration() + "기\n" +
                "파트: " + getPart() + "\n" +
                "학번: " + studentId;
    }
}
