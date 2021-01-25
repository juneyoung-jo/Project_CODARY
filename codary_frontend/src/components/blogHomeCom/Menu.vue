<template>
<div class='py-12'>
 <v-tabs grow>
      <v-tab v-for="tab in tabs" :key="tab.id" @click="updateRouter(tab.route)">
        {{ tab.name }}
      </v-tab>
</v-tabs>
</div>
</template>


<script>
  export default {
    data () {
      return {
        activeTab: '',
        tabs: [
            {id:1, name:'내가쓴글', route: { name:'MyPost' }},
            {id:2, name:'메모보기', route: { name:'MyMemo' }},
            {id:3, name:'즐겨찾기', route: { name:'MyLikePost' }},
            {id:4, name:'좋은사람', route: { name:'MySubscriber' }}
        ], 
      }
    },
    methods: {
      updateRouter(val){
        this.$router.push(val)
      }
    }
  }
</script>


<style>
</style>