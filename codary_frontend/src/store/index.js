import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({

    state:{
        loggedInUserData:{
            photo:'https://demos.creative-tim.com/vue-material-dashboard/img/marc.aba54d65.jpg', 
            job:'CE0 / FOUNDER' , 
            name:'John Leider', 
            intro:'Hi nice to meet you'
        }
    },
    getters:{
        LoggedInUserData(state) {
            return state.loggedInUserData
        }
    },
    mutations: {

    },
    actions: {

    },

}
);