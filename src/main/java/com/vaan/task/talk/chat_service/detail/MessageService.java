package com.vaan.task.talk.chat_service.detail;

import org.springframework.ai.chat.messages.MessageType;
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
        }

        // Create user message
        Message userMessage = new Message(chatId, content, MessageType.USER.getValue());
        saveMessage(userMessage);

        // Create assistant message (empty for now)
        Message assistantMessage = new Message(
                chatId,
                llmService.continueChat(content, chatId),
                MessageType.ASSISTANT.getValue()
        );

        return saveMessage(assistantMessage);
    }
}
