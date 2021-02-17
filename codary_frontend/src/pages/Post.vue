<template>
  <div>
    <PostCover :blogContents="blogContents" />
    <v-container>
      <!-- <PostViewer :blogContents="blogContents" /> -->
      <v-sheet color='white' class='ma-16 pa-4 my-10 mt-12'>
        <div class="pt-13 mt-8 pa-12 ml-16 pt-16 keepblack">
          <h1 class='mb-10 mx-10 mt-16'>{{ this.blogContents.blogContentsTitle }}</h1>
          <div class='ml-10'>
            <v-chip
              v-for="hashtag in this.blogContents.hashtags"
              :key="hashtag.hashtagId"
              color='primary'
              class='mr-1'
              @click="search(hashtag)"
              >{{ hashtag.hashtagContent }}</v-chip
            >
            <v-divider color='#F5F5F5' class='mt-10 mr-16 pr-16'></v-divider>
          </div>
        </div>
        <div class='ma-16 pa-4 mt-1'>
          <v-sheet class='pa-13 ml-10' light id="viewer" />
          <div class='py-3'></div>
          <div class='ml-10'>
            <Profile class='ml-10' :blogContents="blogContents" />
            <v-btn-toggle
              v-model="icon"
              class='ml-10 mt-10'
              dense
            >
              <v-btn align="center" @click="clickLike()">
                <font-awesome-icon :icon="['fas','thumbs-up']" class='mr-2'/> 
                <div>
                  == {{ likeOrNot}}
                </div>
              </v-btn>  
              <v-btn
                v-if="this.isItMine"
                id="modify"
                align="center"
                @click="editPost()"
              >
                <font-awesome-icon :icon="['fas','edit']" class='mr-2'/> 
                수정
              </v-btn>
              <v-btn
                v-if="this.isItMine"
                id="delete"
                align="center"
                @click="deletePost()"
              >
                <font-awesome-icon :icon="['fas','trash-alt']" class='mr-2'/>
                삭제
              </v-btn>
            </v-btn-toggle>
            
          </div>
          <div class='py-10'></div>
        </div>

      </v-sheet>

      <div class="px-12">
        <CommentWrite @WRITECMT="writeComment" :blogContents="blogContents" />
        <Comment
          :items="items"
          @DELETECMT="deleteComment"
          @MODIFYCOMMENT="modifyComment"
        />
      </div>
      <div class='py-16'></div>
    </v-container>
  </div>
</template>

<script>
import PostCover from "../components/postCom/PostCover.vue";
import Profile from "../components/postCom/Profile.vue";
import Comment from "../components/postCom/comment/Comment.vue";
import CommentWrite from "../components/postCom/comment/CommentWrite.vue";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";
import { commentList } from "@/api/comment.js";
import { mapGetters } from "vuex";
import { writeLog } from "@/api/blogContents.js";
import {
  getContent,
  contentsLikeOrNot,
  contentsLike,
  contentsUnlike,
} from "@/api/blogcontent.js";
import { getuidCookie, getblogIdCookie } from "@/util/cookie.js";
import { deleteContent } from "@/api/blogcontent.js";

