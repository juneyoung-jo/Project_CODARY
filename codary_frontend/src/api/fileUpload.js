import { createInstanceFile } from "./index.js";

const instance = createInstanceFile();

function fileUpload(file, success, fail) {

    instance
        .post(`file/upload`,file)
        .then(success)
        .catch(fail);
}

export {fileUpload};