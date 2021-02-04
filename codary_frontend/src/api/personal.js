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

export {personalList, showMyMemo, showMyBloger, showMyBlogContents, showJandi, userGraph};