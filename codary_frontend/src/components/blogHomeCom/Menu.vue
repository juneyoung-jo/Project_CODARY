<template>
<div class='py-12'>
 <v-tabs align-with-title>
      <router-link :to="{ name:'MyPost'}">
          <v-tab>내가쓴글</v-tab>
      </router-link>
      <router-link :to="{ name:'MyMemo'}">
        <v-tab>메모보기</v-tab>
      </router-link>
      <router-link :to="{ name:'MyLikePost'}">
        <v-tab>즐겨찾기</v-tab>
      </router-link>
      <router-link :to="{ name:'MySubscriber'}">
        <v-tab>좋아요한사람</v-tab>
      </router-link>
</v-tabs>
 
</div>

</template>

<script>
export default {
    name:'Menu',
 
}
</script>

<style>

</style>