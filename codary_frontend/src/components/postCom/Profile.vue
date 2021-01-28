<template>
<div class='py-6 pa-8'>
  <v-container
    id="user-profile-view"
    fluid
    class='profile-back'
  >
    <v-row>
      <v-col
        cols="9"
        class="d-flex"
      >
        <v-img
          class="rounded-circle elevation-6"
          src="https://demos.creative-tim.com/vue-material-dashboard/img/marc.aba54d65.jpg"
          width="128"
          height="128"
        />
        <v-card-text>
          <h6 class="text-h6 mb-2 text--secondary">
            CEO / FOUNDER
          </h6>

          <h4 class="text-h4 mb-3 text--primary">
            chic nam
          </h4>
          <p class="text--secondary">
            개발자를 위한 블로그를 개발중인 개발자
          </p>
        </v-card-text>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>
export default {
  name: 'Profile'
}
</script>

<style>

</style>