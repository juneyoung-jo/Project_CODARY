import axios from 'axios'

const actions = {
  // 구글 토큰 localstorage에 저장
    googleCallback(context, data) {
        axios
          .post("http://localhost:8000/codary/user/login/google", data.uc.id_token)
          .then(response => {
            console.log(response.data.user)
            let token = data.uc.id_token
            let provider = response.data.user.provider
            localStorage.setItem('provider',provider)
            localStorage.setItem('access_token', token)
            context.dispatch("getMemberInfo")
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
          let provider = response.data.user.provider
          localStorage.setItem('provider',provider)
          localStorage.setItem('access_token', token)
          context.dispatch("getMemberInfo")
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 구글 유저 정보 가져오기
    getMemberInfo({ commit }){
      let token = localStorage.getItem("access_token")
      let provider = localStorage.getItem("provider")
      if (token) {
        if (provider==="google") {
          axios
              .post("http://localhost:8000/codary/user/login/google", token)
              .then(response=> {
                console.log(response.data.user)
                console.log(response)
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
                console.log('데이터 불러오기 실패');
                console.log(error);
              });
            console.log("success");
        } else if (provider==="Kakao"){
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
        }
      } 
    },
    // 로그아웃
    LOGOUT({ commit }) {
      commit("logout");
    },
  }
  export default actions