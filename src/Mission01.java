import java.util.Scanner;

public class Mission01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count;
        while (true) {
            System.out.print("저장할 아기사자 수를 5 이상 입력해주세요. ");
            count = sc.nextInt();
            if (count >= 5) {
                break;
            }
            System.out.println("[오류] 5 이상 입력해주세요.");
        }

        String[] lions = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 아기사자 이름을 입력하세요: ");
            lions[i] = sc.next();
        }

        System.out.println("\n=== 아기사자 명단 ===");
        for (int i = 0; i < lions.length; i++) {
            System.out.println((i + 1) + ". " + lions[i]);
        }
        System.out.println("총 " + count + "마리의 아기사자가 등록되었습니다.");

        sc.close();
    }
}