package com.been.beenbackend.dto;

public class follow {
    private int followerID;
    private int followedID;
    private int accepted;

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public int getFollowedID() {
        return followedID;
    }

    public void setFollowedID(int followedID) {
        this.followedID = followedID;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}
