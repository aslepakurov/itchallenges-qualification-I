package me.itchallenges.pathfinder.domain.rest;

/**
 * Date: 06/08/2016 5:31 PM
 */
public class PathSubmitRequest {
    private String pointA;
    private String pointB;
    private long estimate;  //in minutes

    public PathSubmitRequest() {
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }

    public long getEstimate() {
        return estimate;
    }

    public void setEstimate(long estimate) {
        this.estimate = estimate;
    }
}
