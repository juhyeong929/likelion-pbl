package package2;

import package1.Lion;

public class Step3 {
    public static void main(String[] args) {
        System.out.println("=== Step 3: 접근 제어자에 따른 필드 접근 확인 ===\n");

        // 객체 생성
        Lion lion = new Lion("김멋사", "컴퓨터공학", 13);

        System.out.println(" 현재 위치: package2 (Lion 클래스와 다른 패키지)\n");

        System.out.println("─────────────────────────────────────");

        // 1. public 필드 접근 시도
        System.out.println("1️⃣  public 필드 (name) 접근 시도");
        System.out.println("─────────────────────────────────────");
        System.out.println("   현재 값: " + lion.name);
        lion.name = "이멋사";
        System.out.println("   변경 시도: lion.name = \"이멋사\"");
        System.out.println("   변경 후 값: " + lion.name);
        System.out.println("   ✅ 접근 성공! (다른 패키지에서도 접근 가능)\n");

        // 2. default 필드 접근 시도
        System.out.println("2️⃣  default 필드 (major) 접근 시도");
        System.out.println("─────────────────────────────────────");
        System.out.println("   변경 시도: lion.major = \"경영학\"");
        System.out.println("   ❌ 컴파일 에러 발생!");
        System.out.println("   → The field Lion.major is not visible");
        System.out.println("   → default 접근 제어자는 같은 패키지에서만 접근 가능\n");

        // 3. private 필드 접근 시도
        System.out.println("3️⃣  private 필드 (generation) 접근 시도");
        System.out.println("─────────────────────────────────────");
        System.out.println("   변경 시도: lion.generation = 14");
        System.out.println("   ❌ 컴파일 에러 발생!");
        System.out.println("   → The field Lion.generation is not visible");
        System.out.println("   → private 접근 제어자는 클래스 내부에서만 접근 가능\n");


        System.out.println("─────────────────────────────────────");
        System.out.println(" 접근 제어자별 접근 가능 범위 정리");
        System.out.println("─────────────────────────────────────");
        System.out.println("public    : ✅ 어디서든 접근 가능");
        System.out.println("default   : ⚠️  같은 패키지에서만 접근 가능");
        System.out.println("private   : 🔒 클래스 내부에서만 접근 가능");
        System.out.println("─────────────────────────────────────\n");
    }
}
