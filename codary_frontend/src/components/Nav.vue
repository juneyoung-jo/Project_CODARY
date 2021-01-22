<template>
  <div>
 
      <v-app-bar
        app
        color="white"
        height="100"
      >
        <v-avatar
          class="mr-3"
          color="grey lighten-5"
          size="70"
        >
          <v-img
            contain
            max-height="70%"
            src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          ></v-img>
        </v-avatar>

        <v-toolbar-title class="font-weight-black headline">
          VUETIFY
        </v-toolbar-title>
      </v-app-bar>

  </div>
</template>

<script>
export default {
  name: 'Nav',
}
</script>

<style>

</style>