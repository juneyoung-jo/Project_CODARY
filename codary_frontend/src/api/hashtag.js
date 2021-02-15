import { createInstanceFile } from "./index.js";

const instance = createInstanceFile();

function getTagList(success, fail) {

    instance
        .get(`blog/getHashtag`)
        .then(success)
        .catch(fail);
}

function getTagKey(value, success, fail) {
    instance
        .post(`blog/getTagKey`, value)
        .then(success)
        .catch(fail);
}

export {getTagList, getTagKey};