<template>
  <div>
    <div class="py-7"></div>
    <h1>Comment</h1>
    <v-container class="d-flex">
      <v-text-field
        v-model="comment.commentContent"
        label="여기에 댓글을 입력하세요"
        clearable
      ></v-text-field>
      <v-btn plain color="blue" @click="write()">
        댓글달기!
      </v-btn>
    </v-container>
  </div>
</template>

<script>
import { writeComment } from '@/api/comment.js';
export default {
  name: 'CommentWrite',
  data() {
    return {
      comment: {
        blogContentsId: '2', // 받아야 할 정보
        blogId: 'mqa6x5p2oESm', // 받아야 할 정보
        commentContent: '',
        uid: 'VXFXaxa6Eupw', // 로컬스토리지 uid 값 입력
      },
    };
  },
  methods: {
    write() {
      if (this.comment.commentContent === '') return;
      writeComment(
        this.comment,
        (response) => {
          if (response.data.msg === 'success') {
            // this.$router.go(this.$router.current);
            this.$emit('WRITECMT');
            this.clearWrite();
          } else if (response.data.msg === 'fail') {
            alert('댓글 달기 실패?');
          }
        },
        (error) => {
          alert('로그인 해주세요!');
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
