import { deleteCookie } from '@/util/cookie.js'

const mutations = {
    fetchLoggedInUserData(state, userInfo){
        state.loggedInUserData = userInfo;
        state.isLogin = true;
    },
    logout(state) {
        state.loggedInUserData = null;
        state.isLogin = false;
        localStorage.clear()
        deleteCookie();
    }
}


export default mutations