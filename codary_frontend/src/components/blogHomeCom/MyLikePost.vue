<template>
  <section id="blog">
    <v-container>
      <v-responsive
        class="mx-auto mb-12"
        width="56"
      >
      </v-responsive>
      <v-row>
        <v-col
          v-for="(article, idx) in articles"
          :key="idx"
          cols="12"
          md="4"
        >
          <h3
            class="font-weight-black mb-4 text-uppercase"
          >{{article.blogContentsTitle}}</h3>
          <div
            class="title font-weight-light mb-5"
          ></div>
          <v-btn
            class="ml-n4 font-weight-black"
            text
          >
            Continue Reading
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
import {mapState} from 'vuex'
import { showMyBlogContents } from '@/api/personal.js';
import {getuidCookie, getblogIdCookie} from '@/util/cookie.js';

export default {
  name:"MyLikePost",
  data () {
    return {
      articles: [], 
      user: {
        user:'',
        blogId: '',
      }
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
    this.initUser();
    this.mylikepost();
  },
  methods:{
     initUser(){
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    mylikepost(){
      showMyBlogContents(
      this.user.user,
      this.user.blogId,
      (response) => {
        // console.log(response)
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