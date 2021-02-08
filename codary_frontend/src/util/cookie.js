
function saveUserToCookie(uid,blogId,memoId) {
    const date = new Date();
    date.setTime(date.getTime() + 1*24*60*60);
      document.cookie = `uid=${uid};expires=${date.toUTCString()};path=/`;
      document.cookie = `blogId=${blogId};expires=${date.toUTCString()};path=/`;
      document.cookie = `memoId=${memoId};expires=${date.toUTCString()};path=/`;

  }

function getuidCookie() {
return document.cookie.replace(
    /(?:(?:^|.*;\s*)user\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
);
}
  
function getblogIdCookie() {
    return document.cookie.replace(
      /(?:(?:^|.*;\s*)blogId\s*=\s*([^;]*).*$)|^.*$/,
      '$1',
    );
}
  
function getmemoIdCookie() {
    return document.cookie.replace(
      /(?:(?:^|.*;\s*)memoId\s*=\s*([^;]*).*$)|^.*$/,
      '$1',
    );
  }
  
  function deleteCookie() {
      document.cookie = `uid=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
      document.cookie = `blogId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
      document.cookie = `memoId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  }
  
  export {
    saveUserToCookie,
    getuidCookie,
    getblogIdCookie,
    getmemoIdCookie,
    deleteCookie,
  };