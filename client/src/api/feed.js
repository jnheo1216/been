import {createInstance} from "./index.js";

const instance = createInstance();

function getFeedAll(success, fail){
    instance
    .get(`post`)
    .then(success)
    .catch(fail);
}
function postFeed(post, success, fail){
    instance
    .post(`post`, JSON.stringify(post))
    .then(success)
    .catch(fail);
}
function getFeedDetail(postId, success, fail){
    instance
    .get(`post/${postId}`)
    .then(success)
    .catch(fail);
}
function getFeedUserId(userId, success, fail){
    instance
    .get(`post/userId/${userId}/1`)
    .then(success)
    .catch(fail);
}
function getFeedFollowPost(userId, success, fail){
    instance
    .get(`post/followPost/${userId}`)
    .then(success)
    .catch(fail);
}
function getFeedLike(userId, success, fail){
    instance
    .get(`post/like/${userId}`)
    .then(success)
    .catch(fail);
}
function getFeedLikeCnt(postId, success, fail){
    instance
    .get(`post/likeCnt/${postId}`)
    .then(success)
    .catch(fail);
}
function postLike(userpostInfo, success, fail){
    instance
    .post(`post/like`, JSON.stringify(userpostInfo))
    .then(success)
    .catch(fail);
}
function postLikeDelete(userpostInfo, success, fail){
    instance
    .delete(`post/like/delete/${userpostInfo.postId}/${userpostInfo.userId}`)
    .then(success)
    .catch(fail);
}
function postIsLike(userpostInfo, success, fail){
    instance
    .get(`post/like/${userpostInfo.postId}/{userId}?userId=${userpostInfo.userId}`)
    .then(success)
    .catch(fail);
}

export {getFeedAll, postFeed, getFeedDetail, getFeedFollowPost, getFeedUserId, getFeedLike, getFeedLikeCnt, postLike, postLikeDelete, postIsLike};