package me.itchallenges.pathfinder.service;

import me.itchallenges.pathfinder.dao.IPathDao;
import me.itchallenges.pathfinder.domain.Path;
import me.itchallenges.pathfinder.domain.graph.Graph;
import me.itchallenges.pathfinder.domain.rest.*;
import me.itchallenges.pathfinder.exception.BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Date: 06/10/2016 2:10 AM
 */
@Component
public class PathService implements IPathService {
    private static final Logger LOG = Logger.getLogger(PathService.class);

    @Autowired
    private IMessageService messages;
    @Autowired
    private IPathDao pathDao;

    @Override
    public PathResponse submit(PathSubmitRequest request) throws BadRequestException {
        String pointA = request.getPointA();
        String pointB = request.getPointB();
        long estimate = request.getEstimate();

        if (!StringUtils.hasText(pointA) || !StringUtils.hasText(pointB) || estimate < 0) {
            throw new BadRequestException(messages.getMessage("error.empty.field"));
        }
        Path path = pathDao.get(pointA, pointB);
        if (path == null) {
            pathDao.create(new Path(pointA, pointB, estimate));
            return new PathResponse(ResponseType.CREATED, null, false);
        } else {
            pathDao.update(pointA, pointB, estimate);
            return new PathResponse(ResponseType.UPDATED, null, false);
        }
    }

    @Override
    public PathResponse delete(PathSubmitRequest request) throws BadRequestException {
        String pointA = request.getPointA();
        String pointB = request.getPointB();
        long estimate = request.getEstimate();

        if (!StringUtils.hasText(pointA) || !StringUtils.hasText(pointB) || estimate < 0) {
            throw new BadRequestException(messages.getMessage("error.empty.field"));
        }
        if (!pathDao.delete(pointA, pointB)) {
            String message = messages.getMessage("no.path.found", pointA, pointB);
            LOG.warn(message);
            return new PathResponse(ResponseType.NONDELETED, message, false);
        } else {
            String message = messages.getMessage("path.delete", pointA, pointB);
            LOG.warn(message);
            return new PathResponse(ResponseType.DELETED, message, false);
        }
    }

    @Override
    public EstimateResponse countEstimate(EstimateRequest request) throws BadRequestException {
        String pointA = request.getPointA();
        String pointB = request.getPointB();

        if (!StringUtils.hasText(pointA) || !StringUtils.hasText(pointB)) {
            throw new BadRequestException(messages.getMessage("error.empty.field"));
        }
        Graph graph = new Graph(pathDao.getPaths());
        graph.dijkstra(pointA);
        long estimationTime = graph.printPath(pointB);
        if (estimationTime == Long.MAX_VALUE || estimationTime == -1) {
            String message = messages.getMessage("estimate.not.found", pointA, pointB);
            LOG.info(message);
            return new EstimateResponse(pointA, pointB, -1, message, false);
        } else {
            String message = messages.getMessage("estimate.found", pointA, pointB, estimationTime);
            LOG.info(message);
            return new EstimateResponse(pointA, pointB, estimationTime, message, false);
        }
    }

    @Override
    public PathsResponse getPaths() {
        List<Path> paths = pathDao.getPaths();
        StringBuilder sb = new StringBuilder();
        for (Path path : paths) {
            sb.append("\n").append(String.format("From %s to %s in %d minutes;", path.getPointA(), path.getPointB(), path.getEstimate()));
        }
        return new PathsResponse(paths, sb.toString(), false);
    }
}
