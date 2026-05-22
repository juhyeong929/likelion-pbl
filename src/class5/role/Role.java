package class5.role;

import class5.policy.SubmissionPolicy;

public abstract class Role {

    private final String name;
    private final String major;
    private final int generation;
    private final String part;

    protected Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    protected abstract SubmissionPolicy getSubmissionPolicy();

    public abstract String getDetails();

    public boolean canSubmitAssignment() {
        return getSubmissionPolicy().canSubmit();
    }

    public void printInfo() {
        System.out.println(getDetails());
        System.out.println("과제 제출 가능 여부: " + (canSubmitAssignment() ? "제출 가능" : "제출 불가"));
    }
}
