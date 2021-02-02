<template>
  <v-card-text>
      <div>
        <v-list two-line>
          <v-list-item-content>
            <v-list-item-title>Memo1</v-list-item-title>
            <v-list-item-subtitle>메모입니다</v-list-item-subtitle>
          </v-list-item-content>
          <v-divider></v-divider>
          <v-list-item-content>
            <v-list-item-title>Memo2</v-list-item-title>
            <v-list-item-subtitle>메모입니다2</v-list-item-subtitle>
          </v-list-item-content>
          <v-divider></v-divider>
          <v-list-item-content>
            <v-list-item-title>Memo3</v-list-item-title>
            <v-list-item-subtitle>메모입니다3</v-list-item-subtitle>
          </v-list-item-content>
        </v-list>
      </div>
  </v-card-text>
</template>


<script>
export default {
    name:'MemoList',
    data() {
      return {

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