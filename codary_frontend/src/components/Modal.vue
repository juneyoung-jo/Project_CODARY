<template>
  <div>
    <Login/>
    <Register/>
  </div>
</template>

<script>
import Login from './user/Login'
import Register from './user/Register'

export default {


  name: 'Modal',
  components: {
    Login,
    Register,
    
  },
}
</script>

<style>

</style>
