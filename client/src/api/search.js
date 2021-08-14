import {createInstance} from "./index.js";

const instance = createInstance();

function searchUserNickname(userNickname, success, fail){
    instance
    .get(`user/findNickname/${userNickname}`)
    .then(success)
    .catch(fail);
}
function searchUserEmail(userEmail, success, fail){
    instance
    .get(`user/findEmail/${userEmail}`)
    .then(success)
    .catch(fail);
}
function searchPostTitle(postTitle, success, fail){
    instance
    .get(`post/searchByTitle/${postTitle}`)
    .then(success)
    .catch(fail);
}

export {searchUserNickname, searchUserEmail, searchPostTitle};