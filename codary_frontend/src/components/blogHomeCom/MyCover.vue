<template>
  <v-img 
    :src="mycover"
    max-height="300"
    top
  >
  </v-img>
</template>

<script>

import {mapState} from 'vuex'
  export default {
    name: 'MyCover',
    computed:{
      ...mapState(['mycover'])
    }
    
    }
</script>

<style>

</style>