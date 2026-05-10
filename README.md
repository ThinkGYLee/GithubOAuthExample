# GithubOAuthExample 

이 프로젝트는 **Jetpack Compose** 환경에서 **GitHub OAuth** 인증 절차를 더 깔끔하게 처리하기 위해 **AuthTab**을 도입하여 리팩토링하는 과정을 담고 있습니다.

기존의 `CustomTab` 방식이 가진 **MainActivity 의존성**과 **생명주기 단절** 문제를 어떻게 해결했는지 코드와 로그를 통해 확인할 수 있습니다.

---

##  블로그 상세 설명

프로젝트의 상세한 배경과 기술적인 분석은 아래 벨로그 포스팅에서 확인하실 수 있습니다.
👉 **[벨로그: Github OAuth App 인증 절차를 Compose 에 맞게 리팩토링 해보기(AuthTab 을 사용하자)](https://velog.io/@gylee0311/Github-OAuth-App-%EC%9D%B8%EC%A6%9D-%EC%A0%88%EC%B0%A8%EB%A5%BC-Compose-%EC%97%90-%EB%A7%9E%EA%B2%8C-%EB%A6%AC%ED%8C%A9%ED%84%B0%EB%A7%81-%ED%95%B4%EB%B3%B4%EA%B8%B0AuthTab-%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%9E%90)**

---

##  주요 목표

* **Single Activity 구조 유지**: `MainActivity`의 코드 비대화를 방지하고 UI 단(Compose)에서 인증 로직 완결성 확보.
* **ActivityResult 활용**: `onNewIntent`를 통한 파편화된 데이터 수신 대신, `registerForActivityResult`를 통한 일관된 흐름 구현.
* **AuthTab 도입**: `CustomTab`의 한계를 극복하고 브라우저 기반 인증 결과를 `ActivityResult`로 직접 수신.

---

##  핵심 비교: CustomTab vs AuthTab

| 구분 | CustomTab (기존 방식) | AuthTab (리팩토링 방식) |
| --- | --- | --- |
| **결과 수신** | `onNewIntent` (별도의 경로) | `ActivityResultLauncher` (직접 수신) |
| **런처 반응** | `RESULT_CANCELED` 발생 | `RESULT_OK` 정상 반환 |
| **데이터 처리** | Activity 내 별도 파싱 로직 필요 | Compose 런처 콜백 내에서 처리 가능 |
| **흐름(Flow)** | **단절됨** (브라우저 실행 후 앱 재진입) | **연결됨** (하나의 비동기 흐름 유지) |

---

##  Tech Stack

* **Language**: Kotlin
* **UI**: Jetpack Compose
* **DI**: Hilt
* **Browser**: Android Browser Custom Tabs (AuthTab)

---

##  시작하기

### 1. GitHub OAuth 앱 설정

GitHub Developer settings에서 OAuth App을 생성하고 아래 정보를 준비합니다.

* **Client ID**
* **Redirect URI** (예: `test://github-auth`)

### 2. local.properties 설정

보안을 위해 Client ID와 Redirect URI는 `local.properties`에 정의하여 사용합니다.

```properties
CLIENT_ID="YOUR_GITHUB_CLIENT_ID"
REDIRECT_URI="YOUR_REDIRECT_URI"

```

### 3. Intent Filter 설정

`AndroidManifest.xml`에 설정한 Redirect URI의 스킴(Scheme)을 등록해야 합니다.

---