export default {
  components: { PostCover, Profile, Comment, CommentWrite },
  name: "Post",
  data() {
    return {
      items: [],
      blogContents: {
        blogId: "",
        blogContents: "",
        blogContentsId: "",
        profile: "",
        nickname: "",
        commantCnt: "",
        blogContentsCover: "",
        blogDatetime: "",
        blogContentsTitle: "",
        blogContentsLike: "",
        blogContentsView: "",
        hashtags: [],
      },
      user: {
        uid: "",
        blogId: "",
      },
      blogContentsLike: {
        uid: "",
        blogContentsId: "",
      },
      isItMine: false,
      likeOrNot: false,
      icon:'justify',
    };
  },
  created() {
    this.UpperPage();  
    this.initUser();
    this.getBlogContent();
    this.checkLikeOrNot();
  },
  computed: {
    ...mapGetters(["loggedInUserData"]),
  },
  watch: {
    $route: function () {
      this.getBlogContent();
    },
  },
  methods: {
    initUser() {
      this.user.uid = getuidCookie();
      this.user.blogId = getblogIdCookie();
    },
    getBlogContent() {
      const blogId = this.$route.query.blogId;
      const blogContentsId = this.$route.query.blogContentsId;
      this.blogContents.blogId = blogId;
      this.blogContents.blogContentsId = blogContentsId;
      // console.log(blogContentsId);

      if (this.user !== null && this.user.blogId === blogId) {
        // alert('본인글'); // 나중에 지우기.
        this.isItMine = true;
        writeLog(
          this.user.uid,
          blogId,
          blogContentsId,
          (response) => {
            this.blogContents.blogContentsCover =
              response.data.data.blogContentsCover;
            this.blogContents.blogContentsTitle =
              response.data.data.blogContentsTitle;
            this.blogContents.blogContents = response.data.data.blogContents;
            this.blogContents.blogContentsLike =
              response.data.data.blogContentsLike;
            this.blogContents.hashtags = response.data.hashtag;

            function youtubePlugin() {
              Viewer.codeBlockManager.setReplacer("youtube", (youtubeId) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0
                const arr = youtubeId.split("v=");
                youtubeId = arr[1];

                const wrapperId = `yt${Math.random()
                  .toString(36)
                  .substr(2, 10)}`;

                setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);
                return `<div id="${wrapperId}"></div>`;
                // return `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen></iframe>`;
              });
            }

            function renderYoutube(wrapperId, youtubeId) {
              const el = document.querySelector(`#${wrapperId}`);
              el.innerHTML = `<iframe width="100%" height="380px" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen ></iframe>`;
            }

            function blogPlugin() {
              Viewer.codeBlockManager.setReplacer("url", (url) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0

                const wrapperId = `yt${Math.random()
                  .toString(36)
                  .substr(2, 10)}`;

                setTimeout(renderblogUrl.bind(null, wrapperId, url), 0);
                return `<div id="${wrapperId}"></div>`;
                // return `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen></iframe>`;
              });
            }

            function renderblogUrl(wrapperId, url) {
              const el = document.querySelector(`#${wrapperId}`);
              el.innerHTML = `<iframe width="100%" height="400px" src="${url}" 
              frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" style="border:1 solid navy"
              ></iframe>`;
            }

            new Viewer({
              el: document.querySelector("#viewer"),
              viewer: true,
              plugins: [youtubePlugin, blogPlugin],
              initialValue: response.data.data.blogContents,
            });
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        getContent(
          blogId,
          blogContentsId,
          (res) => {
            console.log(res.data.data);
            this.blogContents.blogContentsCover =
              res.data.data.blogContentsCover;
            this.blogContents.blogContentsTitle =
              res.data.data.blogContentsTitle;
            this.blogContents.blogContents = res.data.data.blogContents;
            this.blogContents.hashtags = res.data.hashtag;

            function youtubePlugin() {
              Viewer.codeBlockManager.setReplacer("youtube", (youtubeId) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0
                const arr = youtubeId.split("v=");
                youtubeId = arr[1];

                const wrapperId = `yt${Math.random()
                  .toString(36)
                  .substr(2, 10)}`;

                setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);
                return `<div id="${wrapperId}"></div>`;
                // return `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen></iframe>`;
              });
            }

            function renderYoutube(wrapperId, youtubeId) {
              const el = document.querySelector(`#${wrapperId}`);
              el.innerHTML = `<iframe width="100%" height="380px" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen ></iframe>`;
            }

            function blogPlugin() {
              Viewer.codeBlockManager.setReplacer("url", (url) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0

                const wrapperId = `yt${Math.random()
                  .toString(36)
                  .substr(2, 10)}`;

                setTimeout(renderblogUrl.bind(null, wrapperId, url), 0);
                return `<div id="${wrapperId}"></div>`;
                // return `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen></iframe>`;
              });
            }

            function renderblogUrl(wrapperId, url) {
              const el = document.querySelector(`#${wrapperId}`);
              el.innerHTML = `<iframe width="100%" height="400px" src="${url}" 
              frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" style="border:1 solid navy"
              ></iframe>`;
            }

            new Viewer({
              el: document.querySelector("#viewer"),
              viewer: true,
              plugins: [youtubePlugin, blogPlugin],
              initialValue: res.data.data.blogContents,
            });
          },
          (error) => console.log(error)
        );
      }
      commentList(
        this.blogContents,
        (response) => {
          this.items = response.data.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteComment(index) {
      this.items.splice(index, 1);
    },
    writeComment() {
      // this.items.push(comment);
      commentList(
        this.blogContents,
        (response) => {
          // console.log(response.data.data);
          this.items = response.data.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyComment(index, content) {
      this.items[index].commentContent = content;
    },
    checkLikeOrNot() {
      this.blogContentsLike.blogContentsId = this.$route.query.blogContentsId;
      this.blogContentsLike.uid = this.user.uid;
      contentsLikeOrNot(
        this.blogContentsLike,
        (res) => {
          if (res.data.msg === "yet") this.likeOrNot = false;
          else this.likeOrNot = true;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    clickLike() {
      console.log(this.likeOrNot);
      console.log(this.user);
      if (this.user.uid === "") alert("로그인 해주세요");
      else if (this.likeOrNot) {
        if (confirm("좋아요를 취소하시겠어요?")) {
          contentsUnlike(
            this.blogContentsLike,
            () => {
              this.likeOrNot = false;
              alert("좋아요가 취소되었습니다.");
            },
            () => {
              alert("다시 시도해주세요.");
            }
          );
        }
      } else {
        contentsLike(
          this.blogContentsLike,
          () => {
            this.likeOrNot = true;
            if (this.user.blogId == this.$route.query.blogContentsId)
              alert("자화자찬?");
          },
          () => {
            alert("다시 시도해주세요.");
          }
        );
      }
    },
    editPost() {
      if (confirm("Are you sure?")) {
        this.$router.push({
          name: "CreatePost",
          query: {
            blogId: this.blogContents.blogId,
            blogContentsId: this.blogContents.blogContentsId,
            blogContents: this.blogContents.blogContents,
            blogContentsTitle: this.blogContents.blogContentsTitle,
            blogContentsCover: this.blogContents.blogContentsCover,
            hashtags: this.blogContents.hashtags,
          },
        });
      }
    },
    deletePost() {
      if (confirm("Are you sure?")) {
        deleteContent(
          this.blogContents.blogId,
          this.blogContents.blogContentsId,
          () => {
            alert("삭제가 완료되었습니다.");
            this.$router.go(-1);
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    search(hashtag) {
      console.log(hashtag);
      this.$router.push({
        name: "SearchPage",
        query: {
          hashtagId: hashtag.hashtagId,
          hashtagContent: hashtag.hashtagContent,
        },
      });
    },
    UpperPage() {
      window.scrollTo(0,0)
    },
  },
};
</script>

<style>
.keepblack {
  color : black;
}
</style>
