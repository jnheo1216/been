import {createInstance} from "./index.js";

const instance = createInstance();


function getUserInfo(userId, success, fail){
    instance
    .get(`user/${userId}`)
    .then(success)
    .catch(fail);
}

export {getUserInfo};