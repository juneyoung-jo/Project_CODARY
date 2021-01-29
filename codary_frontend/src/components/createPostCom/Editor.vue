<template>
  <div class='d-flex flex-column'>
<!--  마크다운 구현예정 
  npm install @toast-ui/editor
  npm install @toast-ui/vue-editor
  npm install codemirror-->
    <editor
      ref="toastuiEditor"
      :initialValue="editorText"
      :options="editorOptions"
      height="500px"
      initialEditType="markdown"
      previewStyle="vertical"
      
    />
    <h3 class='py-8'>태그를 입력하세요</h3>
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
      align='center'
      class='my-8'
      outlined
      color="primary"
    >
      저장
    </v-btn>
    <div class='py-16'></div>
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor.css'
import 'codemirror/lib/codemirror.css'
import { Editor} from '@toast-ui/vue-editor'

export default {
  name: 'Editor',
    components: {
    'editor': Editor,
  },
  data() {
    return {
      editorText: '# This is initialValue.',
      editorOptions: {
        hideModeSwitch: true
      },
      items: ['코딩초보', '도와주세요', '알고리즘', '백준'],
      values: ['코딩초보', '도와주세요'],
      value: null,
    };
  },
  methods: {
    datachange : function() {
      const content = this.$refs.toastuiEditor.invoke("getMarkdown");
      
      this.editorText = content

      console.log(this.$refs.toastuiViewer)
      
      this.$refs.toastuiViewer.editor.markdownValue = content
      // console.log(this.editorText)
      // this.editorText = (Editor.getMarkdown())


      // axios연결로 에디터와 제목과 태그가 한번에 저장되도록 연결
      
    }
  },
}
</script>

<style>

</style>