package me.itchallenges.pathfinder.domain;

/**
 * Date: 06/08/2016 5:29 PM
 */
public class Path {
    private String id;
    private String pointA;
    private String pointB;
    private long estimate;      //in minutes

    public Path() {
    }

    public Path(String pointA, String pointB, long estimate) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.estimate = estimate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
