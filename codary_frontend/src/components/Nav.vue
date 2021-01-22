<template>

      <v-app-bar
        app
        absolute
        color="white"
      >

        <v-toolbar-title class="font-weight-bold">
          CODARY
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn>
          <font-awesome-icon :icon="['fas','search']"/>
        </v-btn>

        <v-btn>login</v-btn>

      </v-app-bar>



</template>

<script>
export default {
  name: 'Nav',
}
</script>

<style>

</style>