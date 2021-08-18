import {createInstance} from "./index.js";

const instance = createInstance();


function getUserInfo(userId, success, fail){
    instance
    .get(`user/${userId}`)
    .then(success)
    .catch(fail);
}
function getUserInfoEmail(userEmail, success, fail){
    instance
    .get(`user/findEmail/${userEmail}/1`)
    .then(success)
    .catch(fail);
}

export {getUserInfo, getUserInfoEmail};