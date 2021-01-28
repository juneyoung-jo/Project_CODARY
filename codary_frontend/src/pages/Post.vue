<template>
  <div>
    <PostCover/>
    <v-container>
      <v-card class='pa-8 py-8'>
        <PostViewer/>
        <div class='py-16'></div>
        <Profile/>
      </v-card>
      <CommentWrite/>
      <Comment/>
    </v-container>
  </div>
</template>

<script>
import PostCover from '../components/postCom/PostCover.vue'
import PostViewer from '../components/postCom/PostViewer.vue'
import Profile from '../components/postCom/Profile.vue'
import Comment from '../components/postCom/comment/Comment.vue'
import CommentWrite from '../components/postCom/comment/CommentWrite.vue'

export default {
  components: { PostCover, PostViewer, Profile, Comment, CommentWrite },
  name: 'Post',
}
</script>

<style>

</style>