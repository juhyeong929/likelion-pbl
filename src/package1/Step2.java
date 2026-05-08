package package1;

import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Step 2: 객체 내부에서 유효성 검증 ===\n");

        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();

        System.out.print("전공을 입력하세요: ");
        String major = scanner.nextLine();

        System.out.print("기수를 입력하세요: ");
        int generation = scanner.nextInt();

        System.out.println();
        
        // 2. 검증 없이 바로 객체 생성
        System.out.println("[Lion 객체 생성 완료]");
        Lion lion = new Lion(name, major, generation);

        System.out.println();

        // 3. 객체가 스스로 검증 수행
        System.out.println("[객체 내부에서 검증 시작]");

        if (lion.validate()) {
            System.out.println("✅ 객체 검증 성공");
            System.out.println("→ 유효한 아기사자 정보입니다.");
            lion.printInfo();
        } else {
            System.out.println("❌ 객체 검증 실패");
            System.out.println("→ 유효하지 않은 아기사자 정보입니다.");

            // 어떤 필드가 문제인지 확인
            System.out.println("\n[검증 실패 원인]");
            if (lion.name == null || lion.name.trim().isEmpty()) {
                System.out.println("- 이름이 비어 있습니다.");
            }
            if (lion.major == null || lion.major.trim().isEmpty()) {
                System.out.println("- 전공이 비어 있습니다.");
            }
            // private 필드는 직접 접근 불가하므로 검증 메서드 결과로만 판단
            System.out.println();
        }

        scanner.close();
    }

}
