<template>
  <section id="blog">
    <v-container>
      <v-responsive
        class="mx-auto mb-12"
        width="56"
      >
      </v-responsive>
      <div class="subtitle-2 text-center">
        <h4 v-if="this.articles.length==0">팔로잉한 유저가 없어요!</h4>
      </div>
      <v-row>
        <v-col
          v-for="(article, src, index) in articles"
          :key="index"
          cols="12"
          md="4"
          class='d-flex flex-column align-center'
        >
        <v-avatar
          size="160"
        >
          <v-img
            :src="article.profile"
            class="mb-4"
          ></v-img>
        </v-avatar>
          
          <v-btn
            class="font-weight-black"
            text
          >
          <router-link :close-on-content-click="false" class="noline pa-5" 
          :to="{
            name: 'BlogHome', 
            query: { 
              blogId: article.blog_id
              }
            }">
            {{article.nickname}}
          </router-link>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-16"></div>
    <div class='py-16'></div>
  </section>
</template>

<script>
import { mapState } from 'vuex'
import { showMyBloger } from '@/api/personal.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

export default {
  name:"PopularTag",
  data () {
    return {
      articles: [],
      user: {
        user:'',
        blogId:'',
      }
    }
  }, 

  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created() {
    this.initUser();
    this.mysubscriber();
  },
  methods:{
    initUser(){
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    mysubscriber(){
      showMyBloger(
      this.user.blogId,
      this.user.user,
      (response) => {
        //console.log(response)
        this.articles = response.data
      },
      (err) => {
        console.log(err)
      }
    )  
    }
  }
}
</script>

<style>

</style>