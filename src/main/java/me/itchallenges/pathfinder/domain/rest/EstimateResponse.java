package me.itchallenges.pathfinder.domain.rest;

import me.itchallenges.pathfinder.domain.Path;

import java.util.List;

/**
 * Date: 06/08/2016 5:44 PM
 */
public class EstimateResponse extends Response {
    private String pointA;
    private String pointB;
    private long estimate;      //in minutes

    public EstimateResponse() {
        super();
    }

    public EstimateResponse(String pointA, String pointB, long estimate, String message, boolean error) {
        super(message, error);
        this.pointA = pointA;
        this.pointB = pointB;
        this.estimate = estimate;
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
