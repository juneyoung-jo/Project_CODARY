<template>
      <v-dialog
        transition="dialog-top-transition"
        max-width="400">
        <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              plain
            >로그인</v-btn>
          </template>
        <v-tabs
          v-model="tab"
          background-color="indigo"
          color="white"
          grow
        >
          <v-tab>
            codary
          </v-tab>
        </v-tabs>
        <v-tabs-items 
          v-model="tab"
        >
          <v-tab-item>
            <Login/>  
          </v-tab-item>
        </v-tabs-items>
      </v-dialog>
</template>

<script>
import Login from './user/Login'

export default {


  name: 'Modal',
  components: {
    Login,
    
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
