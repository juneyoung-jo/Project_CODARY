<template>
  <section id="blog">
    <v-container class='ma-10 pa-16'>
      <v-row>
        <v-col
          v-for="(article, src, index) in articles"
          :key="index"
          cols="12"
          md="4"
        >
        <v-avatar
          size="300"
        >
          <v-img
            src='https://images.unsplash.com/photo-1475938476802-32a7e851dad1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'
            class="mb-4"
          ></v-img>
        </v-avatar>
          
          <v-btn
            class="font-weight-black"
            text
          >
            {{article.blogTitle}}
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
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
      articles: [
      //  {src:'https://images.unsplash.com/photo-1475938476802-32a7e851dad1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80'}
      ],
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
        console.log(response)
        this.articles = response.data
        console.log(this.articles)
      },
      (err) => {
        console.log(err)
      }
    )  
    // 후에 블로거 각각의 인물로 요청을 보내서 사진이랑이런거 불러와야?
    }
  }
}
</script>

<style>

</style>