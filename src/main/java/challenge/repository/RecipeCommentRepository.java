package challenge.repository;

import challenge.domain.RecipeComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCommentRepository extends MongoRepository<RecipeComment, String> {
}
