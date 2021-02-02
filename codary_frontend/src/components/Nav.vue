<template>
  <v-app-bar
    app
   
    elevate-on-scroll
  >
    <!-- logo -->
    <router-link :to="'/'" class='noline'>
      <h2 style="color:black">codary</h2>
    </router-link>
    <v-spacer></v-spacer>
    <v-row
      justify="center"
    >
    <!-- dark mode -->
      <v-col cols="auto">
        Dark Mode
      </v-col>
      <v-col cols="auto">
        <v-switch
          v-model="$vuetify.theme.dark"
          class="ma-0 pa-0"
          color="secondary"
          hide-details
        />
      </v-col>
    </v-row>
    <!-- buttons -->
    <v-row justify="end mb-2 mr-2">
      <v-col cols="auto">
        <!-- search -->
        <router-link class='noline' :to="'/searchpage'">
          <v-btn plain>
            <font-awesome-icon :icon="['fas','search']"/>
          </v-btn>
        </router-link>
        <template v-if="isLogin">
          <!-- 마이페이지가는곳 -->
          <MainBadge/>
        </template>
        <template v-else>
          <!-- login -->
          <Modal/>
        </template>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
import Modal from './Modal'
import MainBadge from './MainBadge'
import {mapState} from 'vuex';
export default {
  components: { 
    'Modal':Modal,
    'MainBadge':MainBadge 
  },
  name: 'Nav',
  computed:{
    ...mapState(['isLogin'])
  },
  watch: {
      color (val) {
        this.$vuetify.theme.themes[this.isDark ? 'dark' : 'light'].primary = val
      },
    },
}
</script>

<style>
.noline{
  color:black;
  text-decoration: none !important;
}
h2{
  margin-left: 30px;
}
</style>