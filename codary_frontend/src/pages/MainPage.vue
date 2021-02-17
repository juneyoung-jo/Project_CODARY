<template>
  <full-page ref="fullpage" :options="options">
    <section id="memo" class="section">
      <v-row
        no-gutters
        align="center"
        class="mx-auto"
        justify="center"
        style="height: 100%"
      >
        <v-container fill-height class="page">
          <v-sheet class="downbutton">
            <div
              class="scroll-down"
              @click="$refs.fullpage.api.moveSectionDown()"
            ></div>
          </v-sheet>
          <v-row class="d-flex mb-16 pb-16">
            <v-col cols="1"></v-col>
            <v-col class="d-flex flex-column" cols="6">
              <v-sheet
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-2' : 'display-3',
                ]"
                class="font-weight-bold text-right Scodarymain pa-4"
                style="color: white"
                color="secondary"
              >
                codary
              </v-sheet>
            </v-col>
            <v-col class="d-flex flex-column mt-8" cols="5">
              <v-col cols="1"></v-col>
              <v-sheet class="font-weight-bold headline" color="background">
                개발하는 당신을 위한 코딩 다이어리
              </v-sheet>
            </v-col>
          </v-row>
        </v-container>
      </v-row>
    </section>
    <section id="memo" class="section">
      <v-row
        no-gutters
        align="center"
        class="mx-auto"
        justify="center"
        style="height: 100%"
      >
        <v-container fill-height class="page">
          <v-sheet class="downbutton">
            <div
              class="scroll-down"
              @click="$refs.fullpage.api.moveSectionDown()"
            ></div>
          </v-sheet>
          <v-row no-gutters class="d-flex">
            <v-col class="text-center" cols="12" tag="h3">
              <span
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2',
                ]"
                class="font-weight-bold"
              >
                메모를
              </span>
              <br />
              <span
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2',
                ]"
                class="font-weight-bold"
              >
                이용하세요
              </span>
              <v-responsive class="font-weight-light">
                <br />
                귀찮게 메모장 켤 필요 없습니다.
              </v-responsive>
              <v-responsive class="font-weight-light">
                현재 페이지 주소와 함께 메모가 저장됩니다.
              </v-responsive>
            </v-col>
            <v-col cols="3" class="arrowcontained">
            <svg class='arrowbox' version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 10 220.1 10.9" xml:space="preserve">
                <path stroke-linecap="round" stroke-linejoin="round" class="st0 draw-arrow" d="M11.3,2.5c-5.8,5-8.7,12.7-9,20.3s2,15.1,5.3,22c6.7,14,18,25.8,31.7,33.1" />
                <path stroke-linecap="round" stroke-linejoin="round" class="draw-arrow tail-1" d="M40.6,78.1C39,71.3,37.2,64.6,35.2,58" />
                <path stroke-linecap="round" stroke-linejoin="round" class="draw-arrow tail-2" d="M39.8,78.5c-7.2,1.7-14.3,3.3-21.5,4.9" />
              </svg>
            </v-col>
          </v-row>
        </v-container>
      </v-row>
    </section>
    <section id="tag" class="section page">
      <v-sheet class="downbutton">
        <div
          class="scroll-down"
          @click="$refs.fullpage.api.moveSectionDown()"
        ></div>
      </v-sheet>
      <v-row
        no-gutters
        align="center"
        class="mx-auto"
        justify="center"
        style="height: 100%"
      >
        <v-col cols="7" class="d-flex flex-column pb-16">
          <v-carousel hide-delimiters show-arrows-on-hover>
            <v-carousel-item
              v-for="(item, i) in cards"
              :key="i"
              :src="item.blogConetentsCover"
              :to="{
                name: 'ViewPost',
                query: {
                  blogId: item.blogId,
                  blogContentsId: item.blogContentsId,
                },
              }"
            >
              <v-row class="fill-height" align="end" justify="center">
                <v-item-group>
                  <v-item
                    v-for="(hashtag, i) in item.hashtags"
                    :key="i"
                    class="ma-1"
                  >
                    <v-chip filled color="primary" class="mb-10">
                      {{ hashtag.hashtagContent }}
                    </v-chip>
                  </v-item>
                </v-item-group>
              </v-row>
            </v-carousel-item>
          </v-carousel>
        </v-col>
        <v-col cols="1"> </v-col>
        <v-col class="text-end" cols="2" tag="h3">
          <span
            :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
            class="font-weight-bold"
          >
            태그를
          </span>
          <br />
          <span
            :class="[$vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2']"
            class="font-weight-bold"
          >
            이용하세요
          </span>
          <v-responsive class="font-weight-light">
            <br />
            태그로 소통이 시작됩니다.
          </v-responsive>
          <v-responsive class="font-weight-light">
            태그를 이용해 도움을 청할수도,
          </v-responsive>
          <v-responsive class="font-weight-light">
            커뮤니티를 만들 수도 있습니다.
          </v-responsive>
          <div class="py-16"></div>
        </v-col>
      </v-row>
    </section>

    <section id="share" class="section">
      <v-row no-gutters style="height: 100%">
        <v-container fill-height class="page">
          <v-sheet class="downbutton">
            <div
              class="scroll-down"
              @click="$refs.fullpage.api.moveSectionDown()"
            ></div>
          </v-sheet>
          <v-row>
            <v-col cols="2"></v-col>
            <v-col cols="4" tag="h3">
              <div class="py-16"></div>
              <span
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2',
                ]"
                class="font-weight-bold"
              >
                쉽게 작성하고,
              </span>
              <br />
              <span
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2',
                ]"
                class="font-weight-bold"
              >
                쉽게 공유하세요
              </span>
              <v-responsive class="font-weight-light">
                <br />
                Markdown, 코드 공유
              </v-responsive>
            </v-col>
            <v-col cols="4" class="d-flex flex-column pb-16 mb-16">
              <v-img src="@/assets/share.png"></v-img>
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
        style="height: 100%"
      >
        <v-container fill-height>
          <v-row class="d-flex">
            <v-col class="text-center" cols="12">
              <span
                :class="[
                  $vuetify.breakpoint.smAndDown ? 'display-1' : 'display-2',
                ]"
                class="font-weight-bold"
              >
                잔디를 심으세요
              </span>
              <br />
              <v-responsive
                class="mx-auto font-weight-light mb-16"
                max-width="720"
              >
                <br />
                매일의 공부 기록을 눈으로 확인해보세요.
              </v-responsive>
            </v-col>
            <v-col>
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
                class="mt-16 pb-16"
              ></v-sparkline>
            </v-col>
          </v-row>
        </v-container>
      </v-row>
    </section>
  </full-page>
