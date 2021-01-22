<template>
    <v-app>
      <section id="memo">
        <v-row no-gutters>
          <v-img
            :min-height="'calc(100vh - ' + $vuetify.application.top + 'px)'"
            src="https://upload2.inven.co.kr/upload/2015/12/13/bbs/i13968242593.jpg"
          >
              <v-container fill-height>
                <v-row
                  align="center"
                  class="mx-auto"
                  justify="center"
                >
                  <v-col
                    class="text-center"
                    cols="12"
                    tag="h1"
                  >
                    <span
                    :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                      class="font-weight-light"
                    >
                      메모를 
                    </span>
                    <br>
                    <span
                      :class="[$vuetify.breakpoint.smAndDown ? 'display-1': 'display-2']"
                      class="font-weight-light"
                    >
                      이용하세요
                    </span>
                    <v-responsive
                      class="mx-auto title font-weight-light mb-1"
                      max-width="720"
                    >
                    <br>
                     귀찮게 메모장 켤 필요 없습니다.
                    </v-responsive>
                    <v-responsive
                      class="mx-auto title font-weight-light mb-8"
                      max-width="720"
                    >
                     현재 페이지 주소와 함께 메모가 저장됩니다.
                    </v-responsive>
                  </v-col>
                  <v-theme-provider dark>
                  <v-btn
                    class="align-self-end"
                    fab
                    outlined
                    @click="$vuetify.goTo('#share')"
                  >
                    <v-icon color="black" class="downicon">mdi-chevron-double-down</v-icon>
                  </v-btn>
                  </v-theme-provider>
                </v-row>
              </v-container>
          </v-img>
        </v-row>
      </section>

      <section id="share">
        <div class="py-12"></div>
        <v-container class="text-center">
            <h2
            :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
              class="font-weight-light"
            >
              쉽게 작성하고, 
            </h2>
            <h2
              :class="[$vuetify.breakpoint.smAndDown ? 'display-1': 'display-2']"
              class="font-weight-light"
            >
              쉽게 공유하세요
            </h2>
      
          
          <v-responsive
            class="mx-auto title font-weight-light mb-8"
            max-width="720"
          >
          <br>
            Markdown, 동시편집, 코드복사 
          </v-responsive>
          
          <v-theme-provider dark>
            <v-btn
              class="align-self-end"
              fab
              outlined
              @click="$vuetify.goTo('#tag')"
            >
              <v-icon color="black" class="downicon">mdi-chevron-double-down</v-icon>
            </v-btn>
          </v-theme-provider>

      
        </v-container>
        <div class="py-12"></div>
      </section>
      <section id="tag">
        <div class="py-12"></div>
        <v-container class="text-center">
            <h2
            :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
              class="font-weight-light"
            >
              태그를 이용하세요 
            </h2>
          <br>
          <v-responsive
            class="mx-auto title font-weight-light mb-1"
            max-width="720"
          >
      
            태그로 시작되는 소통
          </v-responsive>
          <v-responsive
            class="mx-auto title font-weight-light mb-1"
            max-width="720"
          >
  
            태그를 이용해 도움을 청할수도,
          </v-responsive>
          <v-responsive
            class="mx-auto title font-weight-light mb-1"
            max-width="720"
          >

            스터디 그룹을 만들 수도 있습니다.
          </v-responsive>
          
          <v-theme-provider dark>
            <v-btn
              class="align-self-end"
              fab
              outlined
              @click="$vuetify.goTo('#stats')"
            >
              <v-icon color="black" class="downicon">mdi-chevron-double-down</v-icon>
            </v-btn>
          </v-theme-provider>

      
        </v-container>
        <div class="py-12"></div>
      </section>
      <section id="stats">
        <v-parallax
          :height="$vuetify.breakpoint.smAndDown ? 700 : 500"
          src="https://images.unsplash.com/photo-1510915228340-29c85a43dcfe?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
        >
          <v-container fill-height>
            <v-row class="mx-auto">
              <v-col
                v-for="[value, title] of stats"
                :key="title"
                cols="12"
                md="3"
              >
                <div class="text-center">
                  <div
                    class="display-3 font-weight-black mb-4"
                    v-text="value"
                  ></div>
                  <div
                    class="title font-weight-regular text-uppercase"
                    v-text="title"
                  ></div>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-parallax>
      </section>
      <section id="blog">
        <div class="py-12"></div>
        <v-container>
          <h2 class="display-2 font-weight-bold mb-3 text-uppercase text-center">Blog</h2>
          <v-responsive
            class="mx-auto mb-12"
            width="56"
          >
            <v-divider class="mb-1"></v-divider>
            <v-divider></v-divider>
          </v-responsive>
          <v-row>
            <v-col
              v-for="({ src, text, title }, i) in articles"
              :key="i"
              cols="12"
              md="4"
            >
              <v-img
                :src="src"
                class="mb-4"
                height="275"
                max-width="100%"
              ></v-img>
              <h3
                class="font-weight-black mb-4 text-uppercase"
                v-text="title"
              ></h3>
              <div
                class="title font-weight-light mb-5"
                v-text="text"
              ></div>
              <v-btn
                class="ml-n4 font-weight-black"
                text
              >
                Continue Reading
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
        <div class="py-12"></div>
      </section>
      <v-sheet
        id="contact"
        color="#333333"
        dark
        tag="section"
        tile
      >  
      </v-sheet>
    </v-app>
</template>

<script>
export default {
  name: 'Home',
  data () {
      return {
        articles: [
          {
            src: 'https://images.unsplash.com/photo-1423784346385-c1d4dac9893a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80',
            title: 'Mobile first & Responsive',
            text: 'Phasellus lorem enim, luctus ut velit eget, convallis egestas eros. Sed ornare ligula eget tortor tempor, quis porta tellus dictum.',
          },
          {
            src: 'https://images.unsplash.com/photo-1475938476802-32a7e851dad1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80',
            title: 'Think outside the box',
            text: 'Nam ut leo ipsum. Maecenas pretium aliquam feugiat. Aenean vel tempor est, vitae tincidunt risus. Sed sodales vestibulum nibh.',
          },
          {
            src: 'https://images.unsplash.com/photo-1416339442236-8ceb164046f8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1892&q=80',
            title: 'Small changes, big difference',
            text: 'Vestibulum in dictum velit, in rhoncus nibh. Maecenas neque libero, interdum a dignissim in, aliquet vitae lectus. Phasellus lorem enim, luctus ut velit eget.',
          },
        ],
        features: [
          {
            icon: 'mdi-account-group-outline',
            title: 'Vibrant Community',
            text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto cupiditate sint possimus quidem atque harum excepturi nemo velit tempora! Enim inventore fuga, qui ipsum eveniet facilis obcaecati corrupti asperiores nam',
          },
          {
            icon: 'mdi-update',
            title: 'Frequent Updates',
            text: 'Sed ut elementum justo. Suspendisse non justo enim. Vestibulum cursus mauris dui, a luctus ex blandit. Lorem ipsum dolor sit amet consectetur adipisicing elit. qui ipsum eveniet facilis obcaecati corrupti consectetur adipisicing elit.',
          },
          {
            icon: 'mdi-shield-outline',
            title: 'Long-term Support',
            text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto cupiditate sint possimus quidem atque harum excepturi nemo velit tempora! Enim inventore fuga, qui ipsum eveniet facilis obcaecati corrupti asperiores nam',
          },
        ],
        stats: [
          ['24k', 'Github Stars'],
          ['330+', 'Releases'],
          ['1m', 'Downloads/mo'],
          ['5m', 'Total Downloads'],
        ],
      }
    }  

}
</script>

<style>

</style>