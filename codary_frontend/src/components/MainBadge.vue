<template>
  <v-menu
    v-model="menu"
    :close-on-content-click="true"
  >
    <template v-slot:activator="{ on, attrs }">
    
        <v-badge
            bordered
            overlap
            content="4"
            class='pb-2'
            offset-x="10"
            offset-y="20"
        >
        <span style="font-size: 35px">
          <font-awesome-icon 
            :icon="['fas', 'user-circle']"
            v-bind="attrs"
            v-on="on"
          />
        </span>
        </v-badge>
 

    </template>

    <v-card>
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
            <img
              :src=this.loggedInUserData.profile
              alt="John"
            >
          </v-list-item-avatar>
          <router-link :close-on-content-click="false" class='noline pa-5' :to="'/bloghome'">
          <v-list-item-content>
            <v-list-item-title>{{this.loggedInUserData.nickname}}</v-list-item-title>
            <v-list-item-subtitle>my blog home</v-list-item-subtitle>
          </v-list-item-content>
          </router-link>
          <v-list-item-action>
            <v-btn
              color="primary"
              @click.prevent='logout'
            >
              LOGOUT
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-card>
  </v-menu>
</template>

<script>
import {mapState} from 'vuex';

  export default {
    name:'MainBadge',
    computed: {
      ...mapState(['loggedInUserData','userInfo'])
    },
    data: () => ({
      fav: true,
      menu: false,
      message: false,
      hints: true,
    }),
    methods: {
      logout() {
        this.$store
        .dispatch('LOGOUT')
        .then(()=>{
          if (this.$route.path !== '/') this.$router.replace('/');
        })
        .catch(() => {
          console.log('로그아웃 문제!');
        });
      }
    }
  }
</script>

<style>

</style>