package me.itchallenges.pathfinder.domain.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 06/08/2016 6:25 PM
 */
public class Vertex implements Comparable<Vertex>{
    private String name;
    private Long estimate;
    private Vertex previous;
    private Map<Vertex, Long> neighbours;

    public Vertex(String name) {
        this.name = name;
        this.estimate = Long.MAX_VALUE;
        this.previous = null;
        this.neighbours = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setEstimate(Long estimate) {
        this.estimate = estimate;
    }

    public long getEstimate() {
        return estimate;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public Map<Vertex, Long> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Vertex vertex, Long estimate) {
        neighbours.put(vertex, estimate);
    }

    public int compareTo(Vertex other) {
        return Long.compare(estimate, other.estimate);
    }
}
