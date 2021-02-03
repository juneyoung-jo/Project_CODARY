import axios from 'axios'

const actions = {
  // 구글 토큰 localstorage에 저장
    googleCallback(context, data) {
        axios
          .post("http://localhost:8000/codary/user/login/google", data.uc.id_token)
          .then(response => {
            console.log(response.data.user)
            let token = data.uc.id_token
            localStorage.setItem('access_token', token)
            context.dispatch("getMemberInfoGoogle")
          })
          .catch(function (error) {
            console.log(error);
          });
        console.log("success");
    },
    // 구글 유저 정보 가져오기
    getMemberInfoGoogle({ commit }){
      let token = localStorage.getItem("access_token")
      axios
          .post("http://localhost:8000/codary/user/login/google", token)
          .then(response=> {
            console.log(response.data.user)
            let userInfo = {
              uid: response.data.user.uid,
              memoId: response.data.user.memoId,
              blogId: response.data.user.blogId,
              nickname: response.data.user.nickname,
              profile: response.data.user.profile
            }
            commit('fetchLoggedInUserData', userInfo)        
          })
          .catch(function (error) {
            console.log(error);
          });
        console.log("success");
    },
     // 카카오 토큰 localstorage에 저장
    kakaoCallback(context,data) {
      axios
        .post("http://localhost:8000/codary/user/login/kakao", data.access_token)
        .then(function (response) {
          console.log(response.data.user.nickname)
          let token = data.access_token
          localStorage.setItem('access_token', token)
          context.dispatch("getMemberInfoKakao")
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 카카오 유저 정보 가져오기
    getMemberInfoKakao({ commit }){
      let token = localStorage.getItem("access_token")
      axios
      .post("http://localhost:8000/codary/user/login/kakao", token)
      .then(response=> {
        console.log(response.data.user)
        let userInfo = {
          uid: response.data.user.uid,
          memoId: response.data.user.memoId,
          blogId: response.data.user.blogId,
          nickname: response.data.user.nickname,
          profile: response.data.user.profile
        }
        commit('fetchLoggedInUserData', userInfo)        
      })
      .catch(function (error) {
        console.log(error);
      });
      console.log("success");  
    },
    LOGOUT({ commit }) {
        commit("logout");
    },
  }
  export default actions