<template>
  <v-img :src="currentImg" max-height="300" top class="py-0 my-0">
    <v-row class="py-16">
      <v-col md="8"></v-col>
      <v-col md="4" align="end">
        <v-dialog transition="dialog-top-transition" max-width="600">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" v-show="myblogId === user.blogId">
              커버수정
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card class="d-flex flex-column">
              <p class="text-center">커버사진골라보세요</p>
              <v-card class="text-center mb-4" v-for="(cover, idx) in covers" :key="idx">
                <v-img :src="cover" @click="selectCover(cover)"></v-img>
              </v-card>
              <v-file-input
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Pick an avatar"
                prepend-icon="mdi-camera"
                @change="selectImage"
              ></v-file-input>

              <v-btn
                outlined
                @click="
                  {
                    close(dialog);
                  }
                "
              >
                선택
              </v-btn>
              <v-btn
                outlined
                @click="
                  {
                    dialog.value = false;
                  }
                "
              >
                취소
              </v-btn>
            </v-card>
          </template>
        </v-dialog>
      </v-col>
    </v-row>
  </v-img>
</template>
<script>
import { getBlogInfo, modifyBlogInfo } from '@/api/personal.js';
import { fileUpload } from '@/api/fileUpload.js';
import { getblogIdCookie } from '@/util/cookie.js';

export default {
  name: 'MyCover',
  computed: {},
  created() {
    this.initUser();
    this.getBlog();
    this.initMyBlogId();
  },
  watch: {
    $route: function() {
      this.initUser();
      this.getBlog();
      this.initMyBlogId();
    },
  },
  data: () => ({
    covers: [
      'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/afrc2021-0004-24.jpg',
      'https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg',
      'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
      'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss063e053998.jpg',
    ],
    currentImg: '',
    selectImg: '',
    uploadImg: '',
    myblogId: '',
    user: {
      uid: '',
      blogId: '',
    },
  }),
  methods: {
    initUser() {
      this.user.blogId = this.$route.query.blogId;
    },
    initMyBlogId() {
      this.myblogId = getblogIdCookie();
    },
    getBlog() {
      if (this.user.blogId === '') return;
      getBlogInfo(
        this.user.blogId,
        (response) => {
          this.currentImg = response.data.blogCover;
        },
        (error) => console.log(error)
      );
    },
    selectCover(cover) {
      this.selectImg = cover;
      // console.log(this.selectImg);
    },
    selectImage(img) {
      this.uploadImg = img;
      this.upload();
      // console.log(this.currentImg);
    },
    upload() {
      if (this.uploadImg === '') {
        alert('이미지를 올려주세요');
        return;
      }

      let formData = new FormData();
      formData.append('file', this.uploadImg);

      //엑시오스
      fileUpload(
        formData,
        (response) => {
          if (response.data === null) return;
          this.covers.push(response.data);
          this.uploadImg = response.data;
        },
        (error) => console.log(error)
      );
    },
    close(dialog) {
      if (this.selectImg === '' && this.uploadImg === '') {
        alert('이미지를 선택해 주세요');
        return;
      }
      // uploadImg 먼저 그다음 selectImg 순서
      if (this.user.blogId === '') return;
      if (this.uploadImg === '') {
        modifyBlogInfo(
          this.selectImg,
          this.myblogId,
          (response) => {
            if (response.data.msg === 'success') {
              this.currentImg = this.selectImg;
            }
          },
          (error) => console.log(error)
        );
      } else {
        modifyBlogInfo(
          this.uploadImg,
          this.myblogId,
          (response) => {
            if (response.data.msg === 'success') {
              this.currentImg = this.uploadImg;
            }
          },
          (error) => console.log(error)
        );
      }

      dialog.value = false;
    },
  },
};
</script>

<style></style>
