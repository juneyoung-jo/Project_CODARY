<template>
  <v-container fluid>
    <v-row>
      <v-col cols="6" md="12">
        <template v-if="flag">
          <v-list-item-title v-html="item.commentContent"></v-list-item-title>
          <template v-if="loggedInUserData !== null && loggedInUserData.uid === item.uid">
            <v-btn plain color="blue" @click="modifyCmt()">댓글수정</v-btn>
            <v-btn plain color="red" @click="deleteCmt(item.commentNum, index)">댓글삭제</v-btn>
          </template>
        </template>
        <template v-else>
          <v-col cols="3" md="12">
            <v-textarea
              solo
              name="input-7-4"
              label="Solo textarea"
              v-model="copyComment"
            ></v-textarea>
          </v-col>
          <v-btn plain color="blue" @click="confirmBtn(index)">확인</v-btn>
          <v-btn plain color="red" @click="cancelBtn()">취소</v-btn>
        </template>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { modifyComment } from '@/api/comment.js';
import { mapGetters } from 'vuex';
export default {
  props: ['item', 'index'],
  data() {
    return {
      flag: true,
      copyComment: '',
    };
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  methods: {
    modifyCmt() {
      this.flag = false;
      this.copyComment = this.item.commentContent;
    },
    deleteCmt(commentNum, index) {
      this.$emit('DELETECMT', commentNum, index);
    },
    cancelBtn() {
      this.flag = true;
    },
    confirmBtn(index) {
      this.item.commentContent = this.copyComment;
      modifyComment(
        this.item,
        (response) => {
          if (response.data.msg === 'success') {
            this.flag = true;
            this.$emit('MODIFYCMT', index, this.copyComment);
            alert('댓글 수정');
          } else if (response.data.msg === 'fail') {
            alert('댓글 수정 실패?');
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
