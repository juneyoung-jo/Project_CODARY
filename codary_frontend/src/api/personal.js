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
    .post(`personal/mymemo`, memoId)
    .then(success)
    .catch(fail);
}

function showMyBloger(like, success, fail) {
  instance
    .post(`personal/likebloger`, like)
    .then(success)
    .catch(fail);
}

function showMyBlogContents(like, success, fail) {
  instance
    .post(`personal/likepost`, like)
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
  // console.log("# " + uid + " 로 프로필 정보 읽어오기!!");
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
  // console.log("#유저 정보 수정!!");
  instance
    .post(`user/updateInfo`, userinfo)
    .then(success)
    .catch(fail);
}

/**
 * 내 블로그 정보 읽어오기
 */
function getBlogInfo(blogId, success, fail) {
  instance
    .get(`personal/blogInfo/${blogId}`)
    .then(success)
    .catch(fail);
}

function modifyBlogInfo(blogCover, blogId, success, fail) {
  const body = {
    blogCover: blogCover,
    blogId: blogId,
  };

  instance
    .put("personal/modifyBlogCover", body)
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
  updateUserinfo,
  getBlogInfo,
  modifyBlogInfo,
};
