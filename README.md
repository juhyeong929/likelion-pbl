likelion-pbl
작성자 : 김주형

Spring Boot 메인 클래스는 `com.likelion.likelionpbl.LikelionPblApplication`입니다.
`GET /hello` 요청을 보내면 `Hello, Spring Boot!`를 반환합니다.

멤버 API:

- `POST /members/lions`
- `POST /members/staffs`
- `GET /members/{name}`
- `PUT /members/lions/{name}`
- `PUT /members/staffs/{name}`
- `DELETE /members/{name}`

핵심 코드 파일:

- [src/main/java/com/likelion/pbl/controller/MemberController.java](src/main/java/com/likelion/pbl/controller/MemberController.java)
- [src/main/java/com/likelion/pbl/service/MemberService.java](src/main/java/com/likelion/pbl/service/MemberService.java)
- [src/main/java/com/likelion/pbl/repository/MemberRepository.java](src/main/java/com/likelion/pbl/repository/MemberRepository.java)
- [src/main/java/com/likelion/pbl/repository/MemoryMemberRepository.java](src/main/java/com/likelion/pbl/repository/MemoryMemberRepository.java)
- [src/main/java/com/likelion/pbl/dto](src/main/java/com/likelion/pbl/dto)
