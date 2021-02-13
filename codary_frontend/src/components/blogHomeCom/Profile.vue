<template>
  <div class="py-12">
    <v-container id="user-profile-view" fluid tag="section">
      <v-row>
        <v-col cols="12">
          <v-img
            class="rounded-circle elevation-6"
            :src="this.loggedInUserData.profile"
            width="128"
          />

          <v-card-text>
            <h6 class="text-h6 mb-2 text--secondary">CEO / FOUNDER</h6>

            <h4 class="text-h4 mb-3 text--primary">
              {{ this.loggedInUserData.nickname }}
            </h4>

            <p class="text--secondary">
              <v-card flat>
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
            <div class="d-flex">
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
                            v-model="nickname"
                            hint="example of persistent helper text"
                            persistent-hint
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- 직업 -->
                        <v-col cols="12">
                          <v-text-field
                            v-model="job"
                            placeholder="job"
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- 소개글 -->
                        <v-col cols="12">
                          <v-text-field
                            v-model="intro"
                            placeholder="intro"
                            required
                          ></v-text-field>
                        </v-col>
                        <!-- ############################################ -->
                        <!-- ############################################ -->
                        <!-- 이미지 -->
                        <v-col cols="12" sm="6">
                          <v-img :src="imgUrl" alt="profile"></v-img>
                        </v-col>
                        <!-- 이미지 업로드 -->
                        <v-col cols="12" sm="6">
                          <v-file-input
                            v-model="uploadImg"
                            accept="image/png, image/jpeg, image/bmp"
                            placeholder="Pick an avatar"
                            prepend-icon="mdi-camera"
                            @change="selectImg"
                          ></v-file-input>
                        </v-col>
                        <v-btn @click="upload">사진 업로드</v-btn>
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
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { fileUpload } from "@/api/fileUpload.js";
import { blogerLike, blogerUnlike, readBlogerlike } from "@/api/personal.js";
import { getuidCookie, getblogIdCookie } from "@/util/cookie.js";

export default {
  created() {
    readBlogerlike(
      this.blogerLike,
      (response) => {
        console.log(response.data.data);
        this.blogerLikeflag = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
    this.initImgUrl();
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
      // profile: this.loggedInUserData.profile,
      imgUrl: "",
      uploadImg: [],
      uploadFile: "",
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
  },
  methods: {
    /*  initUser(){
      this.blogerLike.user = getuidCookie();
      this.blogerLike.blogId = getblogIdCookie();
    },*/
    blgLike() {
      console.log("들어왔당");
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
    },
    blgUnlike() {
      console.log("언팔각이다");
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
    updateInfo() {
      console.log("#updateUserInfo 호출");
      const info = {
        nickname: this.nickname,
        job: this.job,
        intro: this.intro,
        img: this.getImgUrl(),
      };
      console.log("유저 정보: " + info);
      console.log("이미지 정보: " + this.getImgUrl());
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
            this.imgUrl = response.data;
          },
          (error) => console.log(error)
        );
      }
    },
    getImgUrl() {
      return this.imgUrl;
    },
    initImgUrl() {
      this.imgUrl = this.loggedInUserData.profile;
    },
    close() {
      this.uploadImg = [];
      this.dialog = false;
    },
  },
};
</script>
