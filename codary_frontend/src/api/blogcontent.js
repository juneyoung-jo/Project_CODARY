import { createInstance } from "./index.js";

const instance = createInstance();

// 블로그 글 작성
function writeContent(blogContent, success, fail) {
  instance
    .post(`blog`, blogContent)
    .then(success)
    .catch(fail);
}

// 블로그 글 삭제
function deleteContent(blogContent, success, fail) {
  instance
    .delete(`blog/${blogContent.blogId}/${blogContent.blogContentsId}`)
    .then(success)
    .catch(fail);
}

// 블로그 글 수정
function modifyContent(blogContent, success, fail) {
  instance
    .put(`blog`, blogContent)
    .then(success)
    .catch(fail);
}

// 블로그 글 보기
function getContent(blogId, blogContentsId, success, fail) {
  instance
    .get(`blog/${blogId}/${blogContentsId}`)
    .then(success)
    .catch(fail);
}

// 내 블로그 글 보기
function getMyContent(uid, blogId, blogContentsId, success, fail) {
  instance
    .get(`blog/log/${uid}/${blogId}/${blogContentsId}`)
    .then(success)
    .catch(fail);
}

// 블로그 아이디로 유저 정보 가져오기
function getUserinfo(blogId, success, fail) {
  instance
    .get(`blog/blogUserInfo/${blogId}`)
    .then(success)
    .catch(fail);
}

//블로그 글 좋아요 눌렀는지 여부
function contentsLikeOrNot(blogContentsLike, success, fail) {
  instance
    .post(`blog/checkContentsLike`, blogContentsLike)
    .then(success)
    .catch(fail);
}

//블로그 글 좋아요 누르기
function contentsLike(blogContentsLike, success, fail) {
  instance
    .post(`blog/contentsLike`, blogContentsLike)
    .then(success)
    .catch(fail);
}

//블로그 글 좋아요 취소하기
function contentsUnlike(blogContentsLike, success, fail) {
  instance
    .post(`blog/contentsUnlike`, blogContentsLike)
    .then(success)
    .catch(fail);
}

//블로그 글 추천
function recommend(success, fail) {
  instance
    .get(`blog/recommend`)
    .then(success)
    .catch(fail);
}

export {
  writeContent,
  deleteContent,
  modifyContent,
  getContent,
  getMyContent,
  getUserinfo,
  contentsLikeOrNot,
  contentsLike,
  contentsUnlike,
  recommend,
};