<template>
  <div>
    <PostCover :blogContents="blogContents" />
    <v-container>
      <v-card class="pa-8 py-8">
        <PostViewer :blogContents="blogContents" />
        <div class="py-16"></div>
        <Profile :blogContents="blogContents" />
      </v-card>
      <CommentWrite @WRITECMT="writeComment" :blogContents="blogContents" />
      <Comment
        :items="items"
        @DELETECMT="deleteComment"
        @MODIFYCOMMENT="modifyComment"
      />
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
  data() {
    return {
      items: [],
      blogContents: {
        blogId: "",
        blogContents: "",
        blogContentsId: "",
        profile: "",
        nickname: "",
        commantCnt: "",
        blogContentsCover: "",
        blogDatetime: "",
        blogContentsTitle: "",
        blogContentsLike: "",
        blogContentsView: "",
      },
    };
  },
  created() {
    this.getBlogContent();
    commentList(
      this.blogContents,
      (response) => {
        this.items = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    getBlogContent() {
      const blogId = this.$route.query.blogId;
      const blogContentsId = this.$route.query.blogContentsId;
      this.axios
        .get(`blog/${blogId}/${blogContentsId}`)
        .then((res) => {
          console.log(res.data);
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
    },
    modifyComment(index, content) {
      this.items[index].commentContent = content;
    },
  },
};
</script>

<style></style>
