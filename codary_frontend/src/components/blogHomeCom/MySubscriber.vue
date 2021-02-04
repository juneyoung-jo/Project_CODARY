<template>
  <section id="blog">
    <v-container class='ma-10 pa-16'>
      <v-row>
        <v-col
          v-for="(article, index) in articles"
          :key="index"
          cols="12"
          md="4"
        >
        <v-avatar
          size="300"
        >
          <v-img
            :src="src"
            class="mb-4"
          ></v-img>
        </v-avatar>
          
          <v-btn
            class="font-weight-black"
            text
          >
            blog
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
import { showMyBloger } from '@/api/personal.js';

export default {
  name:"PopularTag",
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

  created() {
    showMyBloger(
      this.userData,
      (response) => {
        console.log(response)
        this.articles = response.data
      },
      (err) => {
        console.log(err)
      }
    )  
    // 후에 블로거 각각의 인물로 요청을 보내서 사진이랑이런거 불러와야?
  }
}
</script>

<style>

</style>