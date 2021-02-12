<template>
  <v-card-text class="d-flex flex-column align-center">
    <v-textarea
      id="MemoSave"
      background-color="white"
      placeholder="메모하세요"
      auto-grow
      solo-inverted
      flat
      class="pa-0 ma-0"
      v-model="memodata.memoContent"
    ></v-textarea>
    <button id="button" @click="memoSave()">저장하기</button>
  </v-card-text>
</template>

<script>
import { mapState } from 'vuex';
import { writeMemo, changeMemo } from '@/api/memo.js';

export default {
  name: 'MemoInput',
  props: ['sendingChange'],
  data() {
    return {
      memodata: {
        memoContent: '',
        memoTime: '',
        // memoNum: '',
      },
    };
  },
  computed: {
    ...mapState(['loggedInUserData']),
  },
  watch: {
    sendingChange(val) {
      // console.log(val)
      this.memodata.memoContent = val.memoContent;
      this.memodata.memoTime = val.memoTime;
    },
  },
  methods: {
    memoSave() {
      if (this.memodata.memoContent === '') {
        return;
      }
      if (this.memodata.memoTime === '') {
        Object.assign(this.memodata, { memoId: this.loggedInUserData.memoId });
        writeMemo(
          this.memodata,
          () => {
            // console.log(response)
            // console.log('저장!')
            this.memodata.memoContent = '';
            this.memodata.memoTime = '';
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.sendingChange.memoContent = this.memodata.memoContent;
        Object.assign(this.memodata, { memoId: this.loggedInUserData.memoId });
        changeMemo(
          this.sendingChange,
          () => {
            // console.log(response)
            // console.log('수정!')
            this.memodata.memoContent = '';
            this.memodata.memoTime = '';
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
  outline:none;
  height: 40px;
  text-align: center;
  width: 130px;
  border-radius:40px;
  
  border: 2px solid #1ECD97;
  color: #1ECD97;
  letter-spacing:1px;
  text-shadow:0;

}

#button:hover {
  color:white;
  background-color: #1ECD97;
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
