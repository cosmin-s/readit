package mysoftwarefactory.readit.dao;

import mysoftwarefactory.readit.model.Post;
import mysoftwarefactory.readit.model.ThreadPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadPostRepository extends JpaRepository<ThreadPost, Long> {
}
