<template>
  <div class="py-12">
    <search-input/>
    <popular-tag/>

    <router-link :to="'/viewpost'">
      <v-btn
        color="red">
        글 보기
      </v-btn>
    </router-link>
  </div>
</template>

<script>
import PopularTag from '../components/searchCom/PopularTag.vue'
import SearchInput from '../components/searchCom/SearchInput.vue'


export default {
  components: { SearchInput, PopularTag, },
  name: 'SearchPage',
}
</script>

<style>

</style>