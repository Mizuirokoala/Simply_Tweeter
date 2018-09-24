package pl.mizuirokoala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.mizuirokoala.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.firstName LIKE %:term% OR u.lastName LIKE %:term% OR u.userName LIKE %:term%")
	List<User> findUserLike(@Param("term") String term);
	
}
