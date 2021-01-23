<template>
  <div class="py-12">
    <search-input/>
    <search-result/>
  </div>
</template>

<script>
import SearchInput from '../components/searchCom/SearchInput.vue'
import SearchResult from '../components/searchCom/SearchResult.vue'

export default {
  components: { SearchInput, SearchResult },
  name: 'SearchPage',
}
</script>

<style>

</style>