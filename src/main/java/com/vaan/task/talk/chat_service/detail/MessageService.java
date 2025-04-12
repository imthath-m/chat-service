package com.vaan.task.talk.chat_service.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.vaan.task.talk.chat_service.list.ChatService;
import com.vaan.task.talk.chat_service.list.Chat;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private ChatService chatService;

    @Autowired
    private LLMService llmService;

    public List<Message> getMessagesByChatId(String chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chatId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(String messageId) {
        messageRepository.deleteById(messageId);
    }

    @Transactional
    public Message sendMessage(String chatId, String content, String userId) {
        // If chatId is not provided, create a new chat
        if (chatId == null || chatId.isEmpty()) {
            Chat newChat = chatService.createChat("title", userId);
            chatId = newChat.getId();
//        } else {
//            var messages = getMessagesByChatId(chatId);
//            // loop through messages and create a new string with all the messages and the roles
//            StringBuilder allMessages = new StringBuilder();
//            for (Message message : messages) {
//                allMessages.append(message.getRole()).append(": ").append(message.getContent()).append("\n");
//            }
//            allMessages.append("user: ").append(content).append("\n");
//            content = allMessages.toString();
        }

        // Create user message
        Message userMessage = new Message(chatId, content, "user");
        Message savedUserMessage = saveMessage(userMessage);

        // Create assistant message (empty for now)
        Message assistantMessage = new Message(chatId, llmService.getAnswer(content), "assistant");

        return saveMessage(assistantMessage);
    }
}
