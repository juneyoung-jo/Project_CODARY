<template>
  <div id="settings-wrapper">
    <v-card
      id="settings"
      class="py-2 px-4"
      color="rgba(0, 0, 0, .3)"
      dark
      flat
      link
      min-width="100"
      style="position: fixed; top: 200px; right: -35px; border-radius: 8px; z-index: 1;"
    >
      <v-icon large>
        mdi-clipboard-multiple-outline
      </v-icon>
    </v-card>
      <v-menu
        :close-on-content-click="false"
        activator="#settings"
        bottom
        content-class="v-settings"
        left
        nudge-left="8"
        offset-x
        origin="top right"
        transition="scale-transition"
      >
        <v-card
          class="text-center mb-0"
          width="300"
        >
          <div class="py-3 d-flex flex-column">
            <div class="d-flex px-3 align-center justify-end">
              <strong class="mr-13 py-3">MEMO</strong>
                <NeuElement
                  tag="v-btn"
                  type="flat"
                  height="50px"
                  width="50px"
                  radius="50%"
                  color="#d2d2d2"
                  :distance="5"
                  :intensity="0.15"
                  :blur="10"
                  @onclick="toggle"
                >

                <font-awesome-icon :icon="['fas', 'bars']" v-show="memotoggle"/>
                <font-awesome-icon :icon="['fas', 'pencil-alt']" v-show="!memotoggle"/>
      
                </NeuElement>
            </div>
              <MemoInput v-show="memotoggle"/>   
              <MemoList v-show="!memotoggle"/>
          </div>
        </v-card>
      </v-menu>

  </div>
</template>

<script>
import MemoInput from './MemoInput.vue'
import MemoList from './MemoList.vue'
import NeuElement from "vue-neumorphism-element";
export default {
  components: { MemoInput,MemoList,NeuElement },
    name:'Memo',
    data () {
        return {
          memotoggle: true,
        }
    },
    methods: {
      toggle(){
        this.memotoggle = !this.memotoggle
        console.log(this.memotoggle)
      }
    }
    
}
</script>

<style>
@import '~vue-neumorphism-element/dist/vue-neumorphism-element.css';
</style>