<template>
<div class='py-12 pb-0'>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
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
            job
          </h6>

          <h4 class="text-h4 mb-3 text--primary">
            name
          </h4>

          <p class="text--secondary">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ratione dolorem deserunt veniam tempora magnam quisquam quam error iusto cupiditate ducimus, et eligendi saepe voluptatibus assumenda similique temporibus placeat animi dicta?
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