package com.vaan.task.talk.chat_service;

import org.springframework.boot.SpringApplication;

public class TestChatMcpClientApplication {

	public static void main(String[] args) {
		SpringApplication.from(ChatServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
