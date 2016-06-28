package me.itchallenges.pathfinder.domain.graph;

import me.itchallenges.pathfinder.domain.Path;

import java.util.*;

/**
 * Date: 06/08/2016 6:24 PM
 */
public class Graph {
    private final Map<String, Vertex> graph;

    public Graph(List<Path> paths) {
        graph = new HashMap<>(paths.size());

        for (Path path : paths) {
            if (!graph.containsKey(path.getPointA())) graph.put(path.getPointA(), new Vertex(path.getPointA()));
            if (!graph.containsKey(path.getPointB())) graph.put(path.getPointB(), new Vertex(path.getPointB()));
        }

        for (Path path : paths) {
            graph.get(path.getPointA()).addNeighbour(graph.get(path.getPointB()), path.getEstimate());
            graph.get(path.getPointB()).addNeighbour(graph.get(path.getPointB()), path.getEstimate());
        }
    }


    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for (Vertex v : graph.values()) {
            v.setPrevious(v == source ? source : null);
            v.setEstimate(v == source ? 0 : Long.MAX_VALUE);
            q.add(v);
        }

        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst();
            if (u.getEstimate() == Integer.MAX_VALUE)
                break;

            for (Map.Entry<Vertex, Long> a : u.getNeighbours().entrySet()) {
                v = a.getKey();

                final long alternateDist = u.getEstimate() + a.getValue();
                if (alternateDist < v.getEstimate()) {
                    q.remove(v);
                    v.setEstimate(alternateDist);
                    v.setPrevious(u);
                    q.add(v);
                }
            }
        }
    }

    public long printPath(String endName) {
        if (!graph.containsKey(endName)) {
            return -1;
        }
        return graph.get(endName).getEstimate();
    }
}
