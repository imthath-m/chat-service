package com.vaan.task.talk.chat_service.list;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findByUserId(String userId);
    List<Chat> findByUserIdAndIsArchivedFalse(String userId);
}
