<template>
  <div>
    <SelectPostCover @GETCOVER="getCover" />
    <v-container>
      <!-- <PostName /> -->
      <Editor @GETCONTENT="getContent" />
    </v-container>
  </div>
</template>

<script>
import Editor from "../components/createPostCom/Editor.vue";
// import PostName from "../components/createPostCom/PostName.vue";
import SelectPostCover from "../components/createPostCom/SelectPostCover.vue";
import { mapGetters } from "vuex";
import { writeContent } from "@/api/blogcontent.js";

export default {
  name: "CreatePost",
  // components: { Editor, SelectPostCover, PostName },
  components: { Editor, SelectPostCover },
  data() {
    return {
      blogContents: {
        blogId: "",
        blogContentsTitle: "",
        blogContents: "",
        blogContentsCover:
          "https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg",
      },
    };
  },
  computed: {
    ...mapGetters(["loggedInUserData"]),
  },
  methods: {
    getCover(cover) {
      this.blogContents.blogContentsCover = cover;
    },
    getContent(content, title) {
      this.blogContents.blogContents = content;
      this.blogContents.blogContentsTitle = title;
      this.blogContents.blogId = this.loggedInUserData.blogId;
      // console.log(this.blogContents);
      // this.axios
      //   .post(`blog`, this.blogContents)
      //   .then(() => {
      //     // console.log(res.data);
      //     // this.blogContents = res.data;
      //     this.$router.push("/searchpage");
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
      writeContent(
        this.blogContents,
        (res) => {
          console.log(res);
          this.$router.push("/searchpage");
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>

<style></style>
