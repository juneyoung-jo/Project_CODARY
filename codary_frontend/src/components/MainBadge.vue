<template>
  <v-menu v-model="menu" :close-on-content-click="true" offset-y>
    <template v-slot:activator="{ on, attrs }">
      <v-badge bordered overlap :content="commentCnt" class="pb-2" offset-x="10" offset-y="20">
        <span style="font-size: 35px">
          <font-awesome-icon :icon="['fas', 'user-circle']" v-bind="attrs" v-on="on" />
        </span>
      </v-badge>
    </template>

    <v-card>
      <v-list>
        <v-list-item>
          <v-list-item-avatar class="ma-3">
            <img :src="this.loggedInUserData.profile" alt="John" />
          </v-list-item-avatar>
          <router-link :close-on-content-click="false" class="noline pa-5" :to="'/bloghome'">
            <v-list-item-content>
              <v-list-item-title class="myname">{{
                this.loggedInUserData.nickname
              }}</v-list-item-title>
              <v-list-item-subtitle>내 블로그 홈 가기</v-list-item-subtitle>
            </v-list-item-content>
          </router-link>
          <v-list-item-action>
            <v-btn color="primary" @click.prevent="logout">
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
    },
    cmtCheck() {
      if (this.user.uid === '') return;
      commentCheck(
        this.user,
        (response) => {
          if (response.data.data === null) return;
          response.data.data.forEach((data) => {
            this.$data.commentCnt += data.count;
          });
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
.myname {
  color: black;
}
</style>
