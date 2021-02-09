import { createInstance } from "./index.js";

const instance = createInstance();

function personalList(userData, success, fail) {

    instance
        .get(`personal/${userData.blogId}`)
        .then(success)
        .catch(fail);
}

function showMyMemo(userData, success, fail) {

    instance
        .get(`personal/memo/${userData.memoId}`)
        .then(success)
        .catch(fail);
}

function showMyBloger(userData, success, fail) {

    instance
        .get(`personal/bloger/${userData.blogId}/${userData.uId}`)
        .then(success)
        .catch(fail);
}

function showMyBlogContents(userData, success, fail) {

    instance
        .get(`personal/blog/${userData.blogId}/${userData.uId}`)
        .then(success)
        .catch(fail);
}

function showJandi(userData, success, fail) {

    instance
        .get(`personal/jandi/${userData.blogId}`)
        .then(success)
        .catch(fail);
}

function userGraph(userData, success, fail) {

    instance
        .get(`personal/usergraph/${userData.blogId}`)
        .then(success)
        .catch(fail);
}

  //팔로우
function blogerLike(blogerLike, success, fail) {
    instance
      .post(`personal/blogerLike`, blogerLike)
      .then(success)
      .catch(fail);
  }
  
  //언팔로우
function blogerUnlike(blogerLike, success, fail) {
    instance
      .post(`personal/blogerUnlike`, blogerLike)
      .then(success)
      .catch(fail);
  }

  //팔로우 여부
function readBlogerlike(blogerLike, success, fail) {
    instance
      .post(`personal/checkBlogerlike`, blogerLike)
      .then(success)
      .catch(fail);
  }
  

export {
    personalList, 
    showMyMemo, 
    showMyBloger, 
    showMyBlogContents, 
    showJandi, 
    userGraph,
    blogerLike,
    blogerUnlike,
    readBlogerlike
};