package me.itchallenges.pathfinder.dao;

import me.itchallenges.pathfinder.domain.Path;

import java.util.List;

/**
 * Date: 06/08/2016 5:53 PM
 */
public interface IPathDao {
    String create(Path path);
    Path get(String id);
    Path get(String pointA, String pointB);
    boolean delete(String id);
    boolean delete(String pointA, String pointB);
    void update(String pointA, String pointB, long estimate);
    List<Path> getPaths();
}
