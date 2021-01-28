<template>
    <v-app>
      <full-page ref="fullpage" :options="options">

      <section id="memo" class="section">
         <v-row 
          no-gutters
          align="center"
          class="mx-auto"
          justify="center"
          style="height:100%"
          src="https://www.nasa.gov/sites/default/files/thumbnails/image/pia23900.jpg"
        > 
          <v-container fill-height>
            <v-row>
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
            </v-row>
          </v-container>
        </v-row>
      </section>

      <section id="share" class="section">
        <v-row 
          no-gutters
          align="center"
          class="mx-auto"
          justify="center"
          style="height:100%"
        >
          <v-container fill-height>
            <v-row>
              <v-col
                class="text-center"
                cols="12"
                tag="h1"
              >
                <span
                :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                  class="font-weight-light"
                >
                  쉽게 작성하고, 
                </span>
                <br>
                <span
                  :class="[$vuetify.breakpoint.smAndDown ? 'display-1': 'display-2']"
                  class="font-weight-light"
                >
                  쉽게 공유하세요
                </span>
                <v-responsive
                  class="mx-auto title font-weight-light mb-1"
                  max-width="720"
                >
                <br>
                  Markdown, 동시편집, 코드복사
                </v-responsive>
              </v-col>
            </v-row>
          </v-container>
        </v-row>
      </section>
      <section id="tag" class="section">
        <v-row
          no-gutters
          align="center"
          class="mx-auto"
          justify="center"
          style="height:100%"
        >
          <v-container fill-height>
            <v-row>
              <v-col
                class="text-center"
                cols="12"
                tag="h1"
              >
                <span
                :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                  class="font-weight-light"
                >
                  태그를 
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
                  태그로 소통이 시작됩니다.
                </v-responsive>
                <v-responsive
                  class="mx-auto title font-weight-light mb-8"
                  max-width="720"
                >
                  태그를 이용해 도움을 청할수도, 커뮤니티를 만들 수도 있습니다.
                </v-responsive>
              </v-col>
            </v-row>
          </v-container>
        </v-row>
      </section>
      <section id="grass" class="section">
        <v-row
          no-gutters
          align="center"
          class="mx-auto"
          justify="center"
          style="height:100%"
        >
          <v-container fill-height>
            <v-row>
              <v-col
                class="text-center"
                cols="12"
                tag="h1"
              >
                <span
                :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                  class="font-weight-light"
                >
                  잔디를 심으세요 
                </span>
                <br>
              
                <v-responsive
                  class="mx-auto title font-weight-light mb-1"
                  max-width="720"
                >
                <br>
                  매일의 공부 기록을 눈으로 확인해보세요.
                </v-responsive>
              </v-col>
            </v-row>
          </v-container>
        </v-row>
      </section>
      <section id="stats" class="section">
        <v-parallax 
          src="https://assets.imgix.net/unsplash/bridge.jpg?auto=compress&w=900&h=600&fit=crop"
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

  </full-page>
  </v-app>
</template>

<script>

export default {

  name: 'MainPage',
  data () {
    return {
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
        options: {
          licenseKey: 'YOUR_KEY_HEERE',
          menu: '#menu',
          
          sectionsColor: ['']
        },
      }
    },
    methods: {
      componentsReady() {
        this.$refs.fullpage.init()
      }, 
    },
}
</script>

<style>
*{
  font-family: 'Raleway','Noto Sans KR', sans-serif;
  
}
#memo {
  background-position: center;
  background-size: unset;
  background-image: url("../assets/typewriter.jpg");
}
#share {
  background-position: center;
  background-size: unset;
  background-image: url("../assets/clouds.jpg");
}
#tag {
  background-position: center;
  background-size: unset;
  background-image: url("../assets/tag.jpg");
}
#grass {
  background-position: center;
  background-size: unset;
  background-image: url("../assets/grass.jpg");
}
</style>