<template>
  <div class="py-12">
    <Search-input @GETKEYWORD="getkeyword" />
    <select v-model="selected" @change="changeSelect($event)">
      <option disabled value="">정렬 기준</option>
      <option
        v-for="option in options"
        :key="option.value"
        v-bind:value="option.value"
      >
        {{ option.text }}
      </option>
    </select>
    <v-container>
      <SearchResult v-if="this.isSearched" :list="list" />
      <Popular-tag v-else :list="list" />
    </v-container>
  </div>
</template>

<script>
import PopularTag from "../components/searchCom/PopularTag.vue";
import SearchResult from "../components/searchCom/SearchResult.vue";
import SearchInput from "../components/searchCom/SearchInput.vue";

export default {
  components: { SearchInput, SearchResult, PopularTag },
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
      if (this.selected == 2) this.getByView();
      else if (this.selected == 3) this.getByLike();
      else this.getByDate();
    },
    getByView() {
      const keyword = this.keyword.replace(/#/g, "%23");
      this.axios
        .get(`search/view/${keyword}`)
        .then((res) => {
          this.list = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getByLike() {
      const keyword = this.keyword.replace(/#/g, "%23");
      this.axios
        .get(`search/like/${keyword}`)
        .then((res) => {
          this.list = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getByDate() {
      const keyword = this.keyword.replace(/#/g, "%23");
      this.axios
        .get(`search/date/${keyword}`)
        .then((res) => {
          this.list = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
