
<template v-slot:default="dialog">
  <v-card id='login' >
    <div class='py-5'></div>
    <v-card-actions class="justify-center d-flex flex-column">
      <a
      href="https://kauth.kakao.com/oauth/authorize?client_id=cc55fbaa2ba8ee734547019f8cba7abf&redirect_uri=http://localhost:8000/codary/login/kakao&response_type=code"
      >
      <img
        class="kakao_btn"
        src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
        width="222"
        @click="loginWithKakao"
      /> 
      </a>
      <a
        href="https://nid.naver.com/oauth2.0/authorize?client_id=WpeeiuJ12wUmEB1bn6hr&redirect_uri=http://localhost:8000/codary/login/naver&response_type=code"
      >
        <img
          class="kakao_btn"
          src="../../assets/naverlogin.png"
          width="222"
        />
      </a>
    </v-card-actions>
    <div class='py-5'></div>
  </v-card>
</template>

<script>

export default {

  name: 'Login',
  methods: {
    loginWithKakao: function () {
      console.log("카카오 클릭");
      const params = {
        redirectUri: "http://localhost:8080/auth",
      };
      window.Kakao.Auth.authorize(params);
    },
  }
}
</script>

<style>
  #login {
    background-image:
      linear-gradient(to right, rgba(255, 255, 255, 0), rgb(255, 255, 255)),

  }
</style>