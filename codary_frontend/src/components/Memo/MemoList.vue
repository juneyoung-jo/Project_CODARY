<template>
  <v-card-text 
    id="memoListItem"
    class='pa-10'
    color="background"
  >
    <div class='mb-7'> 
      <v-list 
        v-for="item in this.calData" 
        :key="item.memoNum"
        class='d-flex flex-column backcolor'
      >
        <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
        <v-list-item-content id="item.memoNum">
          <v-list-item-subtitle class='pa-2'>{{item.memoContent}}</v-list-item-subtitle>
        </v-list-item-content>
        <div class='d-flex justify-end'>
          <v-btn small fab plain @click="changingMemo(item)">
            <font-awesome-icon :icon="['fas','edit']"/>
          </v-btn>
          <v-btn small fab plain @click="deletingMemo(item, item.memoNum)">
            <font-awesome-icon :icon="['fas','trash-alt']"/>
          </v-btn>
        </div>

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
      dataPerPage: 3,
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
  width: 270px;
  overflow: auto;
  margin: 0;
  padding: 0;
}
.backcolor {
  background-color: yellow;
}

</style>