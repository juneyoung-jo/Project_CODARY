<template>
  <v-app-bar color="background" app elevate-on-scroll class="pt-1">
    <!-- logo -->
    <router-link :to="'/'" class="noline">
      <h2>codary</h2>
    </router-link>
    <v-spacer></v-spacer>
      <!-- dark mode -->
    <v-row justify="center" class='ml-16 pl-10 pt-2'>
      <router-link class="noline" :to="'/searchpage'">
        <v-btn fab small class="mb-3 mr-3 p-0" color="primary">
          <span style="font-size:20px">
            <font-awesome-icon :icon="['fas', 'search']" />
          </span>
        </v-btn>
      </router-link>
    </v-row>
    <!-- buttons -->
    <v-row justify="end mb-2" class='mr-4'>
      <v-col cols="auto" class='mt-1 font-weight-medium'>
        Dark Mode
      </v-col>
      <v-col cols="auto" class='mt-1'>
        <v-switch v-model="$vuetify.theme.dark" class="ma-0 pa-0" color="secondary" hide-details />
      </v-col>
        <!-- search -->
      <v-col cols="auto">
        <template v-if="isLogin">
          <!-- 마이페이지가는곳 -->
          <MainBadge />
        </template>
        <template v-else>
          <!-- login -->
          <Modal />
        </template>
      </v-col>
    </v-row>
  </v-app-bar>
</template>

<script>
import Modal from './Modal';
import MainBadge from './MainBadge';
import { mapState } from 'vuex';
export default {
  components: {
    Modal: Modal,
    MainBadge: MainBadge,
  },
  name: 'Nav',
  computed: {
    ...mapState(['isLogin']),
  },
  watch: {
    color(val) {
      this.$vuetify.theme.themes[this.isDark ? 'dark' : 'light'].primary = val;
    },
  },
};
</script>

<style>
.noline {
  text-decoration: none !important;
}
h2 {
  margin-left: 30px;
}
</style>
