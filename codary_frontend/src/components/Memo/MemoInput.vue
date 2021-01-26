<template>
  <v-card-text>
    <br>
      <v-textarea
        background-color="amber lighten-4"
        color="orange orange-darken-4"
        label="Label"
      ></v-textarea>
      <v-btn>저장</v-btn>
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