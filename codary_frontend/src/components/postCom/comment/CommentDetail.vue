<template>
  <v-sheet color='background' class="d-flex align-center">
    <img :src="user.profile" width="30px" height="30px" style="border-radius:20px"/>
    <h4 class="ml-3">{{ user.nickname }}</h4>
    <v-sheet color="background">
      <template v-if="loggedInUserData !== null">
        <template v-if="!commentLikeflag">
          <v-btn plain color="grey" class="pa-0" @click="cmtLike()">
            <font-awesome-icon :icon="['fas','heart']"/>
            <div class='ml-2'>
              {{ this.comment.commentLike }}
            </div>
          </v-btn>
        </template>
        <template v-else>
          <v-btn plain color="red" @click="cmtLikeCancle()">
            <font-awesome-icon :icon="['fas','heart']"/> 
            <div class='ml-2'>
              {{ this.comment.commentLike }}
            </div>
          </v-btn>
        </template>
      </template>
    </v-sheet>
  </v-sheet>
</template>

<script>
import { getUserInfo, getCommentLike, commentLike, commentLikeCancle } from '@/api/comment.js';
import { mapGetters } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
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
    this.commentLike.uid = getuidCookie();

    getCommentLike(
      this.commentLike,
      (responese) => {
        // console.log(responese.data.data);
        // console.log(this.commentLike);
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
      if (this.commentLike.uid === '') {
        alert('로그인 해주세요!');
        return;
      }
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
      if (this.commentLike.uid === '') {
        alert('로그인 해주세요!');
        return;
      }
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
