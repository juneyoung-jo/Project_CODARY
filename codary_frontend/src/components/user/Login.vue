
<template v-slot:default="dialog">
  <v-card>
    <v-img
      alt="안보여요"
      src="../../assets/login.jpg"
      max-height="153"
      max-width="250"
    >
      <v-card-title>로그인~</v-card-title>
    </v-img>

    <v-card-actions class="justify-end">
      <v-btn
        text
        @click="dialog.value = false"
      >구글로 로그인</v-btn>
    </v-card-actions>

    <v-card-actions class="justify-end">
      <v-btn
        text
        @click="dialog.value = false"
      >카카오톡으로 로그인</v-btn>
    </v-card-actions>

    <v-card-actions class="justify-end">
      <v-btn
        text
        @click="dialog.value = false"
      >네이버로 로그인</v-btn>
    </v-card-actions>

  </v-card>
</template>
      


<script>


export default {

  name: 'Login',
  methods: {
    change() {
      return
      
    }
  }
}
</script>

<style>

</style>