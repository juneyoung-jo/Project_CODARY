<template>
  <div>
    <SelectPostCover/>
    <PostName/>
    <Editor/>
  </div>
</template>

<script>
import Editor from '../components/createPostCom/Editor.vue'
import PostName from '../components/createPostCom/PostName.vue'
import SelectPostCover from '../components/createPostCom/SelectPostCover.vue'

export default {
  name: 'CreatePost',
  components: { Editor, SelectPostCover, PostName },
}
</script>

<style>

</style>