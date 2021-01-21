<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>메롱</h1>
    <Memo/>
    <Nav/>
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  </div>
</template>

<script>
import Memo from './components/Memo'
import Nav from './components/Nav'



export default {
  name: 'App',
  components: {
    Memo,
    Nav
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
