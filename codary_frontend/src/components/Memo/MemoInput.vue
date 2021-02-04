<template>
  <v-card-text>
      <v-textarea
        id="MemoSave"
        background-color="blue lighten-4"
        color="white"
        placeholder="메모하세요"
        auto-grow
        rounded
        solo-inverted
        flat
        class='memoinput'
        v-model="memodata.memoContent"
      ></v-textarea>
      <v-btn plain @click="memoSave()">저장</v-btn>
  </v-card-text>
</template>

<script>
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
        memoId: '2u1wQOyL8StR'
      }
    }
  },
  watch: {
    sendingChange(val) {
      // console.log(val)
      this.memodata.memoContent = val.memoContent
      this.memodata.memoTime = val.memoTime
    } 
  },
  methods: {
    memoSave() {
      if ( this.memodata.memoContent === '') {
        return
      }
      if ( this.memodata.memoTime === '') {
        writeMemo(
          this.memodata,
          () => {
            // console.log(response)
            // console.log('저장!')
            this.memodata.memoContent = ""
            this.memodata.memoTime = ""       
          },
          (error) => {
            console.log(error)
          }
        )
      } else {
        this.sendingChange.memoContent = this.memodata.memoContent

        changeMemo(
          this.sendingChange,
          () => {
            // console.log(response)
            // console.log('수정!')
            this.memodata.memoContent = ""
            this.memodata.memoTime = ""       
          },
          (error) => {
            console.log(error)
          }
        )
      }
    }
  }
}
</script>
<style>


</style>