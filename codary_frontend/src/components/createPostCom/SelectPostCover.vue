<template>
  <v-img :src="currentImg" max-height="300" top>
    <v-row class="py-16"></v-row>
    <v-row class="py-6"></v-row>
    <v-row class="py-16">
      <v-col md="4" align="start">
        <v-dialog transition="dialog-top-transition" max-width="800px" max-height="80%">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" class="ml-4"> 커버수정 </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card class="d-flex flex-column pa-8">
              <h3 class="text-center pb-5">커버 사진 골라보세요</h3>
              
              <v-container>
              <v-row class="d-flex">
                <v-col  
                  cols="12"
                  md="6"
                  v-for="(cover, idx) in covers"
                  :key="idx"
                >


              <v-card class="text-center mb-4">
                <v-img :src="cover" @click="selectcover(cover)" class="selectCoverImg"></v-img>
              </v-card>

                </v-col>
              </v-row>
              </v-container>

              <v-file-input
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Pick an avatar"
                prepend-icon="mdi-camera"
                @change="selectImg"
              ></v-file-input>

              <v-btn
                outlined
                dense
                @click="
                  {
                    close(dialog);
                  }
                "
              >
                선택
              </v-btn>
            </v-card>
          </template>
        </v-dialog>
      </v-col>
    </v-row>
  </v-img>
</template>

<script>
import { mapState } from 'vuex';
import { fileUpload } from '@/api/fileUpload.js';


export default {
  name: 'SelectPostCover',
  props: ['blogContents'],
  computed: {
    ...mapState(['mycover']),
  },
  data() {
    return {
      covers: [
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/afrc2021-0004-24.jpg',
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss063e053998.jpg',
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
        'https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg',
        '@/assets/cover_sample.bigdata.png'
      ],
      currentImg:
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
      uploadImg: '',
      selectImage: '',
    };
  },
  created() {
    if (this.blogContents != null) this.init();
  },
  methods: {
    init() {
      this.currentImg = this.blogContents.blogContentsCover;
    },
    selectcover(cover) {
      this.selectImage = cover;
      alert('밑에 선택버튼을 눌러주세요!')
      // this.$emit('GETCOVER', cover);
      // console.log(cover);
    },
    selectImg(img) {
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
      if (this.selectImage === '' && this.uploadImg === '') {
        alert('이미지를 선택해 주세요');
        return;
      }

      if (this.uploadImg === '') {
        this.currentImg = this.selectImage;
      } else {
        this.currentImg = this.uploadImg;
      }

      this.$emit('GETCOVER', this.currentImg);
      dialog.value = false;
    },
  },
};
</script>

<style>
.selectCoverImg {
  cursor: pointer;
  transition: 0.2s;
}

.selectCoverImg:hover {
  border: 5px;
} 
</style>
