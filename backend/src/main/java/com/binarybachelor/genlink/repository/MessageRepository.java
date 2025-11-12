package  com.binarybachelor.genlink.repository;

import com.binarybachelor.genlink.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.binarybachelor.genlink.entity.UserEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{

    List<MessageEntity> findBySenderIdAndReceiverIdOrderByCreatedAtAsc(UserEntity senderId, UserEntity receiverId);
}