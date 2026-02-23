# 프로젝트 명세서 (Project Specification)

최소 기능을 갖춘 스프링부트(Spring Boot) 기반 웹 애플리케이션 프로젝트에 대한 명세서입니다.

## 1. 프로젝트 기본 정보 (Project Metadata)

- **그룹 (Group):** `com.example`
- **아티팩트 (Artifact):** `vibeapp`
- **메인 클래스명 (Main Class Name):** `VibeApp`
- **설명 (Description):** 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- **언어 (Language):** Java

## 2. 개발 환경 및 기술 스택 (System Requirements)

- **JDK:** JDK 25 이상
- **Spring Boot:** 4.0.1 이상
- **빌드 도구 (Build Tool):** Gradle 9.3.0 이상 (Groovy DSL 사용)
- **설정 파일 (Configuration):** YAML (`application.yml`)

## 3. 의존성 및 플러그인 (Dependencies & Plugins)

### 플러그인 (Plugins)
- `org.springframework.boot` (Spring Boot 버전 4.0.1 이상)
- `io.spring.dependency-management`: Spring Boot 버전에 맞춰서 플러그인 추가
- `java`

### 의존성 (Dependencies)
- **최소 기능 프로젝트:** 초기 단계에서는 별도의 외부 의존성을 추가하지 않음 (Standard Spring Boot Starter only).

## 4. 프로젝트 구조 (Proposed Structure)

```text
vibeapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── vibeapp/
│   │   │               └── VibeApp.java
│   │   └── resources/
│   │       └── application.yml
├── build.gradle
└── settings.gradle
```

## 5. 주요 설정 사항 (Key Configurations)

### `build.gradle` (예시)
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '4.0.1'
    id 'io.spring.dependency-management' version '1.1.4' // 적정 버전 자동 관리
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### `application.yml`
```yaml
spring:
  application:
    name: vibeapp
```
