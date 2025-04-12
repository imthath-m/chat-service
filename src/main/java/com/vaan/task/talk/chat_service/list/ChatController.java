package com.vaan.task.talk.chat_service.list;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    
    private final ChatService chatService;
    
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Chat>> getAllChatsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(chatService.getAllChatsByUserId(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        return chatService.getChatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestParam String title, @RequestParam String userId) {
        return ResponseEntity.ok(chatService.createChat(title, userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable String id, @RequestParam String title) {
        try {
            return ResponseEntity.ok(chatService.updateChat(id, title));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveChat(@PathVariable String id) {
        chatService.archiveChat(id);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/last-message")
    public ResponseEntity<Void> updateLastMessage(@PathVariable String id, @RequestParam String lastMessage) {
        chatService.updateLastMessage(id, lastMessage);
        return ResponseEntity.ok().build();
    }
}

