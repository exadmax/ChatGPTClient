package com.exadmax.chatgpt.service.openai.service;

import com.exadmax.chatgpt.dto.ChatGPTRequest;
import com.exadmax.chatgpt.dto.ChatGPTResponse;
import com.exadmax.chatgpt.dto.ChatRequest;
import com.exadmax.chatgpt.dto.Message;
import com.exadmax.chatgpt.dto.TranscriptionRequest;
import com.exadmax.chatgpt.dto.WhisperTranscriptionRequest;
import com.exadmax.chatgpt.dto.WhisperTranscriptionResponse;
import com.exadmax.chatgpt.service.openai.client.AIClient;
import com.exadmax.chatgpt.settings.OpenAIClientConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ChatGPTClientService {

    private final AIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";

    public ChatGPTResponse chat(ChatRequest chatRequest){
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();
        return openAIClient.chat(chatGPTRequest);
    }

    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){
        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(openAIClientConfig.getAudioModel())
                .file(transcriptionRequest.getFile())
                .build();
        return openAIClient.createTranscription(whisperTranscriptionRequest);
    }
}
