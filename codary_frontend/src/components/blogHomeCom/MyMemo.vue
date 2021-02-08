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
          v-for="(article, index) in articles"
          :key="index"
          cols="12"
          md="4"
        >
          <h3
            class="font-weight-black mb-4 text-uppercase"
            
          >{{ article.memoContent }}</h3>
          <div
            class="title font-weight-light mb-5"
            
          > {{ article.memoTime }}</div>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
import {mapState} from 'vuex'
import { showMyMemo } from '@/api/personal.js';

export default {
  name:"MyMemo",
  data () {
    return {
      articles: [],
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },
  created(){
    showMyMemo(
      this.loggedInUserData,
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