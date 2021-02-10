import { createInstance } from "./index.js";

const instance = createInstance();

function personalList(blogId, success, fail) {

    instance
        .get(`personal/${blogId}`)
        .then(success)
        .catch(fail);
}

function showMyMemo(memoId, success, fail) {

    instance
        .get(`personal/memo/${memoId}`)
        .then(success)
        .catch(fail);
}

function showMyBloger(blogId, uId, success, fail) {

    instance
        .get(`personal/bloger/${blogId}/${uId}`)
        .then(success)
        .catch(fail);
}

function showMyBlogContents(blogId, uId, success, fail) {

    instance
        .get(`personal/blog/${blogId}/${uId}`)
        .then(success)
        .catch(fail);
}

function showJandi(blogId, success, fail) {

    instance
        .get(`personal/jandi/${blogId}`)
        .then(success)
        .catch(fail);
}

function userGraph(blogId, success, fail) {

    instance
        .get(`personal/usergraph/${blogId}`)
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