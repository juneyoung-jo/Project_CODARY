<template>
  <v-app-bar
    app
    absolute
    color="white"
  >
    <router-link :to="'/'" class='codary'>
      <h2 style="color:black">codary</h2>
    </router-link>

    <v-spacer></v-spacer>

    <router-link :to="'/searchpage'">
      <v-btn>
        <font-awesome-icon :icon="['fas','search']"/>
      </v-btn>
    </router-link>

    <router-link :to="'/bloghome'">
      <v-btn>
        블로그홈
      </v-btn>
    </router-link>

    <router-link :to="'/createpost'">
      <v-btn>
        글 작성
      </v-btn>
    </router-link>

    <v-btn>login</v-btn>

  </v-app-bar>
</template>

<script>
export default {
  name: 'Nav',
}
</script>

<style>
.codary{
  color:black;
  text-decoration: none !important;
}




</style>