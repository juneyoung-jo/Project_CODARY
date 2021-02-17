<template>
  <div>
    <v-img
      :src="this.blogContents.blogContentsCover"
      alt=""
      max-height="250px"
      top
    />
  </div>
</template>

<script>
export default {
  name: "PostCover",
  props: ["blogContents"],
};
</script>

<style></style>
