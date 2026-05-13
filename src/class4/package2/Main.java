package class4.package2;

import class4.role.Lion;
import class4.role.Role;
import class4.role.Staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Role> members = new ArrayList<>();
    private static final Map<String, List<Role>> membersByPart = new HashMap<>();

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
                case 4:
                    printMembersByPart();
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

    private static void printMenu() {
        System.out.println("=== 멤버 관리 프로그램 Step 2 ===");
        System.out.println("1. 멤버 등록");
        System.out.println("2. 전체 멤버 조회");
        System.out.println("3. 이름으로 멤버 검색");
        System.out.println("4. 파트별 멤버 조회");
        System.out.println("0. 종료");
    }

    private static void registerMember() {
        System.out.println("등록할 역할을 선택해주세요.");
        System.out.println("1. 아기사자");
        System.out.println("2. 운영진");
        int roleType = inputInt("역할 선택: ");

        String name = inputText("이름: ");
        if (isDuplicateName(name)) {
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

        members.add(member);

        if (!membersByPart.containsKey(part)) {
            membersByPart.put(part, new ArrayList<>());
        }
        membersByPart.get(part).add(member);

        System.out.println("등록이 완료되었습니다.");
    }

    private static boolean isDuplicateName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static void printAllMembers() {
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

        for (Role member : members) {
            if (member.getName().equals(name)) {
                member.printInfo();
                return;
            }
        }

        System.out.println("해당 이름을 가진 멤버가 없습니다.");
    }

    private static void printMembersByPart() {
        if (membersByPart.isEmpty()) {
            System.out.println("등록된 파트가 없습니다.");
            return;
        }

        System.out.println("등록된 파트 목록: " + membersByPart.keySet());
        String part = inputText("조회할 파트명: ");
        List<Role> partMembers = membersByPart.get(part);

        if (partMembers == null || partMembers.isEmpty()) {
            System.out.println("해당 파트에 속한 멤버가 없습니다.");
            return;
        }

        for (Role member : partMembers) {
            member.printInfo();
            System.out.println("--------------------");
        }
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
