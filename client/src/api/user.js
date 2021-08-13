import {createInstance} from "./index.js";

const instance = createInstance();

function login(user, success, fail){
    instance.defaults.headers["accesss-token"] = window.localStorage.getItem(
      "access-token"
    );
    instance
    .post("user/signin", JSON.stringify(user))
    .then(success)
    .catch(fail);
}
function join(user, success, fail){
  instance
  .post("user", JSON.stringify(user))
  .then(success)
  .catch(fail);
}
function getFollower(userId, success, fail){
    instance
    .get(`user/showFollower/${userId}`)
    .then(success)
    .catch(fail);
}
function getFollowing(userId, success, fail){
    instance
    .get(`user/showFollowing/${userId}`)
    .then(success)
    .catch(fail);
}
function getFollowWait(userId, success, fail){
    instance
    .put(`user/beforeFollowList/${userId}`, JSON.stringify(userId))
    .then(success)
    .catch(fail);
}
function getUserByNickname(user, success, fail){
    instance
    .get(`user/findNickname/${user}`)
    .then(success)
    .catch(fail);
}
export {login, join, getFollower, getFollowing, getFollowWait, getUserByNickname};