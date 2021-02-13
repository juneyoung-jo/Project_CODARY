<template>
  <v-menu 
    v-model="menu" 
    :close-on-content-click="true"
    offset-y
  >
    <template v-slot:activator="{ on, attrs }">
      <v-badge bordered overlap :content="commentCnt" class="pb-2" offset-x="10" offset-y="20">
        <span style="font-size: 35px">
          <font-awesome-icon :icon="['fas', 'user-circle']" v-bind="attrs" v-on="on" />
        </span>
      </v-badge>
    </template>

    <v-card style="width:400px">
      <v-list color="background">
        <v-list-item>
          <v-list-item-avatar class="ma-5">
            <img :src="this.loggedInUserData.profile" alt="John" />
          </v-list-item-avatar>
          <router-link :close-on-content-click="false" class="noline pa-5" 
          :to="{
            name: 'BlogHome', 
            query: { 
              blogId: this.user.blogId
              }
            }">
            <v-list-item-content>
              <v-list-item-title class="myname">{{
                this.loggedInUserData.nickname
              }}</v-list-item-title>
              <v-list-item-subtitle>내 블로그 홈 가기</v-list-item-subtitle>
            </v-list-item-content>
          </router-link>
          <v-list-item-action>
            <v-btn class='pr-16 mr-15' color="primary" @click.prevent="logout">
              LOGOUT
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-card>
  </v-menu>
</template>

<script>
import { mapState } from 'vuex';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';
import { commentCheck } from '@/api/comment.js';

export default {
  name: 'MainBadge',
  computed: {
    ...mapState(['loggedInUserData', 'userInfo']),
  },

  data: () => ({
    fav: true,
    menu: false,
    message: false,
    hints: true,
    user: {
      uid: '',
      blogId: '',
    },
    commentCnt: 0,
  }),
  created() {
    this.initUser();
    this.cmtCheck();
  },
  methods: {
    initUser() {
      this.user.uid = getuidCookie();
      this.user.blogId = getblogIdCookie();
     // console.log("메인벳지에서 보낸값 "+this.user.blogId);
    },
    cmtCheck() {
      if (this.user.uid === '') return;
      commentCheck(
        this.user,
        (response) => {
          response.data.data.forEach((data) => {
            // console.log(data.count);
            this.$data.commentCnt += data.count;
          });
          // console.log(this.commentCnt);
        },
        (error) => console.log(error)
      );
    },
    logout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {
          if (this.$route.path !== '/') this.$router.replace('/');
        })
        .catch(() => {
          console.log('로그아웃 문제!');
        });
    },
  },
};
</script>
<style>

</style>
