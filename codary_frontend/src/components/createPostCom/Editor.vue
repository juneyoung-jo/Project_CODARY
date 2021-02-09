<template>
  <div class="d-flex flex-column">
    <v-text-field v-model="title" label="제목을 입력하세요" class="py-12" large></v-text-field>
    <!-- <editor
      ref="toastuiEditor"
      :initialValue="editorText"
      :options="editorOptions"
      height="500px"
      initialEditType="markdown"
      previewStyle="tab"
    /> -->
    <div id="editor" />
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
    <v-btn id="submit" align="center" class="my-8" outlined color="primary">
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
// import '@toast-ui/editor/dist/toastui-editor.css';
// import 'codemirror/lib/codemirror.css';
// import { Editor } from '@toast-ui/vue-editor';

import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import Editor from '@toast-ui/editor';
import 'highlight.js/styles/github.css';
import { fileUpload } from '@/api/fileUpload.js';

export default {
  name: 'Editor',
  components: {
    // editor: Editor,
  },
  data() {
    return {
      editorText: '# This is initialValue.',
      editorOptions: {
        hideModeSwitch: true,
      },
      title: '',
      items: ['코딩초보', '도와주세요', '알고리즘', '백준'],
      values: ['코딩초보', '도와주세요'],
      value: null,
    };
  },
  mounted() {
    function youtubePlugin() {
      Editor.codeBlockManager.setReplacer('youtube', (youtubeId) => {
        // Indentify multiple code blocks
        //https://www.youtube.com/watch?v=Dxt5WGd-ED0
        const arr = youtubeId.split('v=');
        youtubeId = arr[1];
        const wrapperId = `yt${Math.random()
          .toString(36)
          .substr(2, 10)}`;

        // Avoid sanitizing iframe tag
        setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

        return `<div id="${wrapperId}"></div>`;
      });
    }

    function renderYoutube(wrapperId, youtubeId) {
      const el = document.querySelector(`#${wrapperId}`);

      el.innerHTML = `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" ></iframe>`;
    }

    function blogPlugin() {
      Editor.codeBlockManager.setReplacer('url', (url) => {
        // console.log(youtubeId);
        // Indentify multiple code blocks
        //https://www.youtube.com/watch?v=Dxt5WGd-ED0

        const wrapperId = `yt${Math.random()
          .toString(36)
          .substr(2, 10)}`;

        setTimeout(renderblogUrl.bind(null, wrapperId, url), 0);
        return `<div id="${wrapperId}"></div>`;
        // return `<iframe width="420" height="315" src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allowfullscreen></iframe>`;
      });
    }

    function renderblogUrl(wrapperId, url) {
      const el = document.querySelector(`#${wrapperId}`);
      el.innerHTML = `<iframe width="420" height="315" src="${url}" 
              frameborder="0" width="500" height="200" marginwidth="0" marginheight="0" scrolling="auto" style="border:1 solid navy"
              ></iframe>`;
    }

    const editor = new Editor({
      ref: 'toastuiEditor',
      el: document.querySelector('#editor'),
      initialEditType: 'markdown',
      previewStyle: 'tab',
      viewer: true,
      height: '500px',
      plugins: [youtubePlugin, blogPlugin],
      hooks: {
        addImageBlobHook: (blob, callback) => {
          this.addImageBlobHook(blob, callback);
        },
      },
    });

    const btn = document.querySelector('#submit');
    btn.addEventListener('click', () => {
      const editContent = editor.getMarkdown();
      // console.log(editor.getHtml());

      if (this.title === '') {
        alert('제목을 입력해주세요');
        return;
      }

      if (editContent === '') {
        alert('내용을 입력해주세요');
        return;
      }

      this.$emit('GETCONTENT', editContent, this.title);
    });
  },
  methods: {
    addImageBlobHook(blob, callback) {
      let formData = new FormData();

      formData.append('file', blob);

      fileUpload(
        formData,
        (response) => callback(response.data, 'img'),
        (error) => console.log(error)
      );
    },
    // datachange() {
    //   const content = this.$refs.toastuiEditor.invoke('getMarkdown');

    //   this.editorText = content;

    //   if (this.editorText == null) {
    //     alert('내용을 입력해주세요!');
    //     return;
    //   }

    //   console.log(content);
    //   console.log('title: ' + this.title);
    //   // this.$emit('GETCONTENT', this.editorText, this.title);
    // },
  },
};
</script>

<style></style>
