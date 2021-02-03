const mutations = {
    fetchLoggedInUserData(state, userInfo){
        state.loggedInUserData = userInfo;
        state.isLogin = true;
    },
    logout(state) {
        state.loggedInUserData = null;
        state.isLogin = false;
        localStorage.removeItem("access_token");
    }
}


export default mutations