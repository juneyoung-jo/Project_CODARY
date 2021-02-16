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
* {
  font-family: 'Poppins', 'Nanumsquare','Noto Sans KR', sans-serif;
}
#app {
  background-color: var(--v-background-base);
}


::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  background: #ffffff;
}
::-webkit-scrollbar-thumb {
  border-radius: 3.5px;
  background-color: #ced4da;
}

::-webkit-scrollbar-thumb:hover {
  background-color: #adb5bd;
}
::-webkit-scrollbar-track {
  background: #ffffff;
}

</style>
