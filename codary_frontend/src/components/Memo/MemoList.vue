<template>
  <v-card-text id="memoListItem">
      <div>
        <v-list two-line v-for="item in this.calData" :key="item.memoNum">
          <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
          <v-list-item-content id="item.memoNum">
            <v-list-item-subtitle>{{item.memoContent}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-btn @click="changingMemo(item)">수정버튼</v-btn>
          <v-btn @click="deletingMemo(item, item.memoNum)">삭제버튼</v-btn>
          <v-divider></v-divider>
        </v-list>
      </div>
       <v-pagination
        v-model="curPageNum"
        :length="numOfPages">
      </v-pagination>
  </v-card-text>
</template>


<script>
import { deleteMemo } from '@/api/memo.js';
import { memoList } from '@/api/memo.js';
import {mapState} from 'vuex';

export default {
  name:'MemoList',
  // props: ['memoLists'],
  data() {
    return {
      listData: [],
      dataPerPage: 5,
      curPageNum: 1,
    }
  },
  computed:{
    ...mapState(['isLogin', 'loggedInUserData']),
    startOffset() {
      return ((this.curPageNum - 1) * this.dataPerPage);
    },
    endOffset() {
      return (this.startOffset + this.dataPerPage);
    },
    numOfPages() {
      return Math.ceil(this.listData.length / this.dataPerPage);
    },
    calData() {
      return this.listData.slice(this.startOffset, this.endOffset)
    }
  },

  created() { 
    if (this.isLogin === true) {
      memoList(
        this.loggedInUserData.memoId,
        (response) => {
          this.listData = response.data 
        },
        (error) => {
          console.log(error)
        }
      )
    }
    
  },

  methods: {
    // getmemoList() {
    //   if (this.isLogin === true) {
    //     memoList(
    //       this.loggedInUserData.memoId,
    //       (response) => {
    //         this.memoLists = response.data 
    //       },
    //       (error) => {
    //         console.log(error)
    //       }
    //     )
    //   }
    // },


    deletingMemo(item, memoNum) {
      deleteMemo(
        item,
        () => {
          const findinfIndex = this.listData.findIndex(memoNum)
          // console.log(response)
          // console.log('삭제')
          this.listData.splice(findinfIndex, 1)
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

<style scoped>

#memoListItem {
  height: 500px;
  width: 270px;
  overflow: auto;
  margin: 0;
  padding: 0;
}
::-webkit-scrollbar {
  width: 10px;
}
::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}

</style>