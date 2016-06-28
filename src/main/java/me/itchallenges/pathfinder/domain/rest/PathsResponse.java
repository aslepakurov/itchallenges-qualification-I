package me.itchallenges.pathfinder.domain.rest;

import me.itchallenges.pathfinder.domain.Path;

import java.util.List;

/**
 * Date: 06/10/2016 3:38 AM
 */
public class PathsResponse extends Response {
    private List<Path> paths;
    public PathsResponse() {
    }

    public PathsResponse(List<Path> paths, String message, boolean error) {
        super(message, error);
        this.paths = paths;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
