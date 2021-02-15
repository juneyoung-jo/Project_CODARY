<template>
  <div class="py-12">
    <v-container>
      <v-tabs vertical class="d-flex align-center" style="width:80%; ">
      <v-tab>
        <v-icon left>
          mdi-account
        </v-icon>
        제목으로 검색
      </v-tab>
      <v-tab>
        <v-icon left>
          mdi-lock
        </v-icon>
        태그로 검색
      </v-tab>
       <v-tab-item class="" style="width:100%">
        <SearchInput @GETKEYWORD="getkeyword" />
      </v-tab-item>
      <v-tab-item class="">
        <SearchInputTag @GETKEYWORD="getkeyword" />
      </v-tab-item>
      </v-tabs>
   
      <div class="d-flex selectsort ma-2">
        <select class='now' v-model="selected" @change="changeSelect($event)">
          <option disabled>정렬 기준</option>
          <option
            v-for="option in options"
            :key="option.value"
            v-bind:value="option.value"
          >
            {{ option.text }}
          </option>
        </select>  
      </div>
      <SearchResult v-if="this.isSearched" :list="list" />
      <Popular-tag v-else :list="list" />
    </v-container>
  </div>
</template>

<script>
import PopularTag from "../components/searchCom/PopularTag.vue";
import SearchResult from "../components/searchCom/SearchResult.vue";
import SearchInput from "../components/searchCom/SearchInput.vue";
import SearchInputTag from "../components/searchCom/SearchInputTag.vue";
import { getByDate, getByView, getByLike } from "@/api/search.js";

export default {
  components: { SearchInput, SearchResult, PopularTag, SearchInputTag },
  name: "SearchPage",
  data() {
    return {
      selected: 1,
      isSearched: false,
      keyword: "",
      options: [
        { text: "최신순", value: 1 },
        { text: "조회순", value: 2 },
        { text: "좋아요순", value: 3 },
      ],
      list: [],
      titlesearch: true,
    };
  },
  created() {
    this.searchBy();
  },
  methods: {
    getkeyword(keyword) {
      this.keyword = keyword;
      if (this.keyword.length == 0) this.isSearched = false;
      else this.isSearched = true;
      console.log(this.keyword);
      this.searchBy();
    },
    changeSelect(event) {
      this.selected = event.target.value;
      this.searchBy();
    },
    searchBy() {
      if (this.selected == 2)
        getByView(this.keyword, (res) => {
          this.list = res.data;
        });
      else if (this.selected == 3)
        getByLike(this.keyword, (res) => {
          this.list = res.data;
        });
      else
        getByDate(this.keyword, (res) => {
          this.list = res.data;
        });
    },
  },
};
</script>

<style scoped>

.selectsort select {
  padding: 12px;
  width: 200px;
  border: none;
  font-size: 16px;
  box-shadow: 0 0px 2px rgba(0, 0, 0, 0.2);
  appearance: button;
  outline: none;
  border-radius: 2%;
  background-color: white;
}

.selectsort select option {
  padding: 10px;
  color:black
}

.now {
  color: black;
}
</style>
