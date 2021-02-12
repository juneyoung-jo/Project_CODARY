<template>
  <div>
    <div class="py-7"></div>
    
    <v-container class="d-flex pa-0">
      <v-text-field
        v-model="comment.commentContent"
        label="여기에 댓글을 입력하세요"
        clearable
        @keyup.enter="write()"
      ></v-text-field>
      <v-btn elevation="2" plain color="blue" large class="mx-3" @click="write()">
        댓글달기!
      </v-btn>
      
    </v-container>
  </div>
</template>

<script>
import { writeComment } from '@/api/comment.js';
import { mapGetters } from 'vuex';
export default {
  name: 'CommentWrite',
  props: ['blogContents'],
  created() {
    this.comment.blogContentsId = this.blogContents.blogContentsId;
    this.comment.blogId = this.blogContents.blogId;
  },
  data() {
    return {
      comment: {
        blogContentsId: '', // 받아야 할 정보
        blogId: '', // 받아야 할 정보
        commentContent: '',
        uid: '', // 로컬스토리지 uid 값 입력
      },
    };
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  methods: {
    write() {
      if (this.comment.commentContent === '') return;
      if (this.loggedInUserData === null || !this.loggedInUserData.uid) {
        alert('로그인 해주세요!');
        return;
      }
      this.comment.uid = this.loggedInUserData.uid;
      writeComment(
        this.comment,
        (response) => {
          if (response.data.msg === 'success') {
            // this.$router.go(this.$router.current);
            this.$emit('WRITECMT');
            this.clearWrite();
          } else if (response.data.msg === 'fail') {
            alert('댓글 달기 실패!');
          }
        },
        (error) => {
          alert('댓글 달기 실패!');
          console.log(error);
        }
      );
    },
    clearWrite() {
      this.comment.commentContent = '';
    },
  },
};
</script>

<style></style>
