import { createInstance } from "./index.js";

const instance = createInstance();

function writeLog(uid,blogId,blogContentsId, success, fail) {

    instance
        .get(`blog/log/${uid}/${blogId}/${blogContentsId}`)
        .then(success)
        .catch(fail);
}


export {writeLog};