<template>
  <div>
    <h1>PostViewer</h1>
    <viewer 
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
import { Viewer } from '@toast-ui/vue-editor'

export default {
  name: 'PostViewer',
  components: {
    'viewer': Viewer
    // 'viewer': Viewer
  },
  data() {
    return {
      editorText: '# This is initialValue.',
     
    }
  }
}
</script>

<style>

</style>