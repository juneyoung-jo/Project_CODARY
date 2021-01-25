<template>
  <div>
    <PostCover/>
    <PostViewer/>
    <Profile/>
    <Comment/>
  </div>
</template>

<script>
import PostCover from '../components/postCom/PostCover.vue'
import PostViewer from '../components/postCom/PostViewer.vue'
import Profile from '../components/postCom/Profile.vue'
import Comment from '../components/postCom/comment/Comment.vue'

export default {
  components: { PostCover, PostViewer, Profile, Comment },
  name: 'Post',
}
</script>

<style>

</style>