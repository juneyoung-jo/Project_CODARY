<template>
  <v-card-text>
      <div>
        <v-list two-line v-for="(item,index) in this.memoLists" :key="item.memoNum">
          <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
          <v-list-item-content id="item.memoNum">
            <v-list-item-subtitle>{{item.memoContent}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-btn @click="changingMemo(item)">수정버튼</v-btn>
          <v-btn @click="deletingMemo(item, index)">삭제버튼</v-btn>
          <v-divider></v-divider>
        </v-list>
      </div>
  </v-card-text>
</template>


<script>
import { deleteMemo } from '@/api/memo.js';

export default {
    name:'MemoList',
    props: ['memoLists'],
    data() {
      return {

      }
    },

    methods: {
      deletingMemo(item, index) {
        deleteMemo(
          item,
          () => {
            // console.log(response)
            // console.log('삭제')
            this.memoLists.splice(index, 1)
          },
          (error) => {
            alert('삭제실패');
            console.log(error);
          }
        );
      },

      changingMemo(item) {
        this.$emit('CHANGEMEMO', item)
      }
    }
}
</script>

<style>

</style>