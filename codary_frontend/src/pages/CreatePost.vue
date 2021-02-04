<template>
  <div>
    <SelectPostCover @GETCOVER="getCover" />
    <v-container>
      <PostName />
      <Editor />
    </v-container>
  </div>
</template>

<script>
import Editor from "../components/createPostCom/Editor.vue";
import PostName from "../components/createPostCom/PostName.vue";
import SelectPostCover from "../components/createPostCom/SelectPostCover.vue";

export default {
  name: "CreatePost",
  components: { Editor, SelectPostCover, PostName },
  data() {
    return {
      blogContents: {
        blogId: "",
        blogContents: "",
        blogContentsCover: "",
        blogContentsTitle: "",
      },
    };
  },
  methods: {
    writeContents() {
      this.axios
        .post(`blog`, {
          blogId: this.blogContents.blogId,
          blogContentsTitle: this.blogContents.blogContentsTitle,
          blogContents: this.blogContents.blogContents,
          blogContentsCover: this.blogContentsCover,
        })
        .then((res) => {
          console.log(res.data);
          this.blogContents = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getCover(cover) {
      this.blogContents.blogContentsCover = cover;
    },
  },
};
</script>

<style></style>
