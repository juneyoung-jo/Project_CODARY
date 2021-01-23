<template>
  <v-app id="app"> 
      <Nav/>
      <v-main>
        <v-container fluid>
          <!-- <Memo/>  -->
          <router-view/>
        </v-container>
      </v-main>
  </v-app>
  
</template>

<script>
// import Memo from './components/Memo.vue';
import Nav from './components/Nav';

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
