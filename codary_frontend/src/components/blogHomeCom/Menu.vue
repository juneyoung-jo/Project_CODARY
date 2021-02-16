<template>
<v-sheet color='background' class='py-12 tabmenu' v-if="this.isMe" id="menu">
 <v-tabs background-color='background' grow>
    <v-tab v-for="tab in tabs" :key="tab.id" @click="updateRouter(tab.route)">
      {{ tab.name }}
    </v-tab>
</v-tabs>
</v-sheet>
<div class='py-12' v-else id="menu">
 <v-tabs background-color='background' grow>
    <v-tab v-for="tab in tabs2" :key="tab.id" >
      {{ tab.name }}
    </v-tab>
</v-tabs>
</div>
</template>


<script>
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

  export default {
    data () {
      return {
        activeTab: '',
        tabs: [
            {id:1, name:'글', route: { name:'MyPost' }},
            {id:2, name:'메모보기', route: { name:'MyMemo' }},
            {id:3, name:'즐겨찾기', route: { name:'MyLikePost' }},
            {id:4, name:'팔로우', route: { name:'MySubscriber' }},
            {id:5, name:'유저통계', route: { name:'MyStat' }}, 
        ], 
        tabs2: [
            {id:1, name:'글', route: { name:'MyPost' }},
        ],
        isMe: false,
        user:{
          user: '',
          blogId: '',
        },
      }
    },
    watch: {
      $route:function () {
        this.initUser();
      }
    },
    created(){
      this.initUser();
    },
    methods: {
      initUser(){
       this.user.user = getuidCookie();
       this.user.blogId = getblogIdCookie();    
     
        if(this.user.blogId===this.$route.query.blogId || typeof this.$route.query.blogId==='undefined'){
        //나야 
          this.isMe=true;
          this.user.blogId=getblogIdCookie();
        }else{
          this.isMe=false;
        }
      },
      updateRouter(val){
       // this.$router.push(val)
        this.$router.push(val, () => {})
      },

    }
  }
</script>
<style>

</style>