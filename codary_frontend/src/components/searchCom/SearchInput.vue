<template>
  <div class="d-flex align-center py-12 flex-column">
    <v-autocomplete
      filled
      rounded
      style="width:50%;"
      placeholder="태그 검색 시 '#'를 앞에 붙여주세요"
    ></v-autocomplete>
  </div>

</template>

<script>
export default {
  name: 'SearchInput',
}
</script>

<style>

</style>