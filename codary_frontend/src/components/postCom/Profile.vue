<template>
  <div class="py-6">
    <v-container id="user-profile-view" fluid class="profile-back">
      <v-row class='profileback'>
        <v-col class="d-flex align-center pa-0">
          <router-link
            :to="{
              name: 'BlogHome',
              query: {
                blogId: this.blogId,
              },
            }"
            class="noline rounded-lg"
          >
            <v-img
              class="rounded-circle elevation-6"
              :src="this.blogContents.profile"
              width="80"
              height="80"
            />
          </router-link>
          <v-card-text>

            <h5 class="ml-1" style="color:black;">
              {{ job }}

            </h5>
            <h4 class="text-h4" style='color:black;'>
              {{ this.blogContents.nickname }}
            </h4>
            <span class="ml-1" style='color:black;'>
              {{ intro }}
            </span>
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
        this.job = res.data.data.job;
        this.intro = res.data.data.intro;
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
      job: '',
      intro: '',
    };
  },
  methods: {
    getUserInfo() {
      this.blogId = this.blogContents.blogId;
    },
  },
};
</script>

<style>
.profileback {
  margin-top: 100px;
}
</style>
