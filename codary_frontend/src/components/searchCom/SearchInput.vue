<template>


  <div class="d-flex align-center justify-space-around" style="height:60px">
  
    <!-- <v-autocomplete
      filled
      rounded
      style="width:50%;"
      placeholder="태그 검색 시 '#'를 앞에 붙여주세요"
    ></v-autocomplete> -->

  
    <v-text-field
      v-model="keyword"
      class='font-weight-bold mr-2'
      flat
      hide-details
      solo-inverted
      @keyup.enter="search()"
      @blur="erase()"
      
    >
      <template v-slot:label>
        <div class='ml-2 my-0'>
          제목 또는 태그를 선택한 후에 검색해요
        </div>
      </template>
    </v-text-field>
  </div> 

</template>

<script>
export default {
  name: "SearchInput",
  data() {
    return {
      keyword: "",
      titlesearch: true,
 
    };
  },
  methods: {
    erase() {
      if (this.keyword.length == 0) {
        this.$emit("GETKEYWORD", this.keyword);
      }
    },
    search() {
      console.log(this.keyword, this.titlesearch);
      this.$emit("GETKEYWORD", this.keyword);
    },
  },
};
</script>

<style></style>
