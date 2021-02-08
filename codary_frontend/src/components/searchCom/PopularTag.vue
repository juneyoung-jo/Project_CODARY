<template>
  <section id="blog">
    <v-container>
      <h2 class="font-weight-bold mb-3 text-uppercase text-start">인기태그</h2>
      <v-responsive class="mx-auto mb-12" width="56">
        <v-divider class="mb-1"></v-divider>
        <v-divider></v-divider>
      </v-responsive>
      <v-row>
        <v-col
          v-for="{
            blogContentsCover,
            blogDatetime,
            blogContentsTitle,
            blogContentsLike,
            blogContentsView,
            blogContentsId,
            blogContents,
            blogId,
            profile,
            nickname,
            commentCnt,
          } in list"
          :key="blogContentsId"
          cols="12"
          md="4"
        >
          <v-img
            :src="blogContentsCover"
            class="mb-4"
            height="275"
            max-width="100%"
          ></v-img>
          <h3
            class="font-weight-black mb-4 text-uppercase"
            v-text="blogContentsTitle"
          ></h3>
          <div class="title font-weight-light mb-5" v-text="blogDatetime"></div>
          <router-link
            :to="{
              name: 'ViewPost',
              query: {
                blogId: blogId,
                blogContentsId: blogContentsId,
              },
            }"
            class="noline"
          >
            <v-img
              :src="profile"
              class="mb-4"
              height="30"
              max-width="30%"
            ></v-img>
            <div>
              {{ nickname }} 댓글 {{ commentCnt }} 좋아요
              {{ blogContentsLike }} 조회수
              {{ blogContentsView }}
            </div>
            <div>{{ blogContents | textLengthOverCut }}</div>
            <v-btn class="ml-n4 font-weight-black" text>
              Continue Reading
            </v-btn>
          </router-link>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
export default {
  name: "PopularTag",
  props: ["list"],
  data() {
    return {};
  },
  created() {},
  methods: {},
  filters: {
    textLengthOverCut(txt, len, lastTxt) {
      if (len == "" || len == null) {
        len = 100;
      }
      if (lastTxt == "" || lastTxt == null) {
        lastTxt = "...";
      }
      if (txt.length > len) {
        txt = txt.substr(0, len) + lastTxt;
      }
      return txt;
    },
  },
};
</script>

<style></style>
