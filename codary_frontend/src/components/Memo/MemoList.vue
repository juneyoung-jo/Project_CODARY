<template>
  <v-card-text id="memoListItem" class='mt-3'>
    <div class="mb-2">
      <v-list
        v-for="(item, index) in this.calData"
        :key="index"
        class="d-flex flex-column"
      >
        <!-- 여기서 아이템이 01로 표시되는걸로 걸러지게... -->
        <v-list-item-content :id="item.memoNum" class="pa-0">
          <v-sheet
            color="background"
            
            rounded
            width="300"
            class="pa-5 d-flex justify-center font-weight-medium"
            
          >
            {{item.memoContent}}
          </v-sheet>
        </v-list-item-content>
        <div class="d-flex justify-end">
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <a :href="item.memoLink">
                <v-btn 
                v-bind="attrs"
                v-on="on" 
                small 
                fab 
                plain 
                :value="item.memoLink"
                style="font-size: 15px"   
                class="mb-1 pr-1"
                >
                  <font-awesome-icon :icon="['fas', 'arrow-alt-circle-right']" />
                </v-btn>
              </a>
            </template>
            <span style="font-size: 12px">작성했던 장소로 가기</span>
          </v-tooltip>
          <v-btn small fab plain style="font-size: 15px" @click="changingMemo(item, index)">
            <font-awesome-icon :icon="['fas', 'edit']" />
          </v-btn>
          <v-btn small fab plain class='mr-2' style="font-size: 15px" @click="deletingMemo(item, index)">
            <font-awesome-icon :icon="['fas', 'trash-alt']" />
          </v-btn>
        </div>
      </v-list>
    </div>
      <v-pagination
      v-model="curPageNum"
      :length="numOfPages"
      :total-visible="5"
      >
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
  /* width: 270px; */
  overflow: auto;
  margin: 0;
  padding: 0;
}
</style>