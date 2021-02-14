<template>
 <!--내 블로그이다!!-->
  <v-container fluid v-if="this.isMe">
    <v-sheet color="background" class='mt-0 pa-10 profilebox' id="user-profile-view" fluid tag="section">
      <v-row>
        <v-col class="d-flex flex-column">
          <v-img
            class="rounded-circle elevation-6 ml-8 mt-7"
            :src="this.imgUrl"
            width="130"
            height="130"
          />
         
        </v-col>
      </v-row>

          <v-card-text>
            <h4 class="text-h4 mb-3 text--primary">
              {{ this.nickname }}
            </h4>
            <h6 class="text-h6 mb-2 text--secondary">{{ this.job }}</h6>
            <v-col cols="12">
              {{ this.intro}}
            </v-col>
            <div class="d-flex mb-16">
              <router-link class="noline" :to="'/createpost'">
                <v-btn color="dark" min-width="100" rounded outlined>
                  글 작성
                </v-btn>
              </router-link>
              <div class="pa-1"></div>

              <!-- 프로필 수정 모달 start  ###################################### -->
              <v-dialog v-model="dialog" persistent max-width="600px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="dark" rounded v-bind="attrs" v-on="on" outlined>
                    프로필수정
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">회원 정보</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col cols="12" sm="6" md="4">
                          <!-- 닉네임 -->
                          <v-text-field
                            placeholder="name"
                            v-model="nickname_"
                            hint="example of persistent helper text"
                            persistent-hint
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- 직업 -->
                        <v-col cols="12">
                          <v-text-field
                            v-model="job_"
                            placeholder="job"
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- 소개글 -->
                        <v-col cols="12">
                          <v-text-field
                            v-model="intro_"
                            placeholder="intro"
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- ############################################ -->
                        <!-- ############################################ -->
                        <!-- 이미지 -->
                        <v-col cols="12" sm="6">
                          <v-img :src="imgUrl_" alt="profile"></v-img>
                        </v-col>
                        <!-- 이미지 업로드 -->
                        <v-col cols="12" sm="6">
                          <v-file-input
                            accept="image/png, image/jpeg, image/bmp"
                            placeholder="Pick an avatar"
                            prepend-icon="mdi-camera"
                            @change="selectImg"
                          ></v-file-input>
                          <v-btn @click="upload">사진 업로드</v-btn>
                        </v-col>

                        <!-- ############################################ -->
                        <!-- ############################################ -->
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="close">
                      Close
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="updateInfo">
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <!-- 프로필 수정 모달 end  ###################################### -->
            </div>
          </v-card-text>
    </v-sheet>
  </v-container>

  <!--다른 사람 블로그이다!!-->
  <v-container fluid v-else>
    <v-sheet color="background" class='mt-0 pa-10 profilebox' id="user-profile-view" fluid tag="section">
      <v-row>
        <v-col class="d-flex flex-column">
          <v-img
            class="rounded-circle elevation-6 ml-8 mt-7"
            :src="this.otherimgUrl"
            width="130"
            height="130"
          />
         
        </v-col>
      </v-row>

          <v-card-text>
            <h4 class="text-h4 mb-3 text--primary">
              {{ this.othernickname }}
            </h4>
            <h6 class="text-h6 mb-2 text--secondary">{{ this.otherjob }}</h6>
          
            <v-col cols="12">
              {{ this.otherintro}}
            </v-col>
            <p class="text--secondary">
              <v-card flat class='mb-10'>
                <v-card-text>
                  <v-container fluid class="pa-0">
                    <v-row>
                      <v-col cols="6" sm="3">
                        팔로우
                        <template v-if="blogerLikeflag">
                          <v-btn icon color="pink" @click="blgUnlike()">
                            <v-icon>mdi-heart</v-icon>
                          </v-btn>
                        </template>
                        <template v-else>
                          <v-btn icon color="gray" @click="blgLike()">
                            <v-icon>mdi-heart</v-icon>
                          </v-btn>
                        </template>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
              </v-card>
            </p>
          </v-card-text>
    </v-sheet>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import { fileUpload } from "@/api/fileUpload.js";
import {
  blogerLike,
  blogerUnlike,
  readBlogerlike,
  getUserInfo,
  getOtherInfo,
  updateUserinfo,
} from "@/api/personal.js";
import { getuidCookie, getblogIdCookie } from "@/util/cookie.js";

