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
import { mapGetters } from 'vuex';
import { writeLog } from '@/api/blogContents.js';

export default {
  components: { PostCover, PostViewer, Profile, Comment, CommentWrite },
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
      user: null,
    };
  },
  created() {
    this.getBlogContent();
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  methods: {
    getBlogContent() {
      const blogId = this.$route.query.blogId;
      const blogContentsId = this.$route.query.blogContentsId;
      this.blogContents.blogId = blogId;
      this.blogContents.blogContentsId = blogContentsId;

      // setTimeout(() => {
      if (this.loggedInUserData !== null && this.loggedInUserData.blogId === blogId) {
        writeLog(
          this.loggedInUserData.uid,
          blogId,
          blogContentsId,
          (response) => {
            this.blogContents.blogContentsCover = response.data.data.blogContentsCover;
            this.blogContents.blogContentsTitle = response.data.data.blogContentsTitle;
            this.blogContents.blogContents = response.data.data.blogContents;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.axios
          .get(`blog/${blogId}/${blogContentsId}`)
          .then((res) => {
            this.blogContents.blogContentsCover = res.data.blogContentsCover;
            this.blogContents.blogContentsTitle = res.data.blogContentsTitle;
            this.blogContents.blogContents = res.data.blogContents;
          })
          .catch((err) => {
            console.log(err);
          });
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
      // }, 500);
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
  watch: {
    loggedInUserData() {
      this.getBlogContent();
    },
  },
};
</script>

<style></style>
