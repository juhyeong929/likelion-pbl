package class3;

import class3.role.Lion;
import class3.role.Member;
import class3.role.Staff;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 아기사자 정보 입력
        System.out.println("=== 아기사자 정보 입력 ===");
        System.out.print("이름: ");
        String lionName = scanner.nextLine();
        System.out.print("전공: ");
        String lionMajor = scanner.nextLine();
        System.out.print("기수: ");
        int lionGeneration = Integer.parseInt(scanner.nextLine());
        System.out.print("파트: ");
        String lionPart = scanner.nextLine();
        System.out.print("학번: ");
        String studentId = scanner.nextLine();

        Member lion = new Lion(lionName, lionMajor, lionGeneration, lionPart, studentId);

        System.out.println();

        // 운영진 정보 입력
        System.out.println("=== 운영진 정보 입력 ===");
        System.out.print("이름: ");
        String staffName = scanner.nextLine();
        System.out.print("전공: ");
        String staffMajor = scanner.nextLine();
        System.out.print("기수: ");
        int staffGeneration = Integer.parseInt(scanner.nextLine());
        System.out.print("파트: ");
        String staffPart = scanner.nextLine();
        System.out.print("직책: ");
        String position = scanner.nextLine();

        Member staff = new Staff(staffName, staffMajor, staffGeneration, staffPart, position);

        scanner.close();

        // 결과 출력
        System.out.println();
        System.out.println("=== 결과 출력 ===");
        lion.printInfo();
        System.out.println();
        staff.printInfo();
    }
}
