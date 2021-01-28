<template>
  <v-card-text>
      <v-textarea
        background-color="amber lighten-4"
        color="orange orange-darken-4"
        placeholder="메모하세요"
      ></v-textarea>
      <v-btn outlined>저장</v-btn>
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