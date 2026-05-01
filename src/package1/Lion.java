package package1;

public class Lion {
    public String name;
    String major;
    private int generation;

    public Lion(String name, String major, int generation){
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    //Step 2를 위한 유효성 검증 메서드
    public boolean validate(){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        if(major == null || major.trim().isEmpty()){
            return false;
        }
        if(generation < 1){
            return false;
        }
        return true;
    }

    //정보 출력
    public void printInfo(){
        System.out.println("\n아기사자 정보를 출력합니다.");
        System.out.println("이름 : " +name+ "|");
        System.out.println("전공 : " +major+ "|");
        System.out.println("기수 : " +generation+ "|");
    }


}
