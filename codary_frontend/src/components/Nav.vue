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
    <v-row justify='end' class='mt-0 mb-1'>
      <v-cols cols='auto'>
        <!-- search -->
        <router-link class='noline' :to="'/searchpage'">
          <v-btn plain>
            <font-awesome-icon :icon="['fas','search']"/>
          </v-btn>
        </router-link>
        <!-- login -->
        <Modal/>
        <router-link class='noline' :to="'/bloghome'">
          <v-btn
            plain
          >프로필</v-btn>
        </router-link>
      </v-cols>
    </v-row>
  </v-app-bar>
</template>

<script>
import Modal from './Modal'

export default {
  components: { 
    'Modal':Modal, 
  },
  name: 'Nav',
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