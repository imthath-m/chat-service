package com.vaan.task.talk.chat_service.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.vaan.task.talk.chat_service.list.ChatService;
import com.vaan.task.talk.chat_service.list.Chat;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ChatService chatService;

    @GetMapping("/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String chatId) {
        return ResponseEntity.ok(messageService.getMessagesByChatId(chatId));
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageRequest request) {
        String chatId = request.getChatId();
        
        // If chatId is not provided, create a new chat
        if (chatId == null || chatId.isEmpty()) {
            Chat newChat = chatService.createChat(request.getTitle(), request.getUserId());
            chatId = newChat.getId();
        }

        // Create user message
        Message userMessage = new Message(chatId, request.getContent(), "user");
        Message savedUserMessage = messageService.saveMessage(userMessage);

        // Create assistant message (empty for now)
        Message assistantMessage = new Message(chatId, "", "assistant");
        Message savedAssistantMessage = messageService.saveMessage(assistantMessage);

        return ResponseEntity.ok(savedAssistantMessage);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }
}

// Request DTO for creating a message
class MessageRequest {
    private String chatId;
    private String content;
    private String userId;
    private String title;

    // Getters and setters
    public String getChatId() { return chatId; }
    public void setChatId(String chatId) { this.chatId = chatId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
