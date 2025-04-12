package com.vaan.task.talk.chat_service.list;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    
    private final ChatRepository chatRepository;
    
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    
    public List<Chat> getAllChatsByUserId(String userId) {
        return chatRepository.findByUserIdAndIsArchivedFalse(userId);
    }
    
    public Optional<Chat> getChatById(String id) {
        return chatRepository.findById(id);
    }
    
    public Chat createChat(String title, String userId) {
        Chat chat = new Chat(title, userId);
        return chatRepository.save(chat);
    }
    
    public Chat updateChat(String id, String title) {
        return chatRepository.findById(id)
                .map(chat -> {
                    chat.setTitle(title);
                    return chatRepository.save(chat);
                })
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + id));
    }
    
    public void archiveChat(String id) {
        chatRepository.findById(id)
                .ifPresent(chat -> {
                    chat.setArchived(true);
                    chatRepository.save(chat);
                });
    }
    
    public void deleteChat(String id) {
        chatRepository.deleteById(id);
    }
    
    public void updateLastMessage(String id, String lastMessage) {
        chatRepository.findById(id)
                .ifPresent(chat -> {
                    chat.setLastMessage(lastMessage);
                    chatRepository.save(chat);
                });
    }
}
