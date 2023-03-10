package com.exadmax.chatgpt.service.openai.client;

import com.exadmax.chatgpt.dto.ChatGPTRequest;
import com.exadmax.chatgpt.dto.ChatGPTResponse;
import com.exadmax.chatgpt.dto.WhisperTranscriptionRequest;
import com.exadmax.chatgpt.dto.WhisperTranscriptionResponse;
import com.exadmax.chatgpt.settings.OpenAIClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "openai-service",
        url = "https://api.openai.com/v1",
        configuration = OpenAIClientConfig.class
)
public interface AIClient {

    @PostMapping(value = "${openai-service.urls.chat-url}", headers = {"Content-Type=application/json"})
    ChatGPTResponse chat(@RequestBody ChatGPTRequest chatGPTRequest);

    @PostMapping(value = "${openai-service.urls.create-transcription-url}", headers = {"Content-Type=multipart/form-data"})
    WhisperTranscriptionResponse createTranscription(@ModelAttribute WhisperTranscriptionRequest whisperTranscriptionRequest);
}
