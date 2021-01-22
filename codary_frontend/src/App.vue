<template>
  <!-- <div id="app"
  class="d-flex flex-row"> -->
  <v-app id="app"> 
      <Nav/>
      <Home/> 
  </v-app>
      <!-- <Memo/> -->
  <!-- </div> -->
  
</template>

<script>
import Nav from './components/Nav';
import Home from './pages/Home.vue';
// import Memo from './components/Memo';


export default {
  name: 'App',

  components: {
    Nav,
    Home,
    // Memo,
  },

  data: () => ({
    //
  }),
};
</script>
