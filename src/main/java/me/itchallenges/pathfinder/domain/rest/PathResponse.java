package me.itchallenges.pathfinder.domain.rest;

/**
 * Date: 06/08/2016 5:32 PM
 */
public class PathResponse extends Response {
    private ResponseType type;

    public PathResponse() {
        super();
    }

    public PathResponse(ResponseType type, String message, boolean error) {
        super(message, error);
        this.type = type;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
