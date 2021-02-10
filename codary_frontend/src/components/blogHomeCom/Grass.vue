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
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

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
      user: {
        user:'',
        blogId: '',
      }
    }
  },
  created(){
    this.initUser();
    this.jandi(); 
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  methods: {
    initUser(){
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    jandi(){
      console.log('잔디실행');
      showJandi(
      this.user.blogId,
      (response) => {
        this.result = response.data[0]
        const temp=[];
        
         for(var key in this.result){
          temp.push(this.result[key]);
        }
        this.value=temp;

       var tmp=new Array();
       var j=0;
       for(var idx in this.result){
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
      );
      
    }  
  }
}
</script>


<style>

</style>