export default {
  created() {
    this.initUser();
    this.checkblogerLike();
    // 1. uid를 가지고, 유저 프로필 정보를 읽어온다.
    getUserInfo(
      getuidCookie(),
      (response) => {
        console.log(response);
        this.nickname_ = this.nickname = response.data.info.nickname;
        this.job_ = this.job = response.data.info.job;
        this.intro_ = this.intro = response.data.info.intro;
        this.imgUrl_ = this.imgUrl = response.data.info.profile;
      },
      (error) => {
        console.log(error);
      }
    );
    this.OtherInfo();
  },
  data() {
    return {
      dialog: false,
      blogerLike: {
        uid: getuidCookie(),
        blogId: getblogIdCookie(),
      },
      blogerLikeflag: "",
      nickname: "",
      job: "",
      intro: "",
      imgUrl: "",

      nickname_: "",
      job_: "",
      intro_: "",
      imgUrl_: "",
      uploadFile: "",
      // profile: this.loggedInUserData.profile,
      user:{
        uid:getuidCookie(),
        blogId:getblogIdCookie(),
      },
      isMe:false,

      othernickname:"",
      otherjob:"",
      otherintro:"",
      otherimgUrl:"",

    };
  },
  computed: {
    ...mapState(["loggedInUserData"]),
  },
  watch: {
    uploadImg: function () {
      console.log("#파일 업로드 감지!!");
      console.log(this.uploadImg);
    },
    $route:function () {
      this.initUser();
      this.OtherInfo();
      this.checkblogerLike();
    }
  },
  methods: {
    initUser(){
      if(this.user.blogId===this.$route.query.blogId || typeof this.$route.query.blogId==='undefined'){
        this.isMe=true;
      }else{
        this.isMe=false;
      }
    },
    OtherInfo(){
      getOtherInfo(
        this.$route.query.blogId,
        (response) => {
          this.othernickname=response.data.nickname;
          this.otherjob=response.data.job;
          this.otherintro=response.data.intro;
          this.otherimgUrl=response.data.profile;
        },
        (error) => {
          console.log(error);
        }
      )
    },
    checkblogerLike(){
      this.blogerLike.blogId=this.$route.query.blogId,
      this.blogerLike.uid=getuidCookie(),
      readBlogerlike(
      this.blogerLike,
      (response) => {
        this.blogerLikeflag = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    },
    blgLike() {
      if (this.user.uid === "") alert("로그인 해주세요");
      else{
        this.blogerLike.blogId=this.$route.query.blogId,
        this.blogerLike.uid=getuidCookie(),
      
        blogerLike(
          this.blogerLike,
          (response) => {
            if (response.data.msg === "success") {
              this.blogerLikeflag = true;
              console.log("좋아요누름");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    blgUnlike() {
      this.blogerLike.blogId=this.$route.query.blogId,
      this.blogerLike.uid=getuidCookie(),
      blogerUnlike(
        this.blogerLike,
        (response) => {
          if (response.data.msg === "success") {
            this.blogerLikeflag = false;
            console.log("언팔누름");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    // 회원 정보수정
    updateInfo() {
      // 1. 수정할 회원 정보 객체 생성
      const info = {
        uid: getuidCookie(),
        nickname: this.nickname_,
        job: this.job_,
        intro: this.intro_,
        profile: this.imgUrl_,
      };
      // 2. 회원정보 수정 요청
      updateUserinfo(
        info,
        (response) => {
          console.log(response);
          this.nickname = this.nickname_;
          this.job = this.job_;
          this.intro = this.intro_;
          this.imgUrl = this.imgUrl_;
        },
        (error) => {
          console.log(error);
        }
      );
      this.dialog = false;
    },
    selectImg(img) {
      this.uploadFile = img;
    },
    upload() {
      if (this.uploadFile === "") {
        alert("이미지를 업로드 해주세요");
        return false;
      } else {
        const formData = new FormData();
        formData.append("file", this.uploadFile);
        fileUpload(
          formData,
          (response) => {
            if (response.data === null) return;
            this.imgUrl_ = response.data;
          },
          (error) => console.log(error)
        );
      }
    },
    getImgUrl() {
      return this.imgUrl;
    },
    // 정보 수정 취소
    close() {
      this.nickname_ = this.nickname;
      this.job_ = this.job;
      this.intro_ = this.intro;
      this.imgUrl_ = this.imgUrl;
      this.dialog = false;
    },
  },
};
</script>
<style>
.profilebox {
  box-shadow: 0 0px 2px rgba(0, 0, 0, 0.2);
  border-radius: 12px;
}
</style>