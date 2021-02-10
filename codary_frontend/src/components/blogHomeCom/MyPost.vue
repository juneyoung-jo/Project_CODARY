<template>
  <div class='py-12'>
    <h2>
      Choose tags
    </h2>
    <v-card-text>
      <v-chip-group
        column
        multiple
      >
        <v-chip
          filter
          outlined
        >
          Elevator
        </v-chip>
        <v-chip
          filter
          outlined
        >
          Washer / Dryer
        </v-chip>
        <v-chip
          filter
          outlined
        >
          Fireplace
        </v-chip>
        <v-chip
          filter
          outlined
        >
          Wheelchair access
        </v-chip>
        <v-chip
          filter
          outlined
        >
          Dogs ok
        </v-chip>
        <v-chip
          filter
          outlined
        >
          Cats ok
        </v-chip>
      </v-chip-group>
    </v-card-text>

    <v-container>
      <v-responsive
        class="mx-auto mb-12"
        width="56"
      >
        <v-divider class="mb-1"></v-divider>
        <v-divider></v-divider>
      </v-responsive>
      <v-div class="subtitle-2 text-center">
        <h4 v-if="this.articles.length==0">작성한 글이 없어요!</h4>
      </v-div>
      <v-row>
        <v-col
          v-for="(article, blogId, blogContentsId) in articles"
          :key="blogContentsId"
          cols="12"
          md="4"
        >
          <v-img
            :src=article.blogContentsCover
            class="mb-4"
            height="275"
            max-width="100%"
          ></v-img>
          <h3
            class="font-weight-black mb-4 text-uppercase"
          >{{article.blogContentsTitle}}</h3>
          <div
            class="title font-weight-light mb-5"
            
          ></div>
          <router-link :to="{
              name: 'ViewPost',
              query: {
                blogId: article.blogId,
                blogContentsId: article.blogContentsId,
              },
            }"
             class='noline'>
            <v-btn
              class="ml-n4 font-weight-black"
              text
            >
              Continue Reading
            </v-btn>
          </router-link>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import { personalList } from '@/api/personal.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

export default {
  name: 'MyPost',
  
  data () {
    return {
      articles: [], 
      user:{
        user: '',
        blogId: '',
      }
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
      this.initUser();
      this.mypost();
  },
  methods:{
     initUser(){
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    mypost(){
       personalList(
      this.user.blogId,
      (response) => {
         console.log(response)
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