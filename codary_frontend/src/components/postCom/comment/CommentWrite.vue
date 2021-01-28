<template>
  <div>
    <div class='py-7'></div>
    <h1>Comment</h1>
    <v-container
    class='d-flex'>

      <v-text-field
        v-model="message1"
        label="여기에 댓글을 입력하세요"
        clearable
      ></v-text-field>
      <v-btn plain color="blue">
        댓글달기!
      </v-btn>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'CommentWrite',
}
</script>

<style>

</style>