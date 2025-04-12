package com.vaan.task.talk.chat_service.detail;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private String id;
    private String chatId;
    private String content;
    private String role; // "user" or "assistant"
    
    @CreatedDate
    private Instant createdAt;

    // Constructor with required fields
    public Message(String chatId, String content, String role) {
        this.chatId = chatId;
        this.content = content;
        this.role = role;
    }
} 