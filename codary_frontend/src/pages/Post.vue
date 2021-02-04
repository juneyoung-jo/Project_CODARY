<template>
  <div>
    <PostCover />
    <v-container>
      <v-card class="pa-8 py-8">
        <PostViewer />
        <div class="py-16"></div>
        <Profile />
      </v-card>
      <CommentWrite @WRITECMT="writeComment" />
      <Comment :items="items" @DELETECMT="deleteComment" />
    </v-container>
  </div>
</template>

<script>
import PostCover from "../components/postCom/PostCover.vue";
import PostViewer from "../components/postCom/PostViewer.vue";
import Profile from "../components/postCom/Profile.vue";
import Comment from "../components/postCom/comment/Comment.vue";
import CommentWrite from "../components/postCom/comment/CommentWrite.vue";
import { commentList } from "@/api/comment.js";

export default {
  components: { PostCover, PostViewer, Profile, Comment, CommentWrite },
  name: "Post",
  created() {
    commentList(
      this.blogContents,
      (response) => {
        // console.log(response.data.data);
        this.items = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    this.getBlogContent();
  },
  data() {
    return {
      items: [],
      blogContents: {},
    };
  },
  methods: {
    getBlogContent() {
      this.axios
        .get(
          `blog/${this.$route.params.blogId}/${this.$route.params.blogContentsId}`
        )
        .then((res) => {
          console.log(res);
          this.blogContents = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteComment(index) {
      this.items.splice(index, 1);
    },
    writeComment(comment) {
      this.items.push(comment);
    },
  },
};
</script>

<style></style>
