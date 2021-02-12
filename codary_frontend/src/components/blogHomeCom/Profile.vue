<template>
  <v-container fluid>
    <v-sheet color="background" class='mt-0 pa-10 profilebox' id="user-profile-view" fluid tag="section">
      <v-row>
        <v-col class="d-flex flex-column">
          <v-img
            class="rounded-circle elevation-6 ml-8 mt-7"
            :src="this.loggedInUserData.profile"
            width="130"
            height="130"
          />
          <v-card-text class='ml-4 mt-5 mb-3'>
              <h6 class="text-h6 text--secondary">
                CEO / FOUNDER
              </h6>
              <h4 class="font-weight-medium text-h4 text--primary">
                {{ this.loggedInUserData.nickname }}
              </h4>
              <br>
              <span class="text--secondary">
                개발자를 위한 블로그를 개발중인 개발자
              </span>
          </v-card-text>
        </v-col>
      </v-row>
  

          <v-card-text>
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
            <div class="d-flex mb-16">
              <router-link class="noline" :to="'/createpost'">
                <v-btn color="dark" min-width="100" rounded outlined>
                  글 작성
                </v-btn>
              </router-link>
              <div class="pa-1"></div>
              <!-- 프로필수정 모달 -->
              <v-dialog v-model="dialog" persistent max-width="600px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="dark" rounded v-bind="attrs" v-on="on" outlined>
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
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            placeholder="name"
                            hint="example of persistent helper text"
                            persistent-hint
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                          <v-text-field placeholder="job" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                          <v-text-field placeholder="intro" required></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                          <v-img :src="this.loggedInUserData.profile" alt="profile"></v-img>
                        </v-col>
                        <v-col cols="12" sm="6">
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
                    <v-btn color="blue darken-1" text @click="dialog = false">
                      Close
                    </v-btn>
                    <v-btn color="blue darken-1" text @click="dialog = false">
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </v-card-text>
    </v-sheet>
  </v-container>
</template>

<script>
import { mapState } from 'vuex';
import { blogerLike, blogerUnlike, readBlogerlike } from '@/api/personal.js';
import { getuidCookie, getblogIdCookie } from '@/util/cookie.js';

export default {
  name: 'Profile',
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
  },
  data() {
    return {
      dialog: false,
      blogerLike: {
        uid: getuidCookie(),
        blogId: getblogIdCookie(),
      },
      blogerLikeflag: '',
    };
  },
  computed: {
    ...mapState(['loggedInUserData']),
  },
  methods: {
    /*  initUser(){
      this.blogerLike.user = getuidCookie();
      this.blogerLike.blogId = getblogIdCookie();
    },*/
    blgLike() {
      console.log('들어왔당');
      blogerLike(
        this.blogerLike,
        (response) => {
          if (response.data.msg === 'success') {
            this.blogerLikeflag = true;
            console.log('좋아요누름');
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    blgUnlike() {
      console.log('언팔각이다');
      blogerUnlike(
        this.blogerLike,
        (response) => {
          if (response.data.msg === 'success') {
            this.blogerLikeflag = false;
            console.log('언팔누름');
          }
        },
        (error) => {
          console.log(error);
        }
      );
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