<template>
  <v-card-text id="memoListItem" class="pa-4">
    <div class="mb-7">
      <v-list
        v-for="(item, index) in this.calData"
        :key="index"
        class="d-flex flex-column"
      >
        <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
        <v-list-item-content id="item.memoNum">
          <v-list-item-subtitle class="pa-2">{{
            item.memoContent
          }}</v-list-item-subtitle>
          <v-btn value="item.memoLink" herf="item.memoLink">작성한 곳으로 이동</v-btn>
        </v-list-item-content>
        <div class="d-flex justify-end">
          <v-btn small fab plain @click="changingMemo(item, index)">
            <font-awesome-icon :icon="['fas', 'edit']" />
          </v-btn>
          <v-btn small fab plain @click="deletingMemo(item, index)">
            <font-awesome-icon :icon="['fas', 'trash-alt']" />
          </v-btn>
        </div>
      </v-list>
    </div>
      <v-pagination
      v-model="curPageNum"
      :length="numOfPages"
      >
      <!-- :total-visible="2" -->
    </v-pagination>
  </v-card-text>
</template>


<script>
import { deleteMemo } from "@/api/memo.js";
// import { memoList } from "@/api/memo.js";
import { mapState } from "vuex";
// import { getmemoIdCookie } from "@/util/cookie.js";

export default {
  name: "MemoList",
  props: ["listData"],
  data() {
    return {
      dataPerPage: 3,
      curPageNum: 1,
    };
  },
  computed: {
    ...mapState(["isLogin", "loggedInUserData"]),
    startOffset() {
      return (this.curPageNum - 1) * this.dataPerPage;
    },
    endOffset() {
      return this.startOffset + this.dataPerPage;
    },
    numOfPages() {
      return Math.ceil(this.listData.length / this.dataPerPage);
    },
    calData() {
      return this.listData.slice(this.startOffset, this.endOffset);
    },
  },
  methods: {
    deletingMemo(item, index) {
      if (confirm("삭제하시겠습니까?")) {
        deleteMemo(
          item,
          () => {
            this.$emit("DELETEMEMO", index);
            alert("삭제되었습니다.");
          },
          (error) => {
            alert("삭제실패");
            console.log(error);
          }
        );
      }
    },
    changingMemo(item, index) {
      if (confirm("수정하시겠습니까?")) {
        this.$emit("MODIFYMEMO", index, item.memoContent, item.memoNum);
      }
    },
  },
};
</script>

<style scoped>
#memoListItem {
  width: 270px;
  overflow: auto;
  margin: 0;
  padding: 0;
}
</style>