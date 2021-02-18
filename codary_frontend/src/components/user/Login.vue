<template v-slot:default="dialog">
  <v-card id="login">
    
    <v-card-actions class="justify-center d-flex flex-column align-center" style="height:100%">

      <div class="loginBar kakaoLoginBar">
        <KakaoLogin
          api-key="cc55fbaa2ba8ee734547019f8cba7abf"
          :on-success="kakaoCallback"
          :on-failure="onFailure"  
        />
          <!-- :image="kakao_login_large_narrow" -->
          <!-- src="@/assets/kakao_login/kakao_login_large_narrow.png" -->
      </div>
      
      <div class="loginBar">
        <GoogleLogin
          :params="params"
          :renderParams="renderParams"
          :onSuccess="googleCallback"
          :onFailure="onFailure"
          
        ></GoogleLogin>
      </div>
      <!-- <div>
        <NaverLogin
          client-id="WpeeiuJ12wUmEB1bn6hr"
          callback-url="http://localhost:8080"
          v-bind:is-popup="false"
          v-bind:button-type="3"
          v-bind:button-height="50"
          button-color="green"
          :callbackFunction="callbackFunction"
        />
      </div> -->
    </v-card-actions>
    
  </v-card>
</template>
      

<script>
import KakaoLogin from "vue-kakao-login";
import GoogleLogin from "vue-google-login";
import {mapMutations, mapState} from 'vuex'
// import NaverLogin from "vue-naver-login";


let onFailure = (data) => {
    console.log(data);
    console.log("failure");
  };


export default {
  name: "Login",
  components: {
    KakaoLogin,
    GoogleLogin,
    // NaverLogin,
  },
  data() {
    return {
      params: {
        client_id:
          "509903905678-vkgf01saln1b88fti0cd3vlivbkcd00g.apps.googleusercontent.com",
      },
      // only needed if you want to render the button with the google ui
      renderParams: {
        width: 300,
        height: 49,
        longtitle: true,
      },
    };
  },
  methods: {
    ...mapMutations(['fetchLoggedInUserData']),
    ...mapState(['loggedInUserData']),

    kakaoCallback(data){
      // console.log(data)
      this.$store.dispatch('kakaoCallback',data)
       if (this.$route.path !== '/searchpage') this.$router.replace('/searchpage');
    },
    googleCallback(data){
      // console.log(data)
      this.$store.dispatch('googleCallback',data)
      if (this.$route.path !== '/searchpage') this.$router.replace('/searchpage');
    },
    onFailure,
    // callbackFunction,
  },
};
</script>

<style>
#login {
  /* background-image: linear-gradient(
    to right,
    rgba(255, 255, 255, 0),
    rgb(255, 255, 255)
  ); */
  height: 300px;
  
}

.loginBar {
  cursor: pointer;
  width: 300px;
  height: 49px;
  margin: 25px;
}

/* .kakaoLoginBar :hover {
   border:1px solid #999999;
} */

</style>