<template>
  <div>
    <img :src="user.profile" />
    <h3>{{ user.nickname }}</h3>
  </div>
</template>

<script>
import { getUserInfo } from "@/api/comment.js";
export default {
  props: ["comment"],
  created() {
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
  },
  data() {
    return {
      user: {
        uid: this.comment,
      },
    };
  },
};
</script>

<style></style>
