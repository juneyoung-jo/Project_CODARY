<template>
  <div>
<!--  마크다운 구현예정 
  npm install @toast-ui/editor
  npm install @toast-ui/vue-editor
  npm install codemirror-->
    <editor 
      :initialValue="editorText"
      :options="editorOptions"
      height="500px"
      initialEditType="markdown"
      previewStyle="vertical"
    />
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor.css'
import 'codemirror/lib/codemirror.css'
import { Editor } from '@toast-ui/vue-editor'

export default {
  name: 'Editor',
    components: {
    'editor': Editor,
    // 'viewer': Viewer
  },
  data() {
    return {
      editorText: 'This is initialValue.',
      editorOptions: {
        hideModeSwitch: true
      }
    };
  },
}
</script>

<style>

</style>