package pl.mizuirokoala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mizuirokoala.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllBySenderId(Long id);

	List<Message> findAllByReceiverId(Long id);

}
