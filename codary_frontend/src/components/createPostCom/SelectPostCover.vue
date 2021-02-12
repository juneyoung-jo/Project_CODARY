<template>
  <v-img :src="currentImg" max-height="300" top>
    <v-row class="py-16"></v-row>
    <v-row class="py-8"></v-row>
    <v-row class="py-16">
      <v-col md="4" align="start">
        <v-dialog transition="dialog-top-transition" max-width="600">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" class="ml-3">
              커버수정
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card class="d-flex flex-column pa-8">
              <h3 class="text-center py-4">커버 사진 골라보세요</h3>
              <v-card class="text-center mb-4" v-for="(cover, idx) in covers" :key="idx">
                <v-img :src="cover" @click="selectcover(cover)"></v-img>
              </v-card>
              <v-file-input
                accept="image/png, image/jpeg, image/bmp"
                placeholder="Pick an avatar"
                prepend-icon="mdi-camera"
                @change="selectImg"
              ></v-file-input>

              <v-btn color="success" dark small @click="upload">
                Upload
                <v-icon right dark>mdi-cloud-upload</v-icon>
              </v-btn>
              <v-btn
                outlined
                @click="
                  {
                    dialog.value = false;
                    close();
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
  computed: {
    ...mapState(['mycover']),
  },
  data() {
    return {
      covers: [
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/afrc2021-0004-24.jpg',
        'https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg',
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss063e053998.jpg',
      ],
      currentImg:
        'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
      uploadImg: '',
    };
  },
  methods: {
    selectcover(cover) {
      this.currentImg = cover;
      // this.$emit('GETCOVER', cover);
      // console.log(cover);
    },
    selectImg(img) {
      this.uploadImg = img;
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
          this.currentImg = response.data;
        },
        (error) => console.log(error)
      );
    },
    close() {
      if (this.currentImg === '') {
        alert('이미지를 선택해 주세요');
        return;
      }
      this.$emit('GETCOVER', this.currentImg);
    },
  },
};
</script>

<style></style>
