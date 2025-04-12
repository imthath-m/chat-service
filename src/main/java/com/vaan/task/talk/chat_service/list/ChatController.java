package com.vaan.task.talk.chat_service.list;

import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Chat> getAllChatsByUserId(@PathVariable String userId) {
        return chatService.getAllChatsByUserId(userId);
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Chat getChatById(@PathVariable String id) {
        return chatService.getChatById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chat createChat(@RequestParam String title, @RequestParam String userId) {
        return chatService.createChat(title, userId);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Chat updateChat(@PathVariable String id, @RequestParam String title) {
        try {
            return chatService.updateChat(id, title);
        } catch (RuntimeException e) {
            throw new RuntimeException("Chat not found");
        }
    }
    
    @PutMapping("/{id}/archive")
    @ResponseStatus(HttpStatus.OK)
    public void archiveChat(@PathVariable String id) {
        chatService.archiveChat(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
    }
    
    @PutMapping("/{id}/last-message")
    @ResponseStatus(HttpStatus.OK)
    public void updateLastMessage(@PathVariable String id, @RequestParam String lastMessage) {
        chatService.updateLastMessage(id, lastMessage);
    }
}

