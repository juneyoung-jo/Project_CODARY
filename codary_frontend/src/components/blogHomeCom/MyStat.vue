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
import {mapState} from 'vuex'
import { userGraph } from '@/api/personal.js';

export default {
  name: 'MyStat',
  data() {
    return {
      result: [],
      labels: [],
      value: [], 
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
    userGraph(
      this.loggedInUserData,
      (response) => {
        // console.log(response)
        this.result = response.data[0]
        console.log(this.result);
        const temp=[];
        for(var key in this.result){
          temp.push(this.result[key]);
          //i++;
          //if(i==5) break;
        }
        this.value=temp;

        var tmp=new Array();
      // var j=0;
       for(var idx in this.result){
        // if(j==5) break;
       // if(j%28==0) {
          tmp.push(idx.substring(5,10));
       // }else{
       //   tmp.push(' ');
       // }
       // j++;
       }
       this.labels=tmp;
       
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