<template>
  <div id="settings-wrapper">

    <div id="blackBox" class="d-flex align-center justify-center">
      <button @click="move" id="blackBoxButton">왔다리</button>
      <p>보이나?</p>
      
      <h1>dasd</h1>
    </div>

    <v-card
      id="settings"
      class="py-2 px-4"
      color="rgba(0, 0, 0, .3)"
      dark
      flat
      link
      min-width="100"
      style="position: fixed; top: 23%; right: -35px; border-radius: 8px; z-index: 1;"
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
      rounded=xl
    >
      <v-card
        class="text-center mb-0"
        width="300"
      >
      <div class="py-3 d-flex flex-column">
        <div class="d-flex align-center justify-end">
          <strong class="mr-15 py-3">MEMO</strong>
          <v-btn 
            @click="toggle"
            plain
          >
            <font-awesome-icon :icon="['fas', 'bars']" v-show="memotoggle"/>
            <font-awesome-icon :icon="['fas', 'pencil-alt']" v-show="!memotoggle"/>
          </v-btn>
        </div>
          <MemoInput v-show="memotoggle" :sendingChange='sendingChange'/>   
          <MemoList v-show="!memotoggle" :memoLists='memoLists' @CHANGEMEMO="startchange"/>
        </div>
      </v-card>
    </v-menu>

  </div>
</template>

<script>
import {mapState} from 'vuex'
import MemoInput from './MemoInput.vue'
import MemoList from './MemoList.vue'
import { memoList } from '@/api/memo.js';

export default {
  components: { MemoInput,MemoList },
  name:'Memo',
  data () {
    return {
      memotoggle: true,
      memoLists: [],
      // 이거 빼야됭,.., 나중에 로그인 되면 로그인에서 가져오는거로  
      sendingChange: {},
    
    }
  },
  computed: {
    ...mapState([ 'loggedInUserData' ])    
  },

  created() {
    memoList(
      this.loggedInUserData.memoId,
      (response) => {
        this.memoLists = response.data     
      },
      (error) => {
        console.log(error)
      }
    )      
  },

  methods: {
    getmemoList() {
      memoList(
        this.loggedInUserData.memoId,
        (response) => {
          this.memoLists = response.data 
        },
        (error) => {
          console.log(error)
        }
      )
    },


    toggle() {
      this.memotoggle = !this.memotoggle
      // console.log(this.memotoggle) True 일때가 Input, False가 List
      this.getmemoList()
      // console.log(this.loggedInUserData)
    },

    
    startchange(item) {
      // console.log(item)
      this.sendingChange = item
      this.toggle()
    },

    move() {
      const target = document.querySelector('#blackBox')
      const subtarget = document.querySelector('#blackBoxButton')

      subtarget.classList.contains('goingOn') ? subtarget.classList.remove('goingOn') : subtarget.classList.add('goingOn')
      setTimeout(function(){
        target.classList.contains('activate') ? target.classList.remove('activate') : target.classList.add('activate')
      }, 500)
      
    }
  }     
}
</script>

<style>
@import '~vue-neumorphism-element/dist/vue-neumorphism-element.css';

#blackBox {
  background-color: wheat;
  position: fixed;
  z-index: 2;
  right: -282px;
  color: black;
  height: 100%;
  width: 282px;
  transition: 0.5s;
}

.activate {
  transition: 0.5s;
  box-shadow: 0 0 5px 0 black;
  transform: translateX(-100%);
}

#blackBoxButton {
  transition: 0.5s;
  width: 50px;
  background-color: crimson;

  position: relative;
  left: -40%
}

.goingOn {
  transition: 0.5s;
  background-color: blueviolet;
}
</style>