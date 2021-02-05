<template>
  <div class="d-flex flex-column">
    <!--  마크다운 구현예정 
  npm install @toast-ui/editor
  npm install @toast-ui/vue-editor
  npm install codemirror-->
    <v-text-field
      v-model="title"
      label="제목을 입력하세요"
      class="py-12"
      large
    ></v-text-field>
    <editor
      ref="toastuiEditor"
      :initialValue="editorText"
      :options="editorOptions"
      height="500px"
      initialEditType="markdown"
      previewStyle="vertical"
    />
    <h3 class="py-8">태그를 입력하세요</h3>
    <v-col cols="12">
      <v-autocomplete
        v-model="values"
        :items="items"
        dense
        chips
        small-chips
        label="Solo"
        multiple
        solo
      ></v-autocomplete>
    </v-col>
    <v-btn
      @click="datachange()"
      align="center"
      class="my-8"
      outlined
      color="primary"
    >
      저장
    </v-btn>
    <div class="py-16"></div>

    <!-- <form method="post" enctype="multipart/form-data" action="" >
      <input type="file" name="photo" ref="photoimage"> 
      <input type="submit"> 
    </form>  사진 넣는거 해보려고...-->
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "codemirror/lib/codemirror.css";
import { Editor } from "@toast-ui/vue-editor";

export default {
  name: "Editor",
  components: {
    editor: Editor,
  },
  data() {
    return {
      editorText: "# This is initialValue.",
      editorOptions: {
        hideModeSwitch: true,
      },
      title: "",
      items: ["코딩초보", "도와주세요", "알고리즘", "백준"],
      values: ["코딩초보", "도와주세요"],
      value: null,
    };
  },
  methods: {
    datachange() {
      const content = this.$refs.toastuiEditor.invoke("getMarkdown");

      this.editorText = content;

      if (this.editorText == null) {
        alert("내용을 입력해주세요!");
        return;
      }

      console.log(content);
      console.log("title: " + this.title);
      this.$emit("GETCONTENT", this.editorText, this.title);
    },
  },
};
</script>

<style></style>
