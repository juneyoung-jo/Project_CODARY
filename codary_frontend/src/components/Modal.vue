<template>
      <v-dialog
        transition="dialog-top-transition"
        max-width="600">
        <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
            >로그인하러가기</v-btn>
          </template>
        
        <v-tabs
          v-model="tab"
          background-color="indigo"
          color="red"
          grow
        >
          <v-tab>
            Login
          </v-tab>
          <v-tab>
            Register
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item>
              
            <Login/>  
          </v-tab-item>
          <v-tab-item>
             
            <Register/>
          </v-tab-item>
        </v-tabs-items>
      </v-dialog>
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
  data () {
      return {
        tab: null,
        items: [
          'Login', 'Register',
        ],
      }
    },
}
</script>

<style>

</style>
