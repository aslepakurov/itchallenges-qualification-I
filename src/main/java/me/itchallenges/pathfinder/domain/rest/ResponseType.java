package me.itchallenges.pathfinder.domain.rest;

/**
 * Date: 06/08/2016 5:32 PM
 */
public enum ResponseType {
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted"),
    NONDELETED("non-deleted"),
    ESTIMATE("estimate");

    private String name;

    ResponseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
