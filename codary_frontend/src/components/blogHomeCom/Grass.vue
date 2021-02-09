<template>
<div>
  <v-spacer><hr></v-spacer>
  <h2 class='py-12'>커밋</h2>
  <v-sparkline
    :value="value"
    :label-size="4"
   
    :labels="labels"
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
      result: [],
      labels: [],
     // showlabels:[],
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: 'round',
      gradient: gradients[5],
      value: [],
      gradientDirection: 'top',
      gradients,
      fill: false,
      type: 'trend',
      autoLineWidth: false,
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
    showJandi(
      this.loggedInUserData,
      (response) => {
        this.result = response.data[0]
        console.log(this.result)
        const temp=[];
        //var i=0;
         for(var key in this.result){
          temp.push(this.result[key]);
          //i++;
          //if(i==5) break;
        }
        this.value=temp;

       var tmp=new Array();
       var j=0;
       for(var idx in this.result){
        // if(j==5) break;
        if(j%30==0) {
          tmp.push(idx.substring(0,7));
        }else{
          tmp.push(' ');
        }
        j++;
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