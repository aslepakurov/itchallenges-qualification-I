package me.itchallenges.pathfinder.rest;

import me.itchallenges.pathfinder.domain.rest.*;
import me.itchallenges.pathfinder.service.IMessageService;
import me.itchallenges.pathfinder.service.IPathService;
import me.itchallenges.pathfinder.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 06/08/2016 5:03 PM
 */
@RestController
@RequestMapping("/pathfinder")
public class PathfinderController {
    private static final Logger LOG = Logger.getLogger(PathfinderController.class);

    @Autowired
    private IMessageService messages;
    @Autowired
    private IPathService pathService;

    @RequestMapping(value = "/path", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity submitPath(@RequestBody PathSubmitRequest request) {
        try {
            PathResponse response = pathService.submit(request);
            return ResponseUtil.buildResponse(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseUtil.buildResponse(
                    new Response(message, true), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/path", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePath(@RequestBody PathSubmitRequest request) {
        try {
            PathResponse response = pathService.delete(request);
            return ResponseUtil.buildResponse(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseUtil.buildResponse(
                    new Response(message, true), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/paths", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPaths() {
        return ResponseUtil.buildResponse(pathService.getPaths(), HttpStatus.OK);
    }

    @RequestMapping(value = "/estimate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity estimate(@RequestBody EstimateRequest request) {
        try {
            EstimateResponse response = pathService.countEstimate(request);
            return ResponseUtil.buildResponse(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseUtil.buildResponse(
                    new Response(message, true), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
