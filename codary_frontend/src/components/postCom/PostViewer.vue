<template>
  <div>
    <div class="py-2"></div>
    <h1 class="py-10 pa-14">{{ this.blogContents.blogContentsTitle }}</h1>
    <div class="pa-13 py-0">
      <v-chip outlined>코딩초보</v-chip>
      <v-chip outlined>도와주세요</v-chip>
      <v-chip outlined>알고리즘</v-chip>
      <v-chip outlined>백준</v-chip>
    </div>
    <v-container class="pa-16">
      <viewer
        :value="editorText"
        height="500px"
        :initialValue="this.blogContents.blogContents"
        viewer="true"
      />
      <!-- {{ this.blogContents.blogContents }} -->
    </v-container>
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "codemirror/lib/codemirror.css";
import { Viewer } from "@toast-ui/vue-editor";

export default {
  name: "PostViewer",
  props: ["blogContents"],
  components: {
    // viewer: Viewer,
    viewer: Viewer,
  },
  data() {
    return {
      editorText: "##초기값",
    };
  },
  created() {
    // this.editorText = this.blogContents.blogContents;
  },
};
</script>

<style></style>
