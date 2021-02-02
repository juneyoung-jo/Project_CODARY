import {Authentication} from './state.js'

const mutations = {
    fetchLoggedInUserData(state){
        state.loggedInUserData = Authentication.fetchUserData()
    }
}


export default mutations