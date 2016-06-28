package me.itchallenges.pathfinder.domain.rest;

/**
 * Date: 06/08/2016 5:47 PM
 */
public class EstimateRequest {
    private String pointA;
    private String pointB;

    public EstimateRequest() {
    }

    public EstimateRequest(String pointA, String pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
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
}
