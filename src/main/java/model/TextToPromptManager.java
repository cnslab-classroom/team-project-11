package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import java.io.IOException;

public class TextToPromptManager {

    private static final String API_URL = "https://api-inference.huggingface.co/models/gpt2";
    private static final String API_KEY = "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";

    static OkHttpClient client = new OkHttpClient();

    public static String convertToPrompt(String inputText) {
        JsonObject json = new JsonObject();
        json.addProperty("inputs","You are an AI prompt conversion expert. Convert the input text into a prompt suitable for an image generation AI. Input text: " + inputText );
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API call failed: " + response.code() + " " + response.message());
            }

            String responseBody = response.body().string();
            return parseResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    private static String parseResponse(String responseBody) {
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        return jsonObject.get("generated_text").getAsString();
    }
}