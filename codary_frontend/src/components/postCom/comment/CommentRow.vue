<template>
  <v-sheet color='background' fluid class='py-5'>
    <v-row class='py-2 d-flex flex-column' color='background' >
      <v-col class="py-3 pl-7">
      <v-list-item-title v-html="item.commentContent"></v-list-item-title>
      </v-col>
      <v-col>
        <v-sheet v-if="flag" color='background'>
          <template v-if="loggedInUserData !== null && loggedInUserData.uid === item.uid">
            <v-btn plain color="blue" @click="modifyCmt()">
              <font-awesome-icon :icon="['fas','edit']"/> 
              <div class='ml-2'>
                수정
              </div>
            </v-btn>
            <v-btn plain color="red" @click="deleteCmt(item.commentNum, index)">
              <font-awesome-icon :icon="['fas','trash-alt']"/>
              <div class='ml-2'>
                삭제
              </div>
            </v-btn>
          </template>
        </v-sheet>
        <template v-else>
          <v-col md="12">
            <v-textarea
              solo
              name="input-7-4"
              label="Solo textarea"
              v-model="copyComment"
              plain
              flat
            ></v-textarea>
          </v-col>
          <v-btn plain color="blue" @click="confirmBtn(index)">확인</v-btn>
          <v-btn plain color="red" @click="cancelBtn()">취소</v-btn>
        </template>
      </v-col>
    </v-row>
  </v-sheet>
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
