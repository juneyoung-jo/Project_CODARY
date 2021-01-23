<template>
  <div class="py-12">
    <search-input/>
    <popular-tag/>
  </div>
</template>

<script>
import PopularTag from '../components/searchCom/PopularTag.vue'
import SearchInput from '../components/searchCom/SearchInput.vue'

export default {
  components: { SearchInput, PopularTag },
  name: 'SearchPage',
}
</script>

<style>

</style>