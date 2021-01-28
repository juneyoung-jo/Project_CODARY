
<template v-slot:default="dialog">
  <v-card id='login'
    
  >
    
    <v-card-title>로그인~</v-card-title>
    

    <v-card-actions class="justify-center" >
      <v-btn
        outlined
        
        @click="dialog.value = false"
      >
      <v-img src="../../assets/google.png" alt=""
      max-height="34"
      max-width="78"/>
      구글로 로그인
      </v-btn>
    </v-card-actions>

    <v-card-actions class="justify-center" >
      <v-btn
        outlined
        src="../../assets/kakao.png"
        @click="dialog.value = false"
      >
      <v-img src="../../assets/kakao.png" alt=""
      max-height="34"
      max-width="78"/>
      카카오톡으로 로그인
      </v-btn>
    </v-card-actions>

    <v-card-actions class="justify-center" >
      <v-btn
        outlined
        
        @click="dialog.value = false"
      >
      <v-img src="../../assets/naver.png" alt=""
      max-height="34"
      max-width="78"/>
      네이버로 로그인
      </v-btn>
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
  #login {
    background-image:
      linear-gradient(to right, rgba(255, 255, 255, 0), rgb(255, 255, 255)),
      url("../../assets/login.jpg")
  }
</style>