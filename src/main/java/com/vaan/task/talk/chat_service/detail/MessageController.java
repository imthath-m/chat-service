package com.vaan.task.talk.chat_service.detail;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/{chatId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessages(@PathVariable String chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    Message sendMessage(@RequestBody MessageRequest request) {
        return messageService.sendMessage(
            request.getChatId(), 
            request.getContent(), 
            request.getUserId(),
            request.getModel()
        );
    }

    @DeleteMapping("/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable String messageId) {
        messageService.deleteMessage(messageId);
    }
}

// Request DTO for creating a message
@Getter
class MessageRequest {
    private String chatId;
    private String content;
    private String userId;
    private String model;
}
