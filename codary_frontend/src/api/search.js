import { createInstance } from "./index.js";

const instance = createInstance();

function getByDate(keyword, success, fail) {
  instance
    .get(`search/date/${keyword}`)
    .then(success)
    .catch(fail);
}

function hashByDate(keywords, success, fail) {
  instance
    .post(`search/date`, keywords)
    .then(success)
    .catch(fail);
}

function getByView(keyword, success, fail) {
  instance
    .get(`search/view/${keyword}`)
    .then(success)
    .catch(fail);
}

function hashByView(keywords, success, fail) {
  instance
    .get(`search/view`, keywords)
    .then(success)
    .catch(fail);
}

function getByLike(keyword, success, fail) {
  instance
    .get(`search/like/${keyword}`)
    .then(success)
    .catch(fail);
}

function hashByLike(keywords, success, fail) {
  instance
    .get(`search/like`, keywords)
    .then(success)
    .catch(fail);
}

export { getByDate, hashByDate, getByView, hashByView, getByLike, hashByLike };
