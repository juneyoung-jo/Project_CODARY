import { createInstance } from "./index.js";

const instance = createInstance();

function commentList(blogContents, success, fail) {

    instance
        .get(`comment/commentAll/${blogContents.blogId}/${blogContents.blogContentsId}`)
        .then(success)
        .catch(fail);
}

function getUserInfo(user, success, fail) {

    instance
        .post(`comment/getUserInfo`,user)
        .then(success)
        .catch(fail);
}

function writeComment(comment, success, fail) {

    instance
        .post(`comment/writeComment`,comment)
        .then(success)
        .catch(fail);
}

function deleteComment(commentNum, success, fail) {

    instance
        .delete(`comment/deleteComment/${commentNum}`)
        .then(success)
        .catch(fail);
}

export {commentList,getUserInfo,writeComment,deleteComment};