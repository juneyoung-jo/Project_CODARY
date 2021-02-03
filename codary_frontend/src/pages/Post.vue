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
      <Comment :items="items" @DELETECMT="deleteComment" @MODIFYCOMMENT="modifyComment" />
    </v-container>
  </div>
</template>

<script>
import PostCover from '../components/postCom/PostCover.vue';
import PostViewer from '../components/postCom/PostViewer.vue';
import Profile from '../components/postCom/Profile.vue';
import Comment from '../components/postCom/comment/Comment.vue';
import CommentWrite from '../components/postCom/comment/CommentWrite.vue';
import { commentList } from '@/api/comment.js';

export default {
  components: { PostCover, PostViewer, Profile, Comment, CommentWrite },
  name: 'Post',
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
  },
  data() {
    return {
      items: [],
      blogContents: {
        blogId: 'mqa6x5p2oESm', // 이 부분 데이터 받아와야 함
        blogContentsId: 2, // 이 부분 데이터 받아와야 함
      },
    };
  },
  methods: {
    deleteComment(index) {
      this.items.splice(index, 1);
    },
    writeComment() {
      // this.items.push(comment);
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
