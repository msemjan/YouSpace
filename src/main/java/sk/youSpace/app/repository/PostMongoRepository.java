package sk.youSpace.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sk.youSpace.app.model.Post;

import java.util.List;

@Repository
public interface PostMongoRepository extends MongoRepository<Post, ObjectId> {
    @Query(value="{id: ?0}")
    Post findPostById(ObjectId id);

    @Query(value="{$or: [{text: /?0/}, {title: /?0/}]}", sort="{added: -1}")
    List<Post> searchPosts(String query);

    @Query(value="{id: {$exists: 1}}", sort="{added: -1}")
    List<Post> findAll();
}
