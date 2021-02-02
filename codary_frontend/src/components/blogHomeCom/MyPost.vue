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
          v-for="({ src, text, title }, i) in articles"
          :key="i"
          cols="12"
          md="4"
        >
          <v-img
            :src="src"
            class="mb-4"
            height="275"
            max-width="100%"
          ></v-img>
          <h3
            class="font-weight-black mb-4 text-uppercase"
            {{articles.blog_contents_title}}
          ></h3>
          <div
            class="title font-weight-light mb-5"
            {{articles.blog_contents_title}}
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
import axios from "axios";

export default {
  name: 'MyPost',
  props: {
    blog_contents_title: {type:String}
  },
   data () {
      return {
        articles: [
         
        ],
      }
    },
    created(){
      axios
        .get(`http://i4c105.p.ssafy.io:3306/codary/personal`, {
          params: {}
        })
        .then(({data}) => {
          this.articles = data;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        })
    },
}
</script>

<style>

</style>