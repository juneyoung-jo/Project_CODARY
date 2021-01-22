<template>
  <!-- <div id="app"
  class="d-flex flex-row"> -->
  <v-app id="app"> 
      <Nav/>
      <router-view/>
  </v-app>
      <!-- <Memo/> -->
  <!-- </div> -->
  
</template>

<script>
import Nav from './components/Nav';
// import Memo from './components/Memo';


export default {
  name: 'App',

  components: {
    Nav,
    // Memo,
  },

  data: () => ({
    //
  }),
};
</script>
