<template>
  <div>
    <SelectPostCover :blogContents="blogContents" @GETCOVER="getCover" />
    <v-container>
      <!-- <PostName /> -->
      <Editor :blogContents="blogContents" @GETCONTENT="getContent" />
    </v-container>
  </div>
</template>

<script>
import Editor from "../components/createPostCom/Editor.vue";
// import PostName from "../components/createPostCom/PostName.vue";
import SelectPostCover from "../components/createPostCom/SelectPostCover.vue";
import { writeContent, modifyContent } from "@/api/blogcontent.js";
import { getuidCookie, getblogIdCookie } from "@/util/cookie.js";

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
        blogContentsId: "",
        blogContentsCover:
          "https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg",
        hashTag: [],
      },
      user: {
        uid: "",
        blogId: "",
      },
    };
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      this.user.uid = getuidCookie();
      this.user.blogId = getblogIdCookie();

      if (this.$route.query.blogId !== undefined) {
        this.blogContents.blogId = this.$route.query.blogId;
        this.blogContents.blogContentsId = this.$route.query.blogContentsId;
        this.blogContents.blogContents = this.$route.query.blogContents;
        this.blogContents.blogContentsTitle = this.$route.query.blogContentsTitle;
        this.blogContents.blogContentsCover = this.$route.query.blogContentsCover;
        console.log("쿼리로 넘어온 정보: " + this.blogContents.blogContentsId);
      }
    },
    getCover(cover) {
      this.blogContents.blogContentsCover = cover;
    },
    getContent(content, title, selectedTags) {
      this.blogContents.blogContents = content;
      this.blogContents.blogContentsTitle = title;
      this.blogContents.blogId = this.user.blogId;
      this.blogContents.hashTag = selectedTags;
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
      if (this.blogContents.blogContentsId !== "") {
        modifyContent(
          this.blogContents,
          () => {
            alert("수정이 완료되었습니다.");
            this.$router.go(-1);
          },
          (err) => {
            console.log(err);
          }
        );
      } else {
        writeContent(
          this.blogContents,
          (res) => {
            // console.log(res);
            this.$router.push({
              name: "ViewPost",
              query: {
                blogId: this.user.blogId,
                blogContentsId: res.data,
              },
            });
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
  },
};
</script>

<style></style>
