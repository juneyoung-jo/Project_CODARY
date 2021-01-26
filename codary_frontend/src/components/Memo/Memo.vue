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
        <div class="py-3"></div>
        <strong class="m-3">MEMO</strong>
        <v-btn @click="toggle">
            <font-awesome-icon :icon="['fas', 'bars']" v-show="memotoggle"/>
            <font-awesome-icon :icon="['fas', 'pencil-alt']" v-show="!memotoggle"/>
        </v-btn>
            <MemoInput v-show="memotoggle"/>   
            <MemoList v-show="!memotoggle"/>
      </v-card>
    </v-menu>
  </div>
</template>

<script>
import MemoInput from './MemoInput.vue'
import MemoList from './MemoList.vue'
export default {
  components: { MemoInput,MemoList },
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

</style>