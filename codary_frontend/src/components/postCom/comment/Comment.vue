<template>
  <v-sheet color="background">
    <v-list three-line color="background">
      <v-sheet color="background" v-for="(item, index) in this.items" :key="index">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

        <v-divider
          color="background"
          v-else-if="item.divider"
          :key="index"
          :inset="item.inset"
        ></v-divider>

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
         <v-sheet class='pt-1' color='background'>
         </v-sheet>
        <v-divider class='mb-5'></v-divider>
        </v-sheet>
      </v-sheet>
    </v-list>
  </v-sheet>
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
