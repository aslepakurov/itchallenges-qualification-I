package me.itchallenges.pathfinder.util;

import me.itchallenges.pathfinder.domain.rest.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Date: 06/10/2016 2:14 AM
 */
public class ResponseUtil {
    public static ResponseEntity buildResponse(Response response, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(response);
    }
}
