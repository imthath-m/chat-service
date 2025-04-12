package com.vaan.task.talk.chat_service.detail;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class LLMService {

//    @Autowired
    private ChatClient.Builder chatClientBuilder;

//    @Autowired
    private ToolCallbackProvider toolCallbackProvider;

    public LLMService(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClientBuilder = chatClientBuilder;
        this.toolCallbackProvider = toolCallbackProvider;
    }

    public String getAnswer(String question) {
        // Create a chat client
        ChatClient chatClient = chatClientBuilder
                .defaultTools(toolCallbackProvider)
                .build();

        // Return the response
        return chatClient.prompt(question).call().content();
    }
}
