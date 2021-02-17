<template>
  <div id="settings-wrapper">
    <v-sheet id="blackBox" class="d-flex">
      <v-btn
        @click="move"
        id="blackBoxButton"
        color="primary"
        class="d-flex align-center pa-3 pb-3"
      >
        <v-icon large> mdi-clipboard-multiple-outline </v-icon>
      </v-btn>
      <v-sheet v-if="isLogin" class="memopage">
        <MemoList
          class="memolist"
          :listData="listData"
          @DELETEMEMO="deleteMemo"
          @MODIFYMEMO="modifyMemo"
        />
        <MemoInput
          class="memoinput"
          ref="memoinput"
          @UPDATEMEMO="updateMemo"
          @CREATEMEMO="createMemo"
        />
      </v-sheet>
      <div v-else class="d-flex flex-column align-center ma-16 pa-5 py-16">
        <span style="font-size: 70px">
          <font-awesome-icon :icon="['fas', 'sign-in-alt']" />
        </span>
        <div class="py-2"></div>
        <h5>로그인이 필요합니다</h5>
      </div>
    </v-sheet>
  </div>
</template>

<script>
import { mapState } from "vuex";
import MemoInput from "./MemoInput.vue";
import MemoList from "./MemoList.vue";
import { getmemoIdCookie } from "@/util/cookie.js";
import { memoList } from "@/api/memo.js";

export default {
  components: { MemoInput, MemoList },
  name: "Memo",
  data() {
    return {
      memotoggle: true,
      // memoLists: [],
      // 이거 빼야됭,.., 나중에 로그인 되면 로그인에서 가져오는거로
      // sendingChange: {},
      memodata: {},
      listData: [],
      buttonMassage: "왔다리",
      memoId: "",
      // index: "",
      // memoContent: "",
    };
  },
  computed: {
    ...mapState(["isLogin"]),
    ...mapState(["loggedInUserData"]),
  },

  created() {
    this.getMemoList();
  },

  methods: {
    getMemoList() {
      this.memoId = getmemoIdCookie();
      if (this.memoId !== "") {
        memoList(
          this.memoId,
          (response) => {
            // console.log("메모 정보: " + response.data);
            this.listData = response.data;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    deleteMemo(index) {
      this.listData.splice(index, 1);
    },
    modifyMemo(index, memoContent, memoNum) {
      // this.memoContent = memoContent;
      // this.index = index;
      this.$refs.memoinput.initmemo(index, memoContent, memoNum);
    },
    createMemo() {
      this.getMemoList();
    },
    updateMemo(index, memoContent) {
      this.listData[index].memoContent = memoContent;
    },

    move() {
      const target = document.querySelector("#blackBox");
      const subtarget = document.querySelector("#blackBoxButton");

      subtarget.classList.contains("goingOn")
        ? subtarget.classList.remove("goingOn")
        : subtarget.classList.add("goingOn");

      setTimeout(function () {
        target.classList.contains("activate")
          ? target.classList.remove("activate")
          : target.classList.add("activate");
      }, 500);
    },
  },
};
</script>

<style>
@import "~vue-neumorphism-element/dist/vue-neumorphism-element.css";

#blackBox {
  position: fixed;
  z-index: 2;
  /* 네브바가 5번째 인덱스 */
  right: -330px;
  color: black;
  height: 100%;
  width: 330px;
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
  top: 25%;
  left: -20%;
}

/* .memopage {
  position : relative;
} */

.memolist {
  position: absolute;
  height: 68%;
  width: 100%;
}

.memoinput {
  position: absolute;
  bottom: 15%;
  height: 19%;
}

.goingOn {
  transition: 0.5s ease;
  transform: translateX(15%);
}
</style>