package class3.role;

import class3.policy.SubmissionPolicy;

public abstract class Member {

    private final String name;
    private final String major;
    private final int generation;
    private final String part;

    protected Member(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    protected String getName() {
        return name;
    }

    protected String getMajor() {
        return major;
    }

    protected int getGeneration() {
        return generation;
    }

    protected String getPart() {
        return part;
    }

    // 역할별 정책 객체를 반환하는 추상 메서드 - 하위 클래스에서 구현
    protected abstract SubmissionPolicy getSubmissionPolicy();

    // 역할별 상세 정보를 반환하는 추상 메서드 - 하위 클래스에서 구현
    public abstract String getDetails();

    // 정책 객체에 판단을 위임 (조건문 없이 역할별 동작 수행)
    public boolean canSubmitAssignment() {
        return getSubmissionPolicy().canSubmit();
    }

    public void printInfo() {
        System.out.println(getDetails());
        System.out.println("과제 제출 가능 여부: " + (canSubmitAssignment() ? "제출 가능" : "제출 불가"));
    }
}
