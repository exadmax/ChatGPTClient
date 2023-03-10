package com.exadmax.chatgpt.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exadmax.chatgpt.dto.ChatGPTResponse;
import com.exadmax.chatgpt.dto.ChatRequest;
import com.exadmax.chatgpt.dto.TranscriptionRequest;
import com.exadmax.chatgpt.dto.WhisperTranscriptionResponse;
import com.exadmax.chatgpt.service.openai.service.ChatGPTClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class OpenAIClientController {

    private final ChatGPTClientService service;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse chat(@RequestBody ChatRequest chatRequest){
        return service.chat(chatRequest);
    }

    @PostMapping(value = "/transcription", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhisperTranscriptionResponse createTranscription(@ModelAttribute TranscriptionRequest transcriptionRequest){
        return service.createTranscription(transcriptionRequest);
    }
}
