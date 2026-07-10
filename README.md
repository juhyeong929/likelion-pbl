# likelion-pbl

멋쟁이사자처럼 PBL 백엔드 프로젝트입니다. Member(멤버)와 Assignment(과제)를 관리하는 REST API를 제공하며, JPA·MySQL·전역 예외 처리를 적용했습니다.

## 기술 스택

| 구분 | 기술 | 버전 |
|------|------|------|
| Language | Java | 17+ |
| Framework | Spring Boot | 3.4.5 |
| ORM | Spring Data JPA (Hibernate) | 6.x |
| Database | MySQL | 8.x |
| Build | Gradle | - |

## 실행 방법

### 1. 사전 준비

- JDK 17 이상
- MySQL 8.x

### 2. 데이터베이스 생성

```sql
CREATE DATABASE likelion_pbl;
```

### 3. DB 연결 설정

`src/main/resources/application.properties`에서 계정 정보를 환경에 맞게 수정합니다.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/likelion_pbl?serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=1234
```

### 4. 애플리케이션 실행

IntelliJ에서 `LikelionPblApplication`을 실행하거나, Gradle이 설치된 환경에서 아래 명령을 사용합니다.

```bash
./gradlew bootRun
```

### 5. 접속

- 프론트엔드 UI: [http://localhost:8080](http://localhost:8080)
- 헬스 체크: `GET http://localhost:8080/hello`

## API 목록

### Member API

| HTTP 메서드 | URI | 설명 |
|-------------|-----|------|
| POST | `/members/lions` | Lion(아기사자) 등록 |
| POST | `/members/staffs` | Staff(운영진) 등록 |
| GET | `/members` | 전체 멤버 조회 |
| GET | `/members?part={part}` | 파트별 멤버 필터링 |
| GET | `/members/{id}` | 멤버 단건 조회 |
| PUT | `/members/lions/{id}` | Lion 수정 |
| PUT | `/members/staffs/{id}` | Staff 수정 |
| DELETE | `/members/{id}` | 멤버 삭제 |

### Assignment API

| HTTP 메서드 | URI | 설명 |
|-------------|-----|------|
| POST | `/members/{memberId}/assignments` | 과제 등록 |
| GET | `/assignments` | 전체 과제 조회 |
| GET | `/members/{memberId}/assignments` | 멤버별 과제 목록 조회 |
| GET | `/assignments/search?keyword=` | 과제 제목 검색 |
| GET | `/assignments/{id}` | 과제 단건 조회 |
| PUT | `/assignments/{id}` | 과제 수정 |
| DELETE | `/assignments/{id}` | 과제 삭제 |

### 에러 응답 형식

모든 비즈니스 예외는 아래 JSON 형식으로 반환됩니다.

```json
{
  "status": 404,
  "message": "과제를 찾을 수 없습니다. id=999"
}
```

| 예외 | HTTP 상태 |
|------|-----------|
| `MemberNotFoundException` | 404 Not Found |
| `AssignmentNotFoundException` | 404 Not Found |
| `DuplicateMemberException` | 409 Conflict |

## 프로젝트 구조

```
src/main/java/com/likelion/likelionpbl/
├── LikelionPblApplication.java
├── member/
│   ├── controller/     # Member REST API
│   ├── service/        # Member 비즈니스 로직 (@Transactional)
│   ├── repository/     # Member JPA Repository
│   ├── domain/         # Member 엔티티, RoleType
│   └── dto/            # 요청/응답 DTO
├── assignment/
│   ├── controller/     # Assignment REST API
│   ├── service/        # Assignment 비즈니스 로직 (@Transactional)
│   ├── repository/     # Assignment JPA Repository
│   ├── domain/         # Assignment 엔티티 (@ManyToOne)
│   └── dto/            # 요청/응답 DTO
└── global/
    ├── exception/      # 커스텀 예외, @RestControllerAdvice
    └── dto/            # ErrorResponse

src/main/resources/
├── application.properties
└── static/             # 프론트엔드 (index.html, css, js)
```

## 프론트엔드

`src/main/resources/static/`에 멤버/과제 관리 UI가 포함되어 있습니다.

- **멤버 관리 탭**: 등록, 조회, 파트별 필터, 수정, 삭제
- **과제 관리 탭**: 등록, 전체/멤버별 조회, 제목 검색, 수정, 삭제
- **HTTP 통신 로그**: 화면 하단에서 요청/응답 실시간 확인
- **토스트 알림**: 에러 발생 시 `ErrorResponse.message` 표시

## 작성자

김주형
