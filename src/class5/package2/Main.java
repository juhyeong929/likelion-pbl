package class5.package2;

import class5.role.Lion;
import class5.role.Role;
import class5.role.Staff;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MemberService memberService = buildService();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int menu = inputInt("메뉴 선택: ");

            switch (menu) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    printAllMembers();
                    break;
                case 3:
                    searchMemberByName();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택해주세요.");
            }

            System.out.println();
        }
    }

    private static MemberService buildService() {
        boolean useMock = false;
        MemberRepository repository = useMock ? new MockMemberRepository() : new MemoryMemberRepository();
        return new MemberService(repository);
    }

    private static void printMenu() {
        System.out.println("=== 멤버 관리 프로그램 Step 2 ===");
        System.out.println("1. 멤버 등록");
        System.out.println("2. 전체 멤버 조회");
        System.out.println("3. 이름으로 멤버 검색");
        System.out.println("0. 종료");
    }

    private static void registerMember() {
        System.out.println("등록할 역할을 선택해주세요.");
        System.out.println("1. 아기사자");
        System.out.println("2. 운영진");
        int roleType = inputInt("역할 선택: ");

        String name = inputText("이름: ");
        if (memberService.isDuplicateName(name)) {
            System.out.println("등록 실패: 이미 같은 이름의 멤버가 있습니다.");
            return;
        }

        String major = inputText("전공: ");
        int generation = inputInt("기수: ");
        String part = inputText("파트: ");

        Role member;
        if (roleType == 1) {
            String studentId = inputText("학번: ");
            member = new Lion(name, major, generation, part, studentId);
        } else if (roleType == 2) {
            String position = inputText("직책: ");
            member = new Staff(name, major, generation, part, position);
        } else {
            System.out.println("등록 실패: 올바른 역할을 선택해주세요.");
            return;
        }

        boolean success = memberService.registerMember(member);
        if (success) {
            System.out.println("등록이 완료되었습니다.");
        } else {
            System.out.println("등록 실패: 이미 같은 이름의 멤버가 있습니다.");
        }
    }

    private static void printAllMembers() {
        List<Role> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        for (Role member : members) {
            member.printInfo();
            System.out.println("--------------------");
        }
    }

    private static void searchMemberByName() {
        String name = inputText("검색할 이름: ");
        Role member = memberService.findMemberByName(name);
        if (member == null) {
            System.out.println("해당 이름을 가진 멤버가 없습니다.");
            return;
        }

        member.printInfo();
    }

    private static String inputText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int inputInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}
