<template>
  <v-card-text>
      <div>
        <v-list two-line v-for="item in items" :key="item.memoNum">
          <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
          <v-list-item-content id="item.memoNum">
            <v-list-item-subtitle>{{item.memoContent}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-btn>수정버튼</v-btn>
          <v-btn @click="deleteMemo(item)">삭제버튼</v-btn>
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
          
        })
        .catch(err => {
          console.log(err)
        })
      },

      deleteMemo(item) {
        console.log(item)
        // this.$router.push(``)
        // const deletingData = document.querySelector()
      }
    }
}
</script>

<style>

</style>