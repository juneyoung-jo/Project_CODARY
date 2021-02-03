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
        v-model="memoText"
        
      ></v-textarea>
      <v-btn plain @click="memoSave">저장</v-btn>
  </v-card-text>
</template>

<script>
export default {
  name: 'MemoInput',
  data: () => ({
    menu: false,
    // 데이터 왜 일케 넣는지? 물어봐야지?
    memoText: '',
    memoTime: '',

  }),
  methods: {
    memoSave() {
      if ( this.memoTime == "") {
        // 메모 시간이 있으면 기존의 자료라서 수정으로 넘김
        const sendData = {
          "memoContent": this.memoText,
          "memoId": "2u1wQOyL8StR",
          // "memoNum": '',
          // "memoTime": "",
        }
        this.axios.post(`memo/`, sendData)
        .then(res => {
          console.log(res)
          // 글 작성된다음 어디로 보내지?
          // this.$router.push({name: 'MainPage'})
          this.memoTime == ""
          // 생성한뒤에 메모칸 초기화
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        const sendData = {
          "memoContent": this.memoText,
          "memoId": "2u1wQOyL8StR",
          // "memoNum": '',
          "memoTime": this.memoTime,
        }
        this.axios.post(`memo/`, sendData)
        .then(res => {
          console.log(res)
          // 글 작성된다음 어디로 보내지?
          // this.$router.push({name: 'MainPage'})

        })
        .catch(err => {
          console.log(err)
        })
      }
    }
  }

}
</script>
<style>


</style>