<template>
  <div class="d-flex flex-column">
    <v-text-field
      v-model="title"
      label="제목을 입력하세요."
      class="py-12"
      large
      maxlength="70"
      counter="70"
    ></v-text-field>

    <v-card light id="editor" style="word-break: normal" />

    <!-- start  ###################################### -->
    <h3 class="py-8">태그를 입력하세요</h3>
    <tags-input
      element-id="tags"
      v-model="selectedTags"
      placeholder="태그를 입력해주세요."
      typeahead-style="badges"
      text-field="value"
      @keyup.enter="tagChanged"
      :existing-tags="existingtags"
      :typeahead="true"
    >
      <template v-slot:selected-tag="{ tag, index, removeTag }">
        <span v-html="tag.value"></span>

        <a href="#" class="tags-input-remove" @click.prevent="removeTag(index)"></a>
      </template>
    </tags-input>

    <!-- end ###################################### -->
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
import codeSyntaxHightlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import hljs from 'highlight.js';
import { fileUpload } from '@/api/fileUpload.js';
import { getTagList, getTagKey } from '@/api/hashtag.js';
import VoerroTagsInput from '@voerro/vue-tagsinput';

export default {
  name: 'Editor',
  props: ['blogContents'],
  components: {
    // editor: Editor,
    'tags-input': VoerroTagsInput,
  },
  created() {
    this.init();
    this.getHashtag();
  },
  data() {
    return {
      editorText: '# This is initialValue.',
      editorOptions: {
        hideModeSwitch: true,
      },
      // ########################
      existingtags: [],
      selectedTags: [],
      newTag: '',
      // ########################
    };
  },
  mounted() {
    // 코드펜 임베드 나중에
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

      el.innerHTML = `<iframe width="100%" height="380px" src="https://www.youtube.com/embed/${youtubeId}" ></iframe>`;
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
      el.innerHTML = `<iframe width="100%" height="400px" src="${url}"
              frameborder="0" marginwidth="0" marginheight="0" scrolling="auto" style="border:1 solid navy"
              ></iframe>`;
    }

    const editor = new Editor({
      ref: 'toastuiEditor',
      el: document.querySelector('#editor'),
      initialEditType: 'markdown',
      previewStyle: 'tab',
      viewer: true,
      height: '500px',
      hideModeSwitch: true,
      initialValue: this.editorText,
      plugins: [youtubePlugin, blogPlugin, [codeSyntaxHightlight, { hljs }]],
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

      if (this.selectedTags !== null) {
        this.selectedTags.forEach((data) => {
          if (data.key === '') data.key = -1;
        });
      }

      this.$emit('GETCONTENT', editContent, this.title, this.selectedTags);
    });
  },
  methods: {
    init() {
      this.editorText = this.blogContents.blogContents;
      this.title = this.blogContents.blogContentsTitle;

      for (var i = 0; i < this.blogContents.hashTag.length; i++) {
        const d = {
          key: this.blogContents.hashTag[i].hashtagId,
          value: this.blogContents.hashTag[i].hashtagContent,
        };

        this.selectedTags.push(d);
      }

      console.log(this.blogContents.hashTag);
    },
    addImageBlobHook(blob, callback) {
      let formData = new FormData();

      formData.append('file', blob);

      fileUpload(
        formData,
        (response) => callback(response.data, 'img'),
        (error) => console.log(error)
      );
    },
    // ####start 해시태그
    getHashtag: function() {
      console.log('#해시태그 읽어오기');
      getTagList(
        (response) => {
          for (var i = 0; i < response.data.list.length; i++) {
            const d = {
              key: response.data.list[i].hashtagId,
              value: response.data.list[i].hashtagContent,
            };
            this.existingtags.push(d);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    tagChanged: function() {
      console.log('선택된 태그: ');
      var lastIdx = this.selectedTags.length - 1;
      this.newTag = this.selectedTags[lastIdx].value;
      console.log('새로 입력된 태그: ' + this.newTag);
      var isOk = true;

      if (this.newTag[0] != '#') {
        this.newTag = '#' + this.newTag;
        for (var i = 0; i < this.selectedTags.length - 1; i++) {
          if (this.selectedTags[i].value == this.newTag) {
            this.selectedTags.pop();
            isOk = false;
            break;
          }
        }
        if (isOk) {
          const d = {
            key: -1,
            value: this.newTag,
          };
          // 3. 처음 사용된 태그로 key값 입력이 필요함
          getTagKey(d, (response) => {
            // console.log(response);
            this.selectedTags[lastIdx].key = response.data.tag.hashtagId;
            this.selectedTags[lastIdx].value = response.data.tag.hashtagContent;
          }),
            (error) => {
              console.log(error);
            };
        }
      } //end if

      // 4. 현재까지 선택된 태그 출력
      for (var j = 0; j < this.selectedTags.length; j++) {
        console.log(this.selectedTags[j].key + ' ' + this.selectedTags[j].value);
      } //end for
    },

    // ####end hashtag
  },
};
</script>

<style>
/* The input */
.tags-input {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.tags-input input {
  flex: 1;
  background: transparent;
  border: none;
}

.tags-input input:focus {
  outline: none;
}

.tags-input input[type='text'] {
  color: #495057;
}

.tags-input-wrapper-default {
  padding: 0.5em 0.25em;

  background: #fff;

  border: 1px solid transparent;
  border-radius: 0.25em;
  border-color: #dbdbdb;
}

.tags-input-wrapper-default.active {
  border: 1px solid #8bbafe;
  box-shadow: 0 0 0 0.2em rgba(13, 110, 253, 0.25);
  outline: 0 none;
}

/* The tag badges & the remove icon */
.tags-input span {
  margin-right: 0.3em;
}

.tags-input-remove {
  cursor: pointer;
  position: absolute;
  display: inline-block;
  right: 0.3em;
  top: 0.3em;
  padding: 0.5em;
  overflow: hidden;
}

.tags-input-remove:focus {
  outline: none;
}

.tags-input-remove:before,
.tags-input-remove:after {
  content: '';
  position: absolute;
  width: 75%;
  left: 0.15em;
  background: #5dc282;

  height: 2px;
  margin-top: -1px;
}

.tags-input-remove:before {
  transform: rotate(45deg);
}
.tags-input-remove:after {
  transform: rotate(-45deg);
}

/* Tag badge styles */
.tags-input-badge {
  position: relative;
  display: inline-block;
  padding: 0.25em 0.4em;
  font-size: 75%;
  font-weight: 700;
  line-height: 1;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: 0.25em;
  overflow: hidden;
  text-overflow: ellipsis;
}

.tags-input-badge-pill {
  padding-right: 1.25em;
  padding-left: 0.6em;
  border-radius: 10em;
}
.tags-input-badge-pill.disabled {
  padding-right: 0.6em;
}

.tags-input-badge-selected-default {
  color: #212529;
  background-color: #f0f1f2;
}

/* Typeahead */
.typeahead-hide-btn {
  color: #999 !important;
  font-style: italic;
}

/* Typeahead - badges */
.typeahead-badges > span {
  cursor: pointer;
  margin-right: 0.3em;
}

/* Typeahead - dropdown */
.typeahead-dropdown {
  list-style-type: none;
  padding: 0;
  margin: 0;
  position: absolute;
  width: 100%;
  z-index: 1000;
}

.typeahead-dropdown li {
  padding: 0.25em 1em;
  cursor: pointer;
}

/* Typeahead elements style/theme */
.tags-input-typeahead-item-default {
  color: #fff;
  background-color: #343a40;
}

.tags-input-typeahead-item-highlighted-default {
  color: #fff;
  background-color: #007bff !important;
}
</style>
