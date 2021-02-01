<template>
<v-img 
    src='https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg'
    max-height="300"
    top
  >
  <v-row class='py-16'></v-row>
  <v-row class='py-8'></v-row>
    <v-row class="py-16">
      <v-col
        md="8"
      ></v-col>
      <v-col
        md="4"
        align="end"
      >
        <v-dialog
          transition="dialog-top-transition"
          max-width="600"
        >
          <template v-slot:activator="{on,attrs}">
            <v-btn
              v-bind="attrs"
              v-on="on"
            >
              커버수정
            </v-btn>
          </template>
          <v-card class='d-flex flex-column pa-8'>
            <h3 class='text-center py-4'>커버 사진 골라보세요</h3>
            <v-card
              class="text-center mb-4"
              v-for="(cover,idx) in covers"
              :key="idx"
            >
              <v-img :src="cover" @click="selectcover(cover)"></v-img>
            </v-card>
            <v-btn outlined>
              선택
            </v-btn>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </v-img>
</template>

<script>
import {mapState} from 'vuex'
  export default {
    name: 'SelectPostCover',
    computed:{
      ...mapState(['mycover'])
    },
    data: ()=> ({
       covers: [
            'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/afrc2021-0004-24.jpg',
            'https://www.nasa.gov/sites/default/files/thumbnails/image/nhq202005300065.jpg',
            'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss064e020569_0.jpg',
            'https://www.nasa.gov/sites/default/files/styles/full_width_feature/public/thumbnails/image/iss063e053998.jpg'
        ]
    }),
    methods:{
      selectcover(cover){
        // 여기도 axios가 들어가야...
        console.log(cover)
      }
    }

    
    }
</script>


<style>

</style>