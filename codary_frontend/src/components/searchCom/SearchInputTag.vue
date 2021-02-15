<template>
  <div>
    <div class="d-flex align-center justify-space-around" style="height:60px">
      <!-- <v-autocomplete
      filled
      rounded
      style="width:50%;"
      placeholder="태그 검색 시 '#'를 앞에 붙여주세요"
    ></v-autocomplete> -->

      <!--   
    <v-text-field
      v-model="keyword"
      class='font-weight-bold ma-0 ml-4'
      style="width:50%;"
      flat
      solo-inverted
      @keyup.enter="search()"
      @blur="erase()"
    >
      <template v-slot:label>
        <div class='ml-2 my-0'>
          태그를 선택한 후에 검색해요
        </div>
      </template>
    </v-text-field> -->

      <v-autocomplete
        class="mr-2"
        v-model="values"
        :items="items"
        item-text="hashtagContent"
        item-value="hashtagId"
        dense
        filled
        chips
        deletable-chips
        multiple
        hide-details
        label="제목 또는 태그를 선택한 후에 검색하세요"
        @blur="search()"
      ></v-autocomplete>
    </div>
  </div>
</template>

<script>
import { getHashtags } from "@/api/blogcontent.js";

export default {
  name: "SearchInputTag",
  data() {
    return {
      keyword: "",
      titlesearch: true,
      items: [],
      values: [],
    };
  },
  created() {
    this.getHashtag();
  },
  methods: {
    getHashtag: function () {
      getHashtags(
        (res) => {
          this.items = res.data.list;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    search() {
      // console.log(this.values.length);
      if (this.values.length > 0) this.$emit("GETHASH", this.values);
    },
  },
};
</script>

<style></style>
