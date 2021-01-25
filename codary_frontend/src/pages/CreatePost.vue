<template>
  <div>
    <SelectPostCover/>
    <Editor/>
  </div>
</template>

<script>
import Editor from '../components/createPostCom/Editor.vue'
import SelectPostCover from '../components/createPostCom/SelectPostCover.vue'

export default {
  name: 'CreatePost',
  components: { Editor, SelectPostCover },
}
</script>

<style>

</style>