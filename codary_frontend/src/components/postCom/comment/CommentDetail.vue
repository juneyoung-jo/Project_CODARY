<template>
  <div>
    <img :src="user.profile" />
    <h3>{{ user.nickname }}</h3>
    <h5>좋아요 : {{ this.comment.commentLike }}</h5>
    <template v-if="loggedInUserData !== null">
      <template v-if="!commentLikeflag">
        <v-btn plain color="blue" @click="cmtLike()">
          좋아요!
        </v-btn>
      </template>
      <template v-else>
        <v-btn plain color="red" @click="cmtLikeCancle()">
          좋아요 취소!
        </v-btn>
      </template>
    </template>
  </div>
</template>

<script>
import { getUserInfo, getCommentLike, commentLike, commentLikeCancle } from '@/api/comment.js';
import { mapGetters } from 'vuex';
export default {
  props: ['comment'],
  created() {
    // 유저 정보 프로필
    this.user.uid = this.comment.uid;
    getUserInfo(
      this.user,
      (response) => {
        // console.log(response);
        this.user = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );

    // 댓글의 좋아요 체크
    this.commentLike.commentNum = this.comment.commentNum;
    this.commentLike.uid = this.loggedInUserData.uid;
    getCommentLike(
      this.commentLike,
      (responese) => {
        // console.log(responese.data.data);
        this.commentLikeflag = responese.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      user: {
        uid: '',
      },
      commentLike: {
        commentNum: '',
        uid: '',
      },
      commentLikeflag: '',
    };
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  methods: {
    cmtLike() {
      commentLike(
        this.commentLike,
        (response) => {
          // console.log(response.data);
          if (response.data.msg === 'success') {
            this.commentLikeflag = true;
            this.comment.commentLike += 1;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    cmtLikeCancle() {
      // alert('좋아요 취소!');
      commentLikeCancle(
        this.commentLike,
        (response) => {
          if (response.data.msg === 'success') {
            this.commentLikeflag = false;
            this.comment.commentLike -= 1;
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
