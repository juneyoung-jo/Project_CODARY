<template>
  <div>
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
    <v-btn
      @click="datachange()"
    >
      눌러

    </v-btn>
    
    <viewer 
      ref="toastuiViewer"
      :value="editorText" 
      height="500px"
      :initialValue="editorText" 
      viewer=true 
    />
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor.css'
import 'codemirror/lib/codemirror.css'
import { Editor, Viewer } from '@toast-ui/vue-editor'

export default {
  name: 'Editor',
    components: {
    'editor': Editor,
    'viewer': Viewer
  },
  data() {
    return {
      editorText: '# This is initialValue.',
      editorOptions: {
        hideModeSwitch: true
      }
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
      
    }
  },
}
</script>

<style>

</style>