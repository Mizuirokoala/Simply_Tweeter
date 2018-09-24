package pl.mizuirokoala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mizuirokoala.entity.Tweet;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	Tweet findOneByUserId(Long id);

	List<Tweet> findAllByUserIdOrderByCreatedDesc(Long id);
	
	@Query("SELECT t FROM Tweet t ORDER BY t.created DESC")
	List<Tweet> findAllOrderByCreated();

	@Query("SELECT t FROM Tweet t WHERE t.tweetText LIKE %:term% ORDER BY t.created DESC")
	List<Tweet> findTweetLike(@Param("term") String term);

}
