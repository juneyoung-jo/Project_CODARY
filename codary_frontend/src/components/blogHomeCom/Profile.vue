<template>
<div class='py-12'>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
  >
    <v-row>
      <v-col
        cols="12"
      >
        <v-img
          class="rounded-circle elevation-6"
          :src="photo"
          width="128"
        />
        <v-card-text>
          <h6 class="text-h6 mb-2 text--secondary">
            {{ job }}
          </h6>

          <h4 class="text-h4 mb-3 text--primary">
            {{ name }}
          </h4>

          <p class="text--secondary">
            {{ intro }}
          </p>
          <router-link class='noline' :to="'/createpost'">
            <v-btn
              class="mr-0"
              color="primary"
              min-width="100"
              rounded
            >
              글 작성
            </v-btn>
          </router-link>
        
          <!-- 프로필수정 모달 -->
      
            <v-dialog
              v-model="dialog"
              persistent
              max-width="600px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  rounded
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  프로필수정
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">User Profile</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="4"
                      >
                        <v-text-field
                          :placeholder="name"
                          hint="example of persistent helper text"
                          persistent-hint
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          :placeholder="job"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          :placeholder="intro"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                      >
                      <v-img :src="photo"></v-img>
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                      >
                        <v-file-input
                          accept="image/png, image/jpeg, image/bmp"
                          placeholder="Pick an avatar"
                          prepend-icon="mdi-camera"
                        ></v-file-input>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog = false"
                  >
                    Close
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog = false"
                  >
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
        </v-card-text>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>

import {mapState} from 'vuex'
  export default {
    name: 'Profile',
    computed:{
      ...mapState(['photo','name','job','intro'])
    },
    data: () => ({
     dialog: false,
    }),
    }
  
</script>
