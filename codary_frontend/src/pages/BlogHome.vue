<template>
  <div>
   <MyCover/>
   <Profile/>
   <Grass/>
   <MyLikePost/>
   <MyMemo/>
   <MyPost/>
   <MySubscriber/>
  

  </div>
</template>

<script>
import MyCover from '../components/blogHomeCom/MyCover.vue'
import Profile from '../components/blogHomeCom/Profile.vue'
import Grass from '../components/blogHomeCom/Grass.vue'
import MyLikePost from '../components/blogHomeCom/MyLikePost.vue'
import MyMemo from '../components/blogHomeCom/MyMemo.vue'
import MyPost from '../components/blogHomeCom/MyPost.vue'
import MySubscriber from '../components/blogHomeCom/MySubscriber.vue'

export default {
  name: 'BlogHome',
  components: { Profile,MyCover, Grass, MyLikePost,MyMemo, MyPost, MySubscriber }

}
</script>

<style>

</style>