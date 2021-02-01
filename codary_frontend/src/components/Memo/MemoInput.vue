<template>
  <v-card-text>
      <v-textarea
        background-color="blue lighten-4"
        color="white"
        placeholder="메모하세요"
        auto-grow
        rounded
        solo-inverted
        flat
        class='memoinput'
      ></v-textarea>
      <v-btn plain>저장</v-btn>
  </v-card-text>
</template>

<script>
  export default {
    name: 'MemoInput',
    data: () => ({
      menu: false,
    }),
  
  }
</script>
<style>


</style>