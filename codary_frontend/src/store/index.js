import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({

    state:{
        photo:'https://demos.creative-tim.com/vue-material-dashboard/img/marc.aba54d65.jpg', 
        job:'CEO / FOUNDER' , 
        name:'chic nam', 
        intro:'개발자를 위한 블로그를 개발중인 개발자',
        mycover: 'https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg',
    },
    getters:{
    },
    mutations: {

    },
    actions: {

    },

}
);