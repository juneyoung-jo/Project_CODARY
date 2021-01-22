import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home.vue'
import SearchPage from '../pages/SearchPage.vue'



Vue.use(Router) 
 
export default new Router({
  routes: [   
    { 
      path: '/',
      name: "Home",
      component: Home,
    },
    {
      path: '/searchpage',
      name: "SearchPage",
      component: SearchPage,
    }
 
  ]
})
