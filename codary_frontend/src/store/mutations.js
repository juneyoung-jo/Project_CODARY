const mutations = {
    fetchLoggedInUserData(state, userInfo){
        state.loggedInUserData = userInfo;
        state.isLogin = true;
    },
    fetchLoggedInUserProfile(state, userProfile){
        state.loggedInUserProfile = userProfile;
    },
    logout(state) {
        state.userInfo = null;
        state.isLogin = false;
        localStorage.clear();
    }
}


export default mutations