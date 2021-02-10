<template>
  <div class="py-6 pa-8">
    <v-container id="user-profile-view" fluid class="profile-back">
      <v-row>
        <v-col cols="9" class="d-flex">
          <router-link
            :to="{
              name: 'BlogHome',
              params: {
                blogId: this.blogId,
              },
            }"
            class="noline rounded-lg"
          >
            <v-img
              class="rounded-circle elevation-6"
              :src="this.blogContents.profile"
              width="128"
              height="128"
            />
          </router-link>
          <v-card-text>
            <h6 class="text-h6 mb-2 text--secondary">
              카카오 크루원
            </h6>

            <h4 class="text-h4 mb-3 text--primary">
              {{ this.blogContents.nickname }}
            </h4>
            <p class="text--secondary">
              개발자를 위한 블로그를 개발중인 개발자
            </p>
          </v-card-text>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  props: ['blogContents'],
  created() {
    this.getUserInfo();
    this.axios
      .get(`blog/blogUserInfo/${this.blogId}`)
      .then((res) => {
        // console.log("여기입니다");
        // console.log(res.data.profile);
        this.blogContents.profile = res.data.data.profile;
        this.blogContents.nickname = res.data.data.nickname;
      })
      .catch((err) => {
        console.log(err);
      });
    // this.getUserInfo();
    // console.log("하이하이하이");
    // console.log(this.blogContents);
  },
  data() {
    return {
      blogId: '',
      // nickname: "",
      // profile: "",
    };
  },
  methods: {
    getUserInfo() {
      this.blogId = this.blogContents.blogId;
    },
  },
};
</script>

<style></style>
