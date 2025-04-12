package com.vaan.task.talk.chat_service.list;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    private String id;
    private String title;
    private String userId;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;
    
    private boolean isArchived;
    private String lastMessage;

    // Constructor with required fields
    public Chat(String title, String userId) {
        this.title = title;
        this.userId = userId;
        this.isArchived = false;
    }
} 