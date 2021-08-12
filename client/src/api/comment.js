import {createInstance} from "./index.js";

const instance = createInstance();

function writeComment(commentInfo, success, fail){
    instance
    .post(`comment`, JSON.stringify(commentInfo))
    .then(success)
    .catch(fail);
}
function deleteComment(commentid, success, fail){
    instance
    .delete(`comment/${commentid}`)
    .then(success)
    .catch(fail);
}
function getComment(postId, success, fail){
    instance
    .get(`comment/likeByPost/${postId}`)
    .then(success)
    .catch(fail);
}

export {writeComment, deleteComment, getComment};