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
      <v-row>
        <v-col
          v-for="(article, idx) in articles"
          :key="idx"
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
          <router-link :to="'/viewpost'" class='noline'>
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
import { personalList } from '@/api/personal.js';

export default {
  name: 'MyPost',
  
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
    personalList(
      this.userData,
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