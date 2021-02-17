<template>
  <v-menu v-model="menu" :close-on-content-click="true" offset-y>
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
          <router-link
            :close-on-content-click="false"
            class="noline pa-5"
            :to="{
              name: 'BlogHome',
              query: {
                blogId: this.user.blogId,
              },
            }"
          >
            <v-list-item-content>
              <v-list-item-title class="myname font-weight-bold">{{
                this.loggedInUserData.nickname
              }}</v-list-item-title>
              <v-list-item-subtitle>내 블로그 홈 가기</v-list-item-subtitle>
            </v-list-item-content>
          </router-link>
          <v-list-item-action class="ml-0">
            <v-btn  color="primary" @click.prevent="logout">
              LOGOUT
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-card>
    <template v-if="blogContentsList != ''">
      <v-card style="width:400px">
        <v-list color="background"
          ><template v-for="(item, index) in this.blogContentsList">
            <div :key="item.blogContentId">
              <v-btn plain @click="moveBlogCotnents(item.blogContentId, item.count, index)">
                <span class='mt-3 ml-2'>"{{ item.blogCotnentsTitle }}"</span>
                <span color='primary' class='font-weight-bold ml-2 mt-3'>댓글 {{ item.count }}</span>
              </v-btn>
              <br />
              <br />
            </div>
          </template>
        </v-list>
      </v-card>
    </template>
  </v-menu>
</template>

<script>
import { mapState } from 'vuex';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';
import { commentCheck } from '@/api/comment.js';
import { personalList } from '@/api/personal.js';

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
    blogContentsList: [],
    blogContentsListAll: [],
  }),
  created() {
    this.initUser();
    this.cmtCheck();
    // this.blogContentAll();
  },
  methods: {
    initUser() {
      this.user.uid = getuidCookie();
      this.user.blogId = getblogIdCookie();
      // console.log("메인벳지에서 보낸값 "+this.user.blogId);
    },
    blogContentAll() {},
    cmtCheck() {
      if (this.user.uid === '') return;
      commentCheck(
        this.user,
        (response) => {
          if (response.data.data === null) return;
          response.data.data.forEach((data) => {
            // console.log(data.count);
            this.$data.commentCnt += data.count;
          });

          const list = response.data.data;
          const size = list.length;
          // console.log(size);
          personalList(
            this.user.blogId,
            (response) => {
              if (response.data === null) return;
              let index = size - 1;
              response.data.forEach((data) => {
                if (index === -1) return;
                // console.log(data);
                if (data.blogContentsId === list[index].blogContentId) {
                  this.$data.blogContentsList.push({
                    blogContentId: data.blogContentsId,
                    blogCotnentsTitle: data.blogContentsTitle,
                    count: list[index].count,
                  });
                  index--;
                  // console.log(index);
                }
                // console.log(data);
              });
              // console.log(this.blogContentsList);
            },
            (error) => console.log(error)
          );

          // this.blogContentsList = response.data.data;
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
    moveBlogCotnents(blogContentId, count, index) {
      this.blogContentsList.splice(index, 1);
      this.commentCnt -= count;
      this.$router.push({
        path: '/viewpost',
        query: { blogId: this.loggedInUserData.blogId, blogContentsId: blogContentId },
      });
    },
  },
};
</script>
<style>
</style>
