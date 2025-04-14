package com.vaan.task.talk.chat_service.detail;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LLMService {

    @Getter
    enum ClaudeModel {
        HAIKU_3("claude-3-haiku-20240307"),
        HAIKU_3_5("claude-3-5-haiku-20241022");

        private final String name;

        static final ClaudeModel DEFAULT = HAIKU_3_5;

        static ClaudeModel fromKeyword(String keyword) {
            if (keyword == null || keyword.isEmpty()) {
                return DEFAULT;
            }

            for (ClaudeModel model : values()) {
                if (model.name.contains(keyword)) {
                    return model;
                }
            }
            return DEFAULT;
        }

        ClaudeModel(String modelName) {
            this.name = modelName;
        }
    }

    private final String conversationIdKey = AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

    @Autowired
    private ChatClient chatClient;

    public String continueChat(String userMessage, String chatId, String modelKeyword) {
        String model = ClaudeModel.fromKeyword(modelKeyword).getName();
        ChatClient.CallResponseSpec response = chatClient
                .prompt(userMessage)
                .advisors(advisor -> advisor.param(conversationIdKey, chatId))
                .options(ChatOptions.builder().model(model).build())
                .call();

        logModel(response.chatResponse());

        return response.content();
    }

    private void logModel(ChatResponse response) {
        try {
            String model = response.getMetadata().getModel();
            log.info("Model used for the response: {}", model);
        } catch (Exception e) {
            log.error("Error retrieving model from response: {}", e.getMessage());
        }
    }
}
