<template>
  <v-app-bar
    app
    absolute
  >
    <router-link :to="'/'" class='noline'>
      <h2 style="color:black">codary</h2>
    </router-link>
    <v-spacer></v-spacer>
    <v-row
    >
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

    <router-link class='noline' :to="'/searchpage'">
      <v-btn>
        <font-awesome-icon :icon="['fas','search']"/>
      </v-btn>
    </router-link>

    <router-link class='noline' :to="'/bloghome'">
      <v-btn>
        블로그홈
      </v-btn>
    </router-link>

    <router-link class='noline' :to="'/createpost'">
      <v-btn>
        글 작성
      </v-btn>
    </router-link>

    <Modal/>
    

  </v-app-bar>
</template>

<script>
import Modal from './Modal.vue'

export default {
  components: { Modal },
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




</style>