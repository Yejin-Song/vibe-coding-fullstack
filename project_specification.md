# 프로젝트 명세서 (Project Specification)

세련된 디자인과 페이징 기능을 갖춘 스프링부트(Spring Boot) 기반 게시판 애플리케이션 프로젝트에 대한 명세서입니다.

## 1. 프로젝트 기본 정보 (Project Metadata)

- **그룹 (Group):** `com.example`
- **아티팩트 (Artifact):** `vibeapp`
- **메인 클래스명 (Main Class Name):** `VibeApp`
- **설명 (Description):** 기능 중심 구조와 프리미엄 UI를 갖춘 Spring Boot 웹 애플리케이션.
- **언어 (Language):** Java

## 2. 개발 환경 및 기술 스택 (System Requirements)

- **JDK:** JDK 25 이상
- **Spring Boot:** 4.0.1 이상
- **빌드 도구 (Build Tool):** Gradle 9.3.0 이상 (Groovy DSL 사용)
- **템플릿 엔진:** Thymeleaf
- **스타일링:** Vanilla CSS (Tailwind CSS CDN 활용한 프리미엄 디자인)
- **설정 파일 (Configuration):** YAML (`application.yml`)

## 3. 의존성 및 플러그인 (Dependencies & Plugins)

### 플러그인 (Plugins)
- `org.springframework.boot` (Spring Boot 버전 4.0.1 이상)
- `io.spring.dependency-management`
- `java`

### 의존성 (Dependencies)
- `spring-boot-starter-web`: 웹 애플리케이션 구축을 위한 핵심 의존성
- `spring-boot-starter-thymeleaf`: 서버 사이드 템플릿 렌더링
- `spring-boot-starter-test`: 단위 및 통합 테스트 지원

## 4. 프로젝트 구조 (Feature-based Structure)

```text
vibe-coding-fullstack/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── vibeapp/
│   │   │               ├── VibeApp.java (설정 및 메인)
│   │   │               ├── home/ (홈 기능)
│   │   │               │   └── HomeController.java
│   │   │               └── post/ (게시글 기능)
│   │   │                   ├── Post.java (도메인)
│   │   │                   ├── PostController.java
│   │   │                   ├── PostRepository.java
│   │   │                   └── PostService.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── home/ (홈 템플릿)
│   │       │   │   └── home.html
│   │       │   └── post/ (게시글 템플릿)
│   │       │       ├── posts.html
│   │       │       ├── post_detail.html
│   │       │       ├── post_new_form.html
│   │       │       └── post_edit_form.html
│   │       └── application.yml
├── build.gradle
└── settings.gradle
```

## 5. 주요 구현 기능 (Key Features)

- **홈 화면**: 프로젝트 소개 및 게시판 이동 링크 제공.
- **게시글 CRUD**: 목록 조회, 상세 조회, 새 게시글 작성, 기존 게시글 수정 및 삭제 기능 완비.
- **페이징 처리**: 게시글 목록을 한 페이지당 5개씩 출력하며, 세련된 네비게이션 UI 제공.
- **국제화(한글화)**: 모든 UI와 문구를 한국어로 현지화하여 사용자 친화적 경험 제공.
- **패키지 최적화**: 기능형 구조(Feature-based)를 채택하여 유지보수성 향상.
- **코드 리팩토링**: 실무 관례를 준수하는 네이밍 컨벤션 적용 및 클린 코드 지향.

## 6. 빌드 및 실행

```bash
# 환경 변수 설정 (PowerShell 기준)
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25.0.2"

# 애플리케이션 실행
./gradlew bootRun
```
