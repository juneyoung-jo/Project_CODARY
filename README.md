# 카카오로그인

카카오 로그인은 카카오계정과 애플리케이션을 연결하고 토큰을 발급받아 카카오 API를 사용할 수 있도록 하는 기능입니다. 카카오 로그인을 이용함으로써 직접 서비스 ID 및 비밀번호를 입력하고 가입하는 번거로운 절차를 생략하고, 카카오 계정을 통해 사용자를 인증하고 권한을 부여할 수 있습니다.

카카오 로그인이 진행되는 과정을 간략히 표현하면 다음과 같습니다.

![2021-01-28 12;28;23](https://user-images.githubusercontent.com/60100901/106086058-bbc7f600-6164-11eb-9a8e-3b2cd9bff5b7.PNG)

카카오 로그인은 카카오계정의 사용자 자격정보로 인증 코드를 받아오고, 인증 코드로 액세스 토큰과 리프레시 토큰을 얻는 과정으로 구성되 있습니다. 액세스 토큰은 사용자를 인증하고 카카오 API 로 부터 사용자 정보를 권한을 부여받습니다.

아래는 실제 엑세스 토큰을 이용해 카카오 API로 부터 사용자 정보를 받아 온 응답입니다. 

![image](https://user-images.githubusercontent.com/60100901/106086628-dea6da00-6165-11eb-866a-a144d08a487e.png)

# JWT(JSON WEB TOKEN) 이란

JWT 는 JSON Web Token의 약자로 전자 서명 된 URL-safe (URL로 이용할 수있는 문자 만 구성된)의 JSON입니다. 서버와 클라이언트 간 정보를 주고 받을 때 Http 리퀘스트 헤더에 토큰을 넣은 후 서버는 별도의 인증 과정없이 헤더에 포함되어 있는 JWT 정보를 통해 인증합니다.

### JWT 토큰 구성

![image](https://user-images.githubusercontent.com/60100901/106087685-d64f9e80-6167-11eb-989f-c3462da40db8.png)

JWT는 header, payload, signature 세 파트로 나누어지며, Payload 부분에는 토큰에 담을 정보가 위치합니다.

아래는 실제 우리 서비스에서 생성된 access-token이며 Payload 부분에 회원 고유번호인 uId가 담겨있는걸 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/60100901/106087756-fda66b80-6167-11eb-9525-061e6eaaaffd.png)

