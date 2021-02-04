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
          v-for="({ text, title }, i) in articles"
          :key="i"
          cols="12"
          md="4"
        >
          <h3
            class="font-weight-black mb-4 text-uppercase"
            v-text="title"
          ></h3>
          <div
            class="title font-weight-light mb-5"
            v-text="text"
          ></div>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
import { showMyMemo } from '@/api/personal.js';

export default {
  name:"MyMemo",
  data () {
    return {
      articles: [],
      userData: {
        blogId: 'QfIRyFO1HK5H',
        uId: '2u1wQOyL8StR',
        memoId: '2u1wQOyL8StR'
      }, 
    }
  },
  created(){
    showMyMemo(
      this.userData,
      (response) => {
        console.log(response)
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