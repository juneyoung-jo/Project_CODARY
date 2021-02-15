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

/**
 * 내 블로그 홈에서 프로필 정보 읽어오는 함수
 */
function getUserInfo(uid, success, fail) {
    console.log("# "+ uid+  " 로 프로필 정보 읽어오기!!")
    instance
        .get(`user/profileInfo/${uid}`)
        .then(success)
        .catch(fail);
}

/**
 * 타인 블로그 홈에서 프로필 정보 읽어오는 함수
 */
function getOtherInfo(blogId, success, fail) {
    instance
        .get(`personal/userinfo/${blogId}`)
        .then(success)
        .catch(fail);
}

/**
 * 프로필 정보 수정 함수
 */
function updateUserinfo(userinfo, success, fail) {
    console.log("#유저 정보 수정!!")
    instance
        .post(`user/updateInfo`, userinfo)
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
    readBlogerlike,
    getUserInfo,
    getOtherInfo,
    updateUserinfo
};