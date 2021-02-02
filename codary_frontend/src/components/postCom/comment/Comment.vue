<template>
  <div>
    <v-list three-line>
      <template v-for="(item, index) in items">
        <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader>

        <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>

        <v-list-item v-else :key="item.commentNum">
          <!-- <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar> -->
          <CommentDetail :comment="item.uid" />

          <!-- <v-list-item-commentContent> -->
          <v-list-item-title v-html="item.commentContent"></v-list-item-title>
          <!-- <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle> -->
          <!-- </v-list-item-commentContent> -->
        </v-list-item>
      </template>
    </v-list>
    <div class="py-16"></div>
  </div>
</template>

<script>
import { commentList } from '@/api/comment.js';
import CommentDetail from './CommentDetail.vue';

export default {
  name: 'Comment',
  components: {
    CommentDetail,
  },
  created() {
    commentList(
      this.blogContents,
      (response) => {
        // console.log(response.data.data);
        this.items = response.data.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      blogContentsList: [],
      blogContents: {
        blogId: 'mqa6x5p2oESm',
        blogContentsId: 2,
      },
      items: [],
      // items: [
      //   { header: 'Today' },
      //   {
      //     avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      //     title: 'Brunch this weekend?',
      //     subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
      //   },
      //   { divider: true, inset: true },
      //   {
      //     avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
      //     title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
      //     subtitle: `<span class="text--primary">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.`,
      //   },
      //   { divider: true, inset: true },
      //   {
      //     avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
      //     title: 'Oui oui',
      //     subtitle:
      //       '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?',
      //   },
      //   { divider: true, inset: true },
      //   {
      //     avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
      //     title: 'Birthday gift',
      //     subtitle:
      //       '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
      //   },
      //   { divider: true, inset: true },
      //   {
      //     avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
      //     title: 'Recipe to try',
      //     subtitle:
      //       '<span class="text--primary">Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
      //   },
      // ],
    };
  },
};
</script>

<style></style>
