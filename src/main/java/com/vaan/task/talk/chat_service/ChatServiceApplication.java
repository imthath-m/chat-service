package com.vaan.task.talk.chat_service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
		// this ensures there is only one chat client instance and there is no tool duplicated issue
		return builder
				.defaultTools(toolCallbackProvider)
				.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.build();
	}
}
