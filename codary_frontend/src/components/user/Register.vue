<template v-slot:default="dialog">
  <v-card id='register'
    
  >
    
    <v-card-title>회원가입~</v-card-title>
    

    <v-card-actions class="justify-center" >
      <v-btn
        outlined
        @click="dialog.value = false"
      >
      <v-img src="../../assets/google.png" alt=""
      max-height="34"
      max-width="78"/>
      구글로 회원가입
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
      카카오톡으로 회원가입
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
      네이버로 회원가입
      </v-btn>
    </v-card-actions>

  </v-card>
</template>
     

<script>


export default {

  name: 'Register',
  methods: {
    change() {
      
      
    }
  }
}
</script>

<style>
  #register {
    background-image:url("../../assets/register.png")
  }
</style>