<template>
  <div class="d-flex align-center py-12 flex-column">
    <!-- <v-autocomplete
      filled
      rounded
      style="width:50%;"
      placeholder="태그 검색 시 '#'를 앞에 붙여주세요"
    ></v-autocomplete> -->
    <v-text-field
      v-model="keyword"
      label="태그 검색 시 '#'를 앞에 붙여주세요"
      style="width:50%;"
      large
      @keyup.enter="search()"
      @blur="erase()"
    ></v-text-field>
  </div>
</template>

<script>
export default {
  name: "SearchInput",
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    erase() {
      if (this.keyword.length == 0) {
        this.$emit("GETKEYWORD", this.keyword);
      }
    },
    search() {
      console.log(this.keyword);
      this.$emit("GETKEYWORD", this.keyword);
    },
  },
};
</script>

<style></style>
