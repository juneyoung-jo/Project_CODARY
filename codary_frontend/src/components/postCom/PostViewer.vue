<template>
  <div>
    <div class="py-2"></div>
    <h1 class="py-8 pa-6">{{ this.blogContents.blogContentsTitle }}</h1>
    <div class="pa-12 py-0">
      <v-chip outlined>코딩초보</v-chip>
      <v-chip outlined>도와주세요</v-chip>
      <v-chip outlined>알고리즘</v-chip>
      <v-chip outlined>백준</v-chip>
    </div>
    <v-container class="pa-16">
      <!-- <div id="viewer" /> -->
      <!-- <viewer
        @load="onViewerLoad"
        height="500px"
        viewer="true"
        :initialValue="this.blogContents.blogContents"
      /> -->
      <!-- {{ this.blogContents.blogContents }} -->
    </v-container>
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "codemirror/lib/codemirror.css";
// import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
// import { Viewer } from '@toast-ui/vue-editor';

export default {
  name: "PostViewer",
  props: ["blogContents"],
  components: {
    // viewer: Viewer,
    // 'viewer': Viewer
  },
  // mounted() {
  //   new Viewer({
  //     el: document.querySelector('#viewer'),
  //     initialValue: '# 하이하이하이',
  //   });
  // },
  data() {
    return {
      viewerText: this.blogContents.blogContents,
    };
  },
  methods: {
    onViewerLoad() {
      this.viewerText = this.blogContents.blogContents;
    },
  },
};
</script>

<style></style>