</template>

<script>
import { recommend } from "@/api/blogcontent.js";
const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"],
];
export default {
  name: "MainPage",
  created() {
    recommend(
      (response) => {
        this.cards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: "round",
      gradient: gradients[5],
      value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradientDirection: "top",
      gradients,
      fill: false,
      type: "trend",
      autoLineWidth: false,
      features: [
        {
          icon: "mdi-account-group-outline",
          title: "Vibrant Community",
          text:
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto cupiditate sint possimus quidem atque harum excepturi nemo velit tempora! Enim inventore fuga, qui ipsum eveniet facilis obcaecati corrupti asperiores nam",
        },
        {
          icon: "mdi-update",
          title: "Frequent Updates",
          text:
            "Sed ut elementum justo. Suspendisse non justo enim. Vestibulum cursus mauris dui, a luctus ex blandit. Lorem ipsum dolor sit amet consectetur adipisicing elit. qui ipsum eveniet facilis obcaecati corrupti consectetur adipisicing elit.",
        },
        {
          icon: "mdi-shield-outline",
          title: "Long-term Support",
          text:
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Iusto cupiditate sint possimus quidem atque harum excepturi nemo velit tempora! Enim inventore fuga, qui ipsum eveniet facilis obcaecati corrupti asperiores nam",
        },
      ],
      stats: [
        ["24k", "Github Stars"],
        ["330+", "Releases"],
        ["1m", "Downloads/mo"],
        ["5m", "Total Downloads"],
      ],
      items: [
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
        },
      ],
      cards: [],
      options: {
        menu: "#menu",
        sectionsColor: [""],
      },
    };
  },
  methods: {
    componentsReady() {
      this.$refs.fullpage.init();
    },
  },
};
</script>

<style>
#memo {
  background-position: center;
  background-size: unset;
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
.page {
  position: relative;
}
.downbutton {
  position: absolute;
  bottom: 14%;
  left: 48%;
  color: red;
  cursor: pointer;
}

.scroll-down {
  position: absolute;
  left: 40%;
  bottom: 10px;
  display: block;
  text-align: center;
  font-size: 20px;
  z-index: 100;
  text-decoration: none;
  text-shadow: 0;
  width: 50px;
  height: 50px;
  border-bottom: 2px solid;
  border-right: 2px solid;
  z-index: 9;
  left: 50%;
  -webkit-transform: translate(-50%, 0%) rotate(45deg);
  -moz-transform: translate(-50%, 0%) rotate(45deg);
  transform: translate(-50%, 0%) rotate(45deg);
  -webkit-animation: fade_move_down 1s ease-in-out infinite;
  -moz-animation: fade_move_down 1s ease-in-out infinite;
  animation: fade_move_down 1s ease-in-out infinite;
}

/*animated scroll arrow animation*/
@-webkit-keyframes fade_move_down {
  0% {
    -webkit-transform: translate(0, -10px) rotate(45deg);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    -webkit-transform: translate(0, 10px) rotate(45deg);
    opacity: 0;
  }
}
@-moz-keyframes fade_move_down {
  0% {
    -moz-transform: translate(0, -10px) rotate(45deg);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    -moz-transform: translate(0, 10px) rotate(45deg);
    opacity: 0;
  }
}
@keyframes fade_move_down {
  0% {
    transform: translate(0, -10px) rotate(45deg);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: translate(0, 10px) rotate(45deg);
    opacity: 0;
  }
}

.arrowcontained{
  position: absolute;
  right: -8%;
  top: 25%;
}
.arrowbox {
  width: 600px;
  height: 600px; 
  margin: 0 auto;
}
.draw-arrow {  
  stroke-width: 5;
  stroke: dodgerblue; 
  fill: none;
  stroke-dasharray: 400;
  stroke-dashoffset: 400; 
  animation-duration: 2.0s;
  animation-fill-mode: forwards;
  animation-name: draw;
  transform: rotate(270deg);
  animation: draw 2s ease-in-out infinite;
}
.tail-1 {
  animation-delay: 0.8s;
}
.tail-2 {
  animation-delay: 1.2s;
}

@keyframes draw {
 to {
    stroke-dashoffset: 0;
  }
}
</style>
