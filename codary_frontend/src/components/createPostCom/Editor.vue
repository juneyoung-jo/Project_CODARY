<template>
  <div class="d-flex flex-column">
    <!--  마크다운 구현예정 
  npm install @toast-ui/editor
  npm install @toast-ui/vue-editor
  npm install codemirror-->
    <v-text-field label="제목을 입력하세요" class="py-12" large></v-text-field>
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
      items: ["코딩초보", "도와주세요", "알고리즘", "백준"],
      values: ["코딩초보", "도와주세요"],
      value: null,
    };
  },
  methods: {
    datachange: function() {
      const content = this.$refs.toastuiEditor.invoke("getMarkdown");

      this.editorText = content;

      console.log(content);

      const data = {
        blogContents: this.editorText,
        blogContentsCover: "", // 커버 url..?
        blogContentsId: 6, // 하나씩 증가하는 수 여야하는데,,, 왜 안증가하지?
        // "blogContentsLike": 0,
        blogContentsTitle: "string",
        // "blogContentsView": 0,
        // "blogDatetime": "string",
        blogId: "r1p0PIRHl17i", //임의로 넣은
        //  여기서 blogID부분에 user에서 가져온 blogId가 들어가여함
      };

      console.log(data);

      this.axios
        .post(`blog/`, data)
        .then((res) => {
          console.log(res);
          // 글 작성된다음 어디로 보내지?
          // this.$router.push({name: 'MainPage'})
        })
        .catch((err) => {
          console.log(err);
        });
      // this.$refs.toastuiViewer.editor.markdownValue = content
      // console.log(this.editorText)
      // this.editorText = (Editor.getMarkdown())

      // axios연결로 에디터와 제목과 태그가 한번에 저장되도록 연결

      // 일단 404에러 뜸....
    },
    // showdata: function(e) {
    //   e.preventDefault()
    //   var data = new FormData();
    //   var file = this.$refs.photoimage.files[0];
    //   data.append('photo', file);
    //   console.log(data)
    //   console.log(file)
    //   console.log(data)
    //   axios.post('/api/data/' + this.no + '/photo', data)
    //   .then((res) => {
    //     this.result = res.data;
    //   })
    //   .catch((ex) => {
    //     console.log('사진업로드 실패', ex);
    //   });
    // }
  },
};
</script>

<style></style>
