<template>
<div>
  <v-spacer><hr></v-spacer>
  <h2 class='py-12'>커밋</h2>
  <v-sparkline
    :value="value"
    :gradient="gradient"
    :smooth="radius || false"
    :padding="padding"
    :line-width="width"
    :stroke-linecap="lineCap"
    :gradient-direction="gradientDirection"
    :fill="fill"
    :type="type"
    :auto-line-width="autoLineWidth"
    auto-draw
  ></v-sparkline>
</div>
</template>

<script>
import {mapState} from 'vuex'
import { showJandi } from '@/api/personal.js';

  const gradients = [
    ['#222'],
    ['#42b3f4'],
    ['red', 'orange', 'yellow'],
    ['purple', 'violet'],
    ['#00c6ff', '#F0F', '#FF0'],
    ['#f72047', '#ffd200', '#1feaea'],
  ]

export default {
  name: 'grass',
  data() {
    return {
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: 'round',
      gradient: gradients[5],
      value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradientDirection: 'top',
      gradients,
      fill: false,
      type: 'trend',
      autoLineWidth: false,
      articles: [],
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
    showJandi(
      this.loggedInUserData,
      (response) => {
        // console.log(response)
        this.articles = response.data
      },
      (err) => {
        console.log(err)
      }
    )        
  },
}
</script>


<style>

</style>