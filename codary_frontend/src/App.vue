<template>
  <div id="app">
    <v-app>
      <Nav />
      <v-main>
        <Memo />
        <router-view />
      </v-main>
    </v-app>
  </div>
</template>

<script>
import Memo from './components/Memo/Memo';
import Nav from './components/Nav';

export default {
  name: 'app',
  components: {
    Nav,
    Memo,
  },

  data() {
    return {};
    //
  },
};
</script>
<style>
#app {
  background-color: var(--v-background-base);
}
</style>
