import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import VueFullPage from 'vue-fullpage.js'
import axios from 'axios'


axios.defaults.baseURL = 'http://localhost:8000/codary/'
// axios.interceptors.request.use(config => {
//   const access_token = sessionStorage.getItem('access_token')
//   config.headers.common['Authorization'] = access_token ? `Bearer ${access_token}` : ''
  
//   return config
// })  
//후에 토큰으로 잠그고 열고 할때...

Vue.prototype.axios = axios // 사용법 => this.axios
Vue.config.productionTip = false
Vue.use(VueFullPage);



new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch('getMemberInfoGoogle')
  },
  vuetify,
  render: h=>h(App)
}).$mount('#app')
