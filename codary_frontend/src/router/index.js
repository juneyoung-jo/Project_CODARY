import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '../pages/MainPage.vue'
import SearchPage from '../pages/SearchPage.vue'
import BlogHome from '../pages/BlogHome.vue'
import CreatePost from '../pages/CreatePost.vue'


Vue.use(Router) 
 
export default new Router({
  routes: [   
    { 
      path: '/',
      name: "MainPage",
      component: MainPage,
    },
    {
      path: '/searchpage',
      name: "SearchPage",
      component: SearchPage,
    },
    {
      path: '/bloghome',
      name: "BlogHome",
      component: BlogHome,
      children: [
        {
          path: "",
          name: "MyPost",
          component: () => import("@/components/blogHomeCom/MyPost.vue")
        },
        {
          path: "mymemo",
          name: "MyMemo",
          component: () => import("@/components/blogHomeCom/MyMemo.vue")
        },
        {
          path: "mylikepost",
          name: "MyLikePost",
          component: () => import("@/components/blogHomeCom/MyLikePost.vue")
        },
        {
          path: "mysubscriber",
          name: "MySubscriber",
          component: () => import("@/components/blogHomeCom/MySubscriber.vue")
        },
      
      ],
      redirect: () => {
        return "/bloghome";
      }
    },
    {
      path: '/createpost',
      name: "CreatePost",
      component: CreatePost,
    }
 
  ]
})
