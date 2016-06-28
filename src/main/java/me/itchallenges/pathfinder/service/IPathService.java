package me.itchallenges.pathfinder.service;

import me.itchallenges.pathfinder.domain.rest.*;
import me.itchallenges.pathfinder.exception.BadRequestException;

/**
 * Date: 06/08/2016 11:40 PM
 */
public interface IPathService {
    PathResponse submit(PathSubmitRequest request) throws BadRequestException;
    PathResponse delete(PathSubmitRequest request) throws BadRequestException;
    EstimateResponse countEstimate(EstimateRequest request) throws BadRequestException;
    PathsResponse getPaths();
}
