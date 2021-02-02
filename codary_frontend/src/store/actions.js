import axios from 'axios'

const actions = {
    googleCallback(context, data) {
        axios
          .post("http://localhost:8000/codary/user/login/google", data.uc.id_token)
          .then(response=> {
            // console.log(response.data.user)
            console.log(response.data.userInfo)
            localStorage.setItem('jwt', response.data.access_token)
            localStorage.setItem('uid', response.data.user.uid)
            localStorage.setItem('blogId', response.data.user.blogId)
            localStorage.setItem('memoId', response.data.user.memoId)
            // localStorage.setItem('nickname', response.data.user.nickname)
            // localStorage.setItem('', response.data.user.)
            context.commit('fetchLoggedInUserData', response.data.user) 
            context.commit('fetchLoggedInUserProfile', response.data.userInfo)         
          })
          .catch(function (error) {
            console.log(error);
          });
        console.log("success");
      },
    LOGOUT({ commit }) {
        commit("logout");
    }
}
export default actions