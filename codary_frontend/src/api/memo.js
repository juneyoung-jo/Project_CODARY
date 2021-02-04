import { createInstance } from "./index.js";

const instance = createInstance();

function writeMemo(memodata, success, fail) {

    instance
        .post(`memo`,memodata)
        .then(success)
        .catch(fail);
}

function changeMemo(memodata, success, fail) {

    instance
        .put(`memo`,memodata)
        .then(success)
        .catch(fail);
}

function memoList(memoId, success, fail) {

    instance
        .get(`memo/${memoId}`)
        .then(success)
        .catch(fail);
}

function deleteMemo(memodata, success, fail) {

    instance
        .delete(`memo/${memodata.memoId}/${memodata.memoNum}`)
        .then(success)
        .catch(fail);
}

export {writeMemo, changeMemo, memoList, deleteMemo};