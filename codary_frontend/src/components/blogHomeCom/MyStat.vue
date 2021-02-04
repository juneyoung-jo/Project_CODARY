<template>
<div class='py-12'>
    <strong>블로그 방문자 수</strong>
    <div class='py-12'></div>
    <v-row>
      <v-col>
         <v-sheet
          class="v-sheet--offset mx-auto"
          elevation="12"
          max-width="calc(100% - 32px)"
        >
          <v-sparkline
            :labels="labels"
            :value="value"
            line-width="2"
            padding="16"
          ></v-sparkline>
        </v-sheet>
        
      </v-col>
    </v-row>
    <div class='py-16'></div>
</div>
  
</template>

<script>
import { userGraph } from '@/api/personal.js';

export default {
  name: 'MyStat',
  data() {
    return {
      labels: [
        '1/15',
      '1/17',
      '1/19',
      '1/21',
      '1/23',
      '1/25',
      '1/27',
      '1/29',
      ],
      value: [
        200,
        675,
        410,
        390,
        310,
        460,
        250,
        240,
      ],
      articles: [],
      userData: {
        blogId: 'QfIRyFO1HK5H',
        uId: '2u1wQOyL8StR',
        memoId: '2u1wQOyL8StR'
      }, 
    }
  },
  created(){
    userGraph(
      this.userData,
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