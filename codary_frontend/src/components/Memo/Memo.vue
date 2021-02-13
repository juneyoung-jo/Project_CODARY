<template>
  <div id="settings-wrapper">

    <v-sheet id="blackBox" class="d-flex">
      <v-btn @click="move" id="blackBoxButton" color="primary" class="d-flex align-center pa-3 pb-3">
        <v-icon large>
         mdi-clipboard-multiple-outline
        </v-icon>
      </v-btn>
        <v-sheet v-if="isLogin" class="memopage">
          <MemoList class='memolist' @CHANGEMEMO="startchange"/>
          <MemoInput class='memoinput' :sendingChange='sendingChange'/>   
        </v-sheet>
        <div v-else class='d-flex flex-column align-center ma-16 pa-5 py-16'>
           <span style="font-size:50px">
            <font-awesome-icon :icon="['fas','sign-in-alt']"/>
           </span>
           <div class='py-2'></div>
          <!-- 사이즈 조절 물어보기 -->
          <h5>로그인이 필요합니다</h5>
        </div>
    </v-sheet>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import MemoInput from './MemoInput.vue'
import MemoList from './MemoList.vue'
// import { memoList } from '@/api/memo.js';

export default {
  components: { MemoInput,MemoList },
  name:'Memo',
  data () {
    return {
      memotoggle: true,
      // memoLists: [],
      // 이거 빼야됭,.., 나중에 로그인 되면 로그인에서 가져오는거로  
      sendingChange: {},

      buttonMassage: "왔다리"
    }
  },
  computed: {
    ...mapState([ 'isLogin' ]),    
    ...mapState([ 'loggedInUserData' ])    
  },

  created() {
    // if (this.isLogin === true) {
    //   memoList(
    //     this.loggedInUserData.memoId,
    //     (response) => {
    //       this.memoLists = response.data     
    //     },
    //     (error) => {
    //       console.log(error)
    //     }
    //   )      
    // }
  },

  methods: {
    // getmemoList() {
    //   if (this.isLogin === true) {
    //     memoList(
    //       this.loggedInUserData.memoId,
    //       (response) => {
    //         this.memoLists = response.data 
    //       },
    //       (error) => {
    //         console.log(error)
    //       }
    //     )
    //   }
    // },


    // toggle() {
    //   this.memotoggle = !this.memotoggle
    //   // console.log(this.memotoggle) True 일때가 Input, False가 List
    //   this.getmemoList()
    //   // console.log(this.loggedInUserData)
    // },

    
    startchange(item) {
      // console.log(item)
      this.sendingChange = item
      // this.toggle()
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
  position: fixed;
  z-index: 2;
  /* 네브바가 5번째 인덱스 */
  right: -282px;
  color: black;
  height: 100%;
  width: 282px;
  transition: 0.5s;
}

.activate {
  transition: all 0.5s ease;
  transform: translateX(-100%);
}

#blackBoxButton {
  z-index: 1;
  transition: 0.3s;
  width: 60px;
  height: 50px;
  border-radius: 5px;
  position: absolute;
  top: 30%;
  left: -20%;
}

/* .memopage {
  position : relative;
} */


.memolist {
  position : absolute;
}

.memoinput {
  position: absolute;
  bottom: 10%;
}

.goingOn {
  transition: 0.5s ease;
  transform: translateX(2%);
}
</style>