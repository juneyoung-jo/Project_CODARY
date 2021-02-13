<template>
<container class='py-12'>
    <div class="subtitle-2 text-center">
      <h4 v-if="!flag">아직 블로그에 방문한 사람이 없어요!<br>
        블로그를 홍보하세요~
      </h4>
    </div>
    <v-row>
      <v-col>
         <v-sheet
          class="v-sheet--offset mx-auto"
          elevation="1"
          max-width="calc(100% - 32px)"
        >
          <v-sparkline
            :label-size="2.4"
            :labels="labels"
            :value="value"
            line-width="2"
            padding="16"
          ></v-sparkline>
          <div class='py-2'></div>
          <v-text 
            color="primary" 
            class='font-weight-medium d-flex justify-center'
          >
            Number of visitors
          </v-text>
          <div class='py-4'></div>
        </v-sheet>
        
      </v-col>
    </v-row>
    <div class='py-16'></div>
</container>
  
</template>

<script>
import {mapState} from 'vuex'
import { userGraph } from '@/api/personal.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

export default {
  name: 'MyStat',
  data() {
    return {
      result: [],
      labels: [],
      value: [], 
      user: {
        user: '',
        blogId: '',
      },
      flag: true
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
     this.initUser();
      this.mystat();
  },
  methods:{
    initUser(){
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
     mystat(){
       userGraph(
      this.user.blogId,
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
        if(temp.length==0) this.flag=false;

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
        this.flag=false;
        console.log(err)
      }
    ) 
     }
  }
}     

</script>

<style>

</style>