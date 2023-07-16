import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String apiKey = System.getenv("API_KEY");
        OpenAiService service = new OpenAiService(apiKey);

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage userMessage = new ChatMessage(
                ChatMessageRole.USER.value(),
                "Tell me the weather in Barcelona.");
        messages.add(userMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .temperature(0.6d)
            .messages(messages)
            .maxTokens(256)
            .build();

        ChatMessage responseMessage = service.createChatCompletion(chatCompletionRequest)
                .getChoices().get(0).getMessage();
        System.out.println(responseMessage.getContent());
    }
}
