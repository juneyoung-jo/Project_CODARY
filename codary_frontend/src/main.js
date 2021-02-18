import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import VueFullPage from 'vue-fullpage.js'
import axios from 'axios'


axios.defaults.baseURL = 'http://localhost:8000/codary/'


Vue.prototype.axios = axios // 사용법 => this.axios
Vue.config.productionTip = false
Vue.use(VueFullPage);

new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch('getMemberInfo')
  },
  vuetify,
  render: h=>h(App)
}).$mount('#app')
