import {createInstance} from "./index.js";

const instance = createInstance();

function getPostArea(userId, success, fail){
    instance
    .get(`post/preferedArea/${userId}`)
    .then(success)
    .catch(fail);
}
function getPostStyle(userId, success, fail){
    instance
    .get(`post/preferedStyle/${userId}`)
    .then(success)
    .catch(fail);
}

export {getPostArea, getPostStyle};

