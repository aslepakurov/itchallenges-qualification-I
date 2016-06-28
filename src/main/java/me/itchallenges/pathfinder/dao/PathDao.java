package me.itchallenges.pathfinder.dao;

import me.itchallenges.pathfinder.domain.Path;
import me.itchallenges.pathfinder.service.IMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Date: 06/08/2016 5:58 PM
 */
@Component
public class PathDao implements IPathDao {
    private static final Logger LOG = Logger.getLogger(PathDao.class);

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private IMessageService messages;

    @Override
    public String create(Path path) {
        if (!collectionExist()) {
            LOG.info(messages.getMessage("dao.create.scheme", Path.class.toString()));
            mongoOperations.createCollection(Path.class);
        }
        if (path.getId() != null) {
            return path.getId();
        }
        String id = UUID.randomUUID().toString();
        path.setId(id);
        mongoOperations.save(path);
        String pointA = path.getPointA();
        String pointB = path.getPointB();
        path.setId(UUID.randomUUID().toString());
        path.setPointA(pointB);
        path.setPointB(pointA);
        mongoOperations.save(path);
        LOG.info(messages.getMessage("path.create", path.getPointA(), path.getPointB(), path.getEstimate()));
        return path.getId();
    }

    @Override
    public Path get(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Path.class);
    }

    @Override
    public Path get(String pointA, String pointB) {
        Query query = new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB)));
        return mongoOperations.findOne(query, Path.class);
    }

    @Override
    public boolean delete(String id) {
        mongoOperations.remove(new Query(Criteria.where("id").is(id)));
        return get(id) == null;
    }

    @Override
    public boolean delete(String pointA, String pointB) {
        mongoOperations.remove(new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB))), Path.class);
        mongoOperations.remove(new Query(Criteria.where("pointB").is(pointA).andOperator(Criteria.where("pointA").is(pointB))), Path.class);
        LOG.info(messages.getMessage("path.delete", pointA, pointB));
        return get(pointA, pointB) == null;
    }

    @Override
    public void update(String pointA, String pointB, long estimate) {
        mongoOperations.updateFirst(new Query(Criteria.where("pointA").is(pointA).andOperator(Criteria.where("pointB").is(pointB))), Update.update("estimate", estimate), Path.class);
        mongoOperations.updateFirst(new Query(Criteria.where("pointB").is(pointA).andOperator(Criteria.where("pointA").is(pointB))), Update.update("estimate", estimate), Path.class);
        LOG.info(messages.getMessage("path.update", pointA, pointB, estimate));
    }

    @Override
    public List<Path> getPaths() {
        List<Path> allPaths = mongoOperations.findAll(Path.class);
        Path[] pathArray = new Path[allPaths.size()];
        pathArray = allPaths.toArray(pathArray);
        LOG.info(messages.getMessage("path.all", pathArray.length));
        return Arrays.asList(pathArray);
    }

    private boolean collectionExist() {
        return mongoOperations.collectionExists(Path.class);
    }
}
