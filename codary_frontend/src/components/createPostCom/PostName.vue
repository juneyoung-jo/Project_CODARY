
<template>
  <v-text-field
    label="제목을 입력하세요"
    class='py-12'
    large
  ></v-text-field>
</template>
<script>
export default {
    name:'PostName',
    // 제목도 따로 저장버튼에 연동되게..
    data() {
      return {
        
      }
    },
    methods: {
      // 여기에 글 저장될때 같이 저장되게 데이터를 에디터로 넘기는 역할이 필요...
    }
}
</script>

<style>

</style>