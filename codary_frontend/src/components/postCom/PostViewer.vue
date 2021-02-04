<template>
  <div>
    <div class="py-2"></div>
    <h1 class="py-10 pa-14">글 제목</h1>
    <div class="pa-13 py-0">
      <v-chip outlined>코딩초보</v-chip>
      <v-chip outlined>도와주세요</v-chip>
      <v-chip outlined>알고리즘</v-chip>
      <v-chip outlined>백준</v-chip>
    </div>
    <v-container class="pa-16">
      <viewer :value="editorText" height="500px" :initialValue="editorText" viewer="true" />
    </v-container>
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor.css';
import 'codemirror/lib/codemirror.css';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  name: 'PostViewer',
  components: {
    viewer: Viewer,
    // 'viewer': Viewer
  },
  data() {
    return {
      editorText:
        '# This is initialValue.\n ## 안녕하세요\n ### 코다리입니다 \n #### 반갑습니다 \n ##### 마크다운으로 작성가능합니다',
    };
  },
};
</script>

<style></style>
