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

  }),
  methods: {
    memoSave() {
      const sendData = {
          "memoContent": this.memoText,
          "memoId": "2u1wQOyL8StR",
          // "memoNum": '',
          "memoTime": ""
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
</script>
<style>


</style>