<template>
  <div>
    <v-list three-line>
      <template v-for="(item, index) in this.items">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

        <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

        <v-sheet v-else :key="item.commentNum" class="pa-0">
          <!-- <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar> -->

          <CommentDetail :comment="item" />

          <!-- <v-list-item-commentContent> -->
          <!-- <v-list-item-title v-html="item.commentContent"></v-list-item-title> -->
          <CommentRow :item="item" :index="index" @DELETECMT="deleteCmt" @MODIFYCMT="modifyCmt" />
          <!-- <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle> -->
          <!-- </v-list-item-commentContent> -->
         <hr>
          <div class="py-3"></div>
        </v-sheet>
      </template>
    </v-list>
    <div class="py-16"></div>
  </div>
</template>

<script>
import { deleteComment } from '@/api/comment.js';
import CommentDetail from './CommentDetail.vue';
import CommentRow from './CommentRow.vue';

export default {
  name: 'Comment',
  props: ['items'],
  components: {
    CommentDetail,
    CommentRow,
  },
  created() {},
  data() {
    return {};
  },
  methods: {
    deleteCmt(commentNum, index) {
      deleteComment(
        commentNum,
        (response) => {
          if (response.data.msg === 'success') {
            this.$emit('DELETECMT', index);
            alert('댓글삭제');
          } else if (response.data.msg === 'fail') {
            alert('댓글 삭제 실패?');
          }
        },
        (error) => {
          alert('삭제실패');
          console.log(error);
        }
      );
    },
    modifyCmt(index, content) {
      this.$emit('MODIFYCOMMENT', index, content);
    },
  },
};
</script>

<style></style>
