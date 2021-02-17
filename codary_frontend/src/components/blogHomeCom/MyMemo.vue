<template>
  <section id="blog">
    <v-container>
      <v-responsive class="mx-auto mb-12" width="56"> </v-responsive>
      <div class="subtitle-2 text-center">
        <h4 v-if="this.articles.length == 0">작성한 메모가 없어요!</h4>
      </div>
      <v-row>
        <v-col
          v-for="(article, index) in articles"
          :key="index"
          cols="12"
          md="4"
        >
          <h3 class="font-weight-black mb-4 text-uppercase">
            {{ article.memoContent }}
          </h3>
          <div class="title font-weight-light mb-5">
            {{ article.memoTime }}
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <a :href="article.memoLink">
                <v-btn 
                v-bind="attrs"
                v-on="on" 
                small 
                fab 
                plain 
                :value="article.memoLink"
                style="font-size: 15px"   
                class="mb-1 pr-1"
                >
                  <font-awesome-icon :icon="['fas', 'arrow-alt-circle-right']" />
                </v-btn>
              </a>
            </template>
            <span style="font-size: 12px">작성했던 장소로 가기</span>
          </v-tooltip>
            

          </div>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-16"></div>
     <div class="py-16"></div>
  </section>
</template>

<script>
import { mapState } from "vuex";
import { showMyMemo } from "@/api/personal.js";
import {
  getuidCookie,
  getblogIdCookie,
  getmemoIdCookie,
} from "@/util/cookie.js";

export default {
  name: "MyMemo",
  data() {
    return {
      articles: [],
      user: {
        user: "",
        blogId: "",
        memoId: "",
      },
      memo: {
        memoId: "",
      },
    };
  },
  computed: {
    ...mapState(["loggedInUserData"]),
  },
  created() {
    this.initUser();
    this.mymemo();
  },
  methods: {
    initUser() {
      this.user.user = getuidCookie();
      this.user.blogId = getblogIdCookie();
      this.user.memoId = getmemoIdCookie();
      // console.log(this.user.memoId);
    },
    mymemo() {
      // console.log('메모다');
      this.memo.memoId = this.user.memoId;
      showMyMemo(
        this.memo,
        (response) => {
          // console.log(response)
          this.articles = response.data;
          //console.log(this.articles)
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>

<style>
</style>