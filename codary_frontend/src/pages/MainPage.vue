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
                cols="2"
              >
              </v-col>
              <v-col
                class="text-left pt-16 align-center"
                cols="4"
                tag="h1"
              >
                <span
                :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                  class="font-weight-bold"
                >
                  쉽게 작성하고, 
                </span>
                <br>
                <span
                  :class="[$vuetify.breakpoint.smAndDown ? 'display-1': 'display-2']"
                  class="font-weight-bold"
                >
                  쉽게 공유하세요
                </span>
                <v-responsive
                  class="font-weight-light"
                >
                <br>
                  Markdown, 코드 공유
                </v-responsive>
              </v-col>
              <v-col
               cols="4"
               class="justify-start align-start"
              >
                <v-img 
                  src="@/assets/share.png"
                >
                </v-img>
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
            <v-col
              cols="7"  
            >
              <v-carousel 
                hide-delimiters
                show-arrows
              >
                <v-carousel-item
                  v-for="(item,i) in cards"
                  :key="i"
                  :src="item.blogContentsCover"
                  :to="{
                    name: 'ViewPost',
                    query: {
                      blogId: item.blogId,
                      blogContentsId: item.blogContentsId,
                    },
                  }"
                >
                  <v-row
                    class="fill-height"
                    align="end"
                    justify="center"
                  >
                    <v-chip
                      filled
                      color="cyan"
                      class='mb-10'
                    >
                      {{item.blogContentsTitle}}
                    </v-chip>
                  </v-row>
                </v-carousel-item>
              </v-carousel>
            </v-col>
            <v-col
              cols="1"
            >
            </v-col>
            <v-col
              class="text-end"
              cols="2"
              tag="h1"
            >
              <span
              :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
                class="font-weight-bold"
              >
                태그를 
              </span>
              <br>
              <span
                :class="[$vuetify.breakpoint.smAndDown ? 'display-1': 'display-2']"
                class="font-weight-bold"
              >
                이용하세요
              </span>
              <v-responsive
                class="mx-auto title font-weight-light mb-1"
              >
              <br>
                태그로 소통이 시작됩니다.
              </v-responsive>
              <v-responsive
                class="mx-auto title font-weight-light"
              >
                태그를 이용해 도움을 청할수도,
              </v-responsive>
              <v-responsive
                class="mx-auto title font-weight-light"
              >
                커뮤니티를 만들 수도 있습니다.
              </v-responsive>
            </v-col>
     
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
                  class="font-weight-bold"
                >
                  잔디를 심으세요 
                </span>
                <br>
                <v-responsive
                  class="mx-auto title font-weight-light mb-16"
                  max-width="720"
                >
                <br>
                  매일의 공부 기록을 눈으로 확인해보세요.
                </v-responsive>
                 <v-sparkline
                  :value="value"
                  :gradient="gradient"
                  :smooth="radius || false"
                  :padding="padding"
                  :line-width="width"
                  :stroke-linecap="lineCap"
                  :gradient-direction="gradientDirection"
                  :fill="fill"
                  :type="type"
                  :auto-line-width="autoLineWidth"
                  auto-draw
                ></v-sparkline>
      
              </v-col>
            </v-row>

          </v-container>
        </v-row>
      </section>

  </full-page>
  </v-app>
</template>

<script>
import{ recommend } from '@/api/blogcontent.js';
const gradients = [
  ['#222'],
  ['#42b3f4'],
  ['red', 'orange', 'yellow'],
  ['purple', 'violet'],
  ['#00c6ff', '#F0F', '#FF0'],
  ['#f72047', '#ffd200', '#1feaea'],
]
export default {

  name: 'MainPage',
  created() {
    recommend(
      (response) => {
        this.cards = response.data
      },
      (error)=>{
        console.log(error);
      }
    )
  },
  data () {
    return {
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: 'round',
      gradient: gradients[5],
      value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradientDirection: 'top',
      gradients,
      fill: false,
      type: 'trend',
      autoLineWidth: false,
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
        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
        cards: [],
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
  background-image: url("../assets/memoback.png");
}
#share {
  background-position: center;
  background-size: unset;
}
#tag {
  background-position: center;
  background-size: unset;
}
#grass {
  background-position: center;
  background-size: unset;
}
</style>