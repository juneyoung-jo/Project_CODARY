<template>
  <v-card-text class="d-flex flex-column align-center ma-0 pa-0" style="bottom:10%">
    <v-textarea
      id="MemoSave"
      placeholder="메모하세요"
      solo-inverted
      hide-details
      light
      height="130px"
      background-color="white"
      color="black"
      class="pa-0 memoinputarea"
      v-model="memodata.memoContent"
      
    ></v-textarea>
    <button id="button" @click="memoSave()">저장하기</button>
  </v-card-text>
</template>

<script>
// import { mapState } from "vuex";
import { writeMemo, changeMemo } from "@/api/memo.js";
import { getmemoIdCookie } from "@/util/cookie.js";

export default {
  name: "MemoInput",
  // props: ["memoContent", "index"],
  data() {
    return {
      memodata: {
        memoId: "",
        memoContent: "",
        memoLink: "",
        memoNum: "",
      },
      index: "",
    };
  },
  methods: {
    initmemo(index, memoContent, memoNum) {
      // console.log("받은 데이터: " + memoContent);
      this.index = index;
      this.memodata.memoContent = memoContent;
      this.memodata.memoNum = memoNum;
    },
    memoSave() {
      // const button = document.querySelector("#button")
      this.memodata.memoId = getmemoIdCookie();
      // console.log("수정로그 찍기: " + this.index);
      // console.log(this.memodata.memoId);
      if (this.memodata.memoContent === "") {
        return;
      }
      if (this.index === "") {
        // console.log("저장");
        Object.assign(this.memodata, { memoId: this.memodata.memoId });
        // console.log(window.location.href);
        this.memodata.memoLink = window.location.href;
        // console.log(this.memodata);
        writeMemo(
          this.memodata,
          () => {
            // console.log(response)
            // console.log('저장!')
            this.$emit("CREATEMEMO");
            // setTimeout(function() { 
            //   button.classList.add( "validate", 0,
            //     button.classList.remove( "validate" )
            //   );
            // }, 300 );
            this.memodata.memoContent = "";
            this.memodata.memoLink = "";
            
          },
          
          (error) => {
            console.log(error);
          }
        );
      } else {
        Object.assign(this.memodata, { memoId: this.memodata.memoId });
        // console.log("수정");
        changeMemo(
          this.memodata,
          () => {
            // console.log(response)
            // console.log('수정!')
            alert("수정되었습니다.");
            this.$emit("UPDATEMEMO", this.index, this.memodata.memoContent);
            this.memodata.memoContent = "";
            this.memodata.memoLink = "";
            this.memodata.memoNum = "";
            this.index = "";
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style>

#button {
  outline: none;
  height: 40px;
  text-align: center;
  width: 130px;
  border-radius: 40px;
  content:"저장하기";
  border: 2px solid dodgerblue;
  color: dodgerblue;
  letter-spacing: 1px;
  text-shadow: 0;
}

#button:hover {
  color: white;
  background-color: dodgerblue;
}

.memoinputarea {
  color: black !important;
  width: 80%;
  
}

.validate {
  font-size:15px;
  color: white;
  background: dodgerblue;
  font-family:'FontAwesome';
  content:"\f00c";
  
}
/* button:active {
    //letter-spacing: 2px;
    letter-spacing: 2px ;
  },
  button:after {
    content:"SUBMIT";
  }

.onclic {
  width: 40px;
  border-color:gray;
  border-width:3px;
  font-size:0;
  border-left-color:green;
  animation: rotating 2s 0.25s linear infinite;

  onclic:after {
    content:"";
  }
 onclic:hover {
    color:green;
    background: white;
  }
},
.validate {
  font-size:13px;
  color: white;
  background: green;
  &:after {
    font-family:'FontAwesome';
    content:"\f00c";
  }
},

@keyframes rotating {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
} */
</style>
