package com.vaan.task.talk.chat_mcp_client;

import org.springframework.boot.SpringApplication;

public class TestChatMcpClientApplication {

	public static void main(String[] args) {
		SpringApplication.from(ChatMcpClientApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
