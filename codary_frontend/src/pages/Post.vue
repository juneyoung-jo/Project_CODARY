<template>
  <div>
    <PostCover :blogContents="blogContents" />
    <v-container>
      <!-- <PostViewer :blogContents="blogContents" /> -->
      <v-sheet color='white'>
        <h1 class="py-10 pt-13 mt-8 pa-12 ml-9 pt-16 keepblack">{{ this.blogContents.blogContentsTitle }}</h1>
        <div class="pa-12 py-0 ml-8 keepblack">
          <v-chip outlined class="mr-2 keepblack">코딩초보</v-chip>
          <v-chip outlined class="mr-2 keepblack">도와주세요</v-chip>
          <v-chip outlined class="mr-2 keepblack">알고리즘</v-chip>
          <v-chip outlined class="keepblack">백준</v-chip>
        </div>
        <v-divider class='my-8 ml-16 mr-16 keepblack'></v-divider>
        <div class='ma-16 pa-4 my-10 mt-12'>
          <div id="viewer" />
          <div class='py-3'></div>
          <Profile :blogContents="blogContents" />
          <div>
            <v-btn align="center" plain outlined color="primary" @click="clickLike()">
              좋아요? {{ likeOrNot }}
            </v-btn>
            <v-btn
              v-if="this.isItMine"
              id="modify"
              align="center"
              class='ml-3 mr-3'
              outlined
              color="primary"
              @click="editPost()"
            >
              <font-awesome-icon :icon="['fas','edit']" class='mr-2'/> 
              수정
            </v-btn>
            <v-btn
              v-if="this.isItMine"
              id="delete"
              align="center"
              class="my-8"
              outlined
              color="primary"
              @click="deletePost()"
            >
              <font-awesome-icon :icon="['fas','trash-alt']" class='mr-2'/>
              삭제
            </v-btn>
          </div>
          <div class='py-4'></div>
        </div>

      </v-sheet>

      <div class="px-12">
        <CommentWrite @WRITECMT="writeComment" :blogContents="blogContents" />
        <Comment :items="items" @DELETECMT="deleteComment" @MODIFYCOMMENT="modifyComment" />
      </div>
      <div class='py-16'></div>
    </v-container>
  </div>
</template>

<script>
import PostCover from '../components/postCom/PostCover.vue';
import Profile from '../components/postCom/Profile.vue';
import Comment from '../components/postCom/comment/Comment.vue';
import CommentWrite from '../components/postCom/comment/CommentWrite.vue';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import { commentList } from '@/api/comment.js';
import { mapGetters } from 'vuex';
import { writeLog } from '@/api/blogContents.js';
import { getContent, contentsLikeOrNot, contentsLike, contentsUnlike } from '@/api/blogcontent.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';
import { deleteContent } from '@/api/blogcontent.js';

export default {
  components: { PostCover, Profile, Comment, CommentWrite },
  name: 'Post',
  data() {
    return {
      items: [],
      blogContents: {
        blogId: '',
        blogContents: '',
        blogContentsId: '',
        profile: '',
        nickname: '',
        commantCnt: '',
        blogContentsCover: '',
        blogDatetime: '',
        blogContentsTitle: '',
        blogContentsLike: '',
        blogContentsView: '',
      },
      user: {
        uid: '',
        blogId: '',
      },
      blogContentsLike: {
        uid: '',
        blogContentsId: '',
      },
      isItMine: false,
      likeOrNot: false,
    };
  },
  created() {
    this.initUser();
    this.getBlogContent();
    this.checkLikeOrNot();
  },
  computed: {
    ...mapGetters(['loggedInUserData']),
  },
  watch: {
    $route: function() {
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
            this.blogContents.blogContentsCover = response.data.data.blogContentsCover;
            this.blogContents.blogContentsTitle = response.data.data.blogContentsTitle;
            this.blogContents.blogContents = response.data.data.blogContents;
            this.blogContents.blogContentsLike = response.data.data.blogContentsLike;

            function youtubePlugin() {
              Viewer.codeBlockManager.setReplacer('youtube', (youtubeId) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0
                const arr = youtubeId.split('v=');
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
              Viewer.codeBlockManager.setReplacer('url', (url) => {
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
              el: document.querySelector('#viewer'),
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
            this.blogContents.blogContentsCover = res.data.blogContentsCover;
            this.blogContents.blogContentsTitle = res.data.blogContentsTitle;
            this.blogContents.blogContents = res.data.blogContents;

            function youtubePlugin() {
              Viewer.codeBlockManager.setReplacer('youtube', (youtubeId) => {
                // console.log(youtubeId);
                // Indentify multiple code blocks
                //https://www.youtube.com/watch?v=Dxt5WGd-ED0
                const arr = youtubeId.split('v=');
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
              Viewer.codeBlockManager.setReplacer('url', (url) => {
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
              el: document.querySelector('#viewer'),
              viewer: true,
              plugins: [youtubePlugin, blogPlugin],
              initialValue: res.data.blogContents,
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
          if (res.data.msg === 'yet') this.likeOrNot = false;
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
      if (this.user.uid === '') alert('로그인 해주세요');
      else if (this.likeOrNot) {
        if (confirm('좋아요를 취소하시겠어요?')) {
          contentsUnlike(
            this.blogContentsLike,
            () => {
              this.likeOrNot = false;
              alert('좋아요가 취소되었습니다.');
            },
            () => {
              alert('다시 시도해주세요.');
            }
          );
        }
      } else {
        contentsLike(
          this.blogContentsLike,
          () => {
            this.likeOrNot = true;
            if (this.user.blogId == this.$route.query.blogContentsId) alert('자화자찬?');
          },
          () => {
            alert('다시 시도해주세요.');
          }
        );
      }
    },
    editPost() {
      if (confirm('Are you sure?')) {
        this.$router.push({
          name: 'CreatePost',
          query: {
            blogId: this.blogContents.blogId,
            blogContentsId: this.blogContents.blogContentsId,
            blogContents: this.blogContents.blogContents,
            blogContentsTitle: this.blogContents.blogContentsTitle,
            blogContentsCover: this.blogContents.blogContentsCover,
          },
        });
      }
    },
    deletePost() {
      if (confirm('Are you sure?')) {
        deleteContent(
          this.blogContents.blogId,
          this.blogContents.blogContentsId,
          () => {
            alert('삭제가 완료되었습니다.');
            this.$router.go(-1);
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

<style>
.keepblack {
  color : black;
}
</style>
