package me.itchallenges.pathfinder.domain.rest;

/**
 * Date: 06/10/2016 2:18 AM
 */
public class Response {
    private String message;
    private boolean error;

    public Response() {
    }

    public Response(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
