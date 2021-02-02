<template>
  <v-card-text>
      <div>
        <v-list two-line v-for="item in items" :key="item.memoNum">
          <v-list-item-content>
            <!-- <v-list-item-title>{{item.memoNum}}</v-list-item-title> -->
            <v-list-item-subtitle>{{item.memoContent}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-btn>수정버튼</v-btn>
          <v-btn @click="deleteMemo()">삭제버튼</v-btn>
          <v-divider></v-divider>
        </v-list>
      </div>
  </v-card-text>
</template>


<script>
export default {
    name:'MemoList',
    data() {
      return {
        items: [],

      }
    },
    created() {
      this.getMemoList()
    },
    methods: {
      getMemoList() {
        const memoId = '2u1wQOyL8StR'
        this.axios.get(`memo/${memoId}/`)
        .then(res => {
          console.log(res)
          this.items = res.data
          // 글 작성된다음 어디로 보내지?
          // this.$router.push({name: 'MainPage'})
        })
        .catch(err => {
          console.log(err)
        })
      },

      // deleteMemo(e) {
        // console.log(e)
        // const deletingData = document.querySelector
      // }
    }
}
</script>

<style>

</style>