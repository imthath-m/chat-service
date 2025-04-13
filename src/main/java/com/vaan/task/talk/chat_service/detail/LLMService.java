package com.vaan.task.talk.chat_service.detail;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LLMService {

    private final String conversationIdKey = AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

    @Autowired
    private ChatClient chatClient;

    public String continueChat(String userMessage, String chatId) {
        return chatClient
                .prompt(userMessage)
                .advisors(advisor -> advisor.param(conversationIdKey, chatId))
                .call().content();
    }
}
