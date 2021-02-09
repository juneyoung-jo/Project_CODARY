<template>
  <div>
    <PostCover :blogContents="blogContents" />
    <v-container>
      <v-card class="pa-8 py-8">
        <!-- <PostViewer :blogContents="blogContents" /> -->
        <div class="py-2"></div>
        <h1 class="py-10 pa-14">{{ this.blogContents.blogContentsTitle }}</h1>
        <div class="pa-13 py-0">
          <v-chip outlined>코딩초보</v-chip>
          <v-chip outlined>도와주세요</v-chip>
          <v-chip outlined>알고리즘</v-chip>
          <v-chip outlined>백준</v-chip>
        </div>
        <v-container class="pa-16">
          <div id="viewer" />
        </v-container>
        <Profile :blogContents="blogContents" />
      </v-card>
      <CommentWrite @WRITECMT="writeComment" :blogContents="blogContents" />
      <Comment :items="items" @DELETECMT="deleteComment" @MODIFYCOMMENT="modifyComment" />
    </v-container>
  </div>
</template>

<script>
import PostCover from '../components/postCom/PostCover.vue';
import Profile from '../components/postCom/Profile.vue';
import Comment from '../components/postCom/comment/Comment.vue';
import CommentWrite from '../components/postCom/comment/CommentWrite.vue';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import { commentList } from '@/api/comment.js';
import { mapGetters } from 'vuex';
import { writeLog } from '@/api/blogContents.js';
import { getContent } from '@/api/blogcontent.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

export default {
  components: { PostCover, Profile, Comment, CommentWrite },
  name: 'Post',
  data() {
    return {
      items: [],
      blogContents: {
        blogId: '',
        blogContents: '',
        blogContentsId: '',
        profile: '',
        nickname: '',
        commantCnt: '',
        blogContentsCover: '',
        blogDatetime: '',
        blogContentsTitle: '',
        blogContentsLike: '',
        blogContentsView: '',
      },
      user: {
        uid: '',
        blogId: '',
      },
    };
  },
  created() {
    this.initUser();
    this.getBlogContent();
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  methods: {
    initUser() {
      this.user.uid = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    getBlogContent() {
      const blogId = this.$route.query.blogId;
      const blogContentsId = this.$route.query.blogContentsId;
      this.blogContents.blogId = blogId;
      this.blogContents.blogContentsId = blogContentsId;

      if (this.user !== null && this.user.blogId === blogId) {
        // alert('본인글'); // 나중에 지우기.
        writeLog(
          this.user.uid,
          blogId,
          blogContentsId,
          (response) => {
            this.blogContents.blogContentsCover = response.data.data.blogContentsCover;
            this.blogContents.blogContentsTitle = response.data.data.blogContentsTitle;
            this.blogContents.blogContents = response.data.data.blogContents;

            new Viewer({
              el: document.querySelector('#viewer'),
              initialValue: response.data.data.blogContents,
            });
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        getContent(
          blogId,
          blogContentsId,
          (res) => {
            this.blogContents.blogContentsCover = res.data.blogContentsCover;
            this.blogContents.blogContentsTitle = res.data.blogContentsTitle;
            this.blogContents.blogContents = res.data.blogContents;

            new Viewer({
              el: document.querySelector('#viewer'),
              initialValue: res.data.blogContents,
            });
          },
          (error) => console.log(error)
        );
      }
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
