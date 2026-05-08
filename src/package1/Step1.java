package package1;

import java.util.Scanner;

public class Step1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Step 1: main 메서드에서 유효성 검증 ===\n");

        // 1. 입력 받기
        System.out.print("아기사자 이름을 입력하세요 : ");
        String name = scanner.nextLine();

        System.out.print("전공을 입력하세요 : ");
        String major = scanner.nextLine();

        System.out.print("기수을 입력하세요 : ");
        int generation = scanner.nextInt();

        System.out.println();

        // 2. main 메서드에서 유효성 검증 수행
        boolean isVaild = true;

        System.out.println("입력값 검증을 진행합니다.");

        if(name == null || name.trim().isEmpty()){
            System.out.println("❌ 이름은 비어 있을 수 없습니다.");
            isVaild = false;
        }

        if(major == null || major.trim().isEmpty()){
            System.out.println("❌ 전공은 비어 있을 수 없습니다.");
            isVaild = false;
        }

        if(generation < 1){
            System.out.println("❌ 기수는 비어 있을 수 없습니다.");
            isVaild = false;
        }

        System.out.println();

        // 3. 검증 통과 시에만 객체 생성
        if(isVaild){
            System.out.println("아기사자 객체가 자신의 상태를 정상으로 판단했습니다.");

            Lion lion = new Lion(name, major, generation);
            lion.printInfo();
        } else{
            System.out.println("유효하지 않은 입력값으로 인해 객체를 생성하지 않습니다.\n");
        }

        scanner.close();
    }

}
