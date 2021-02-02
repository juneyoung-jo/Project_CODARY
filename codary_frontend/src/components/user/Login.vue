<template v-slot:default="dialog">
  <v-card id="login">
    <div class="py-5"></div>
    <v-card-actions class="justify-center d-flex flex-column">
      <div>
        <KakaoLogin
          api-key="cc55fbaa2ba8ee734547019f8cba7abf"
          image="kakao_login_btn_large"
          :on-success="kakaoCallback"
          :on-failure="onFailure"
        />
      </div>
      <div>
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
    <div class="py-5"></div>
  </v-card>
</template>
      

<script>
import axios from "axios";
import KakaoLogin from "vue-kakao-login";
import GoogleLogin from "vue-google-login";
import {mapMutations, mapGetters} from 'vuex'
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
  data: function () {
    return {
      params: {
        client_id:
          "509903905678-vkgf01saln1b88fti0cd3vlivbkcd00g.apps.googleusercontent.com",
      },
      // only needed if you want to render the button with the google ui
      renderParams: {
        width: 250,
        height: 50,
        longtitle: true,
      },
    };
  },
  methods: {
    ...mapMutations(['fetchLoggedInUserData']),
    ...mapGetters(['LoggedInUserData']),

    kakaoCallback:(data) => {
      console.log(data);
      console.log(data.access_token);
      axios
        .post("http://localhost:8000/codary/user/login/kakao", data.access_token)
        .then(function (response) {
          console.log(response.data.access_token);
          localStorage.setItem('jwt', response.data.access_token)
          

        })
        .catch(function (error) {
          console.log(error);
        });
    },
    googleCallback: (data) => {
      console.log(data);
      console.log(data.uc.id_token);
      axios
        .post("http://localhost:8000/codary/user/login/google", data.uc.id_token)
        .then(response=> {
          localStorage.setItem('jwt', response.data.access_token)
          // this.$router.push({name:'MainPage'})
          // console.log(response.data)
          // console.log(response.user.uid)
          // console.log(response.user.blogId)
          // console.log(response.user.memoId)
          // const payload = {
          //   uid : response.data.
          // }
          // this.$store.commit('fetchLoggedInUserData',payload)
        })
        .catch(function (error) {
          console.log(error);
        });
      console.log("success");
    },
    onFailure,
    // callbackFunction,
  },
};
</script>

<style>
#login {
  background-image: linear-gradient(
    to right,
    rgba(255, 255, 255, 0),
    rgb(255, 255, 255)
  );
}
</style>