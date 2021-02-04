<template>
  <v-app 
    id="app"
  > 
    <Nav/>
    <Memo/>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
  
</template>

<script>
import Memo from './components/Memo/Memo'
import Nav from './components/Nav';

export default {
  name: 'App',
  components: {
    Nav,
    Memo,
  },

  data: () => ({
    //
  }),
};
</script>
