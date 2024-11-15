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
        json.addProperty("inputs", "당신은 AI 프롬프트 변환 전문가입니다. 입력된 텍스트를 이미지 생성 AI에 적합한 프롬프트로 변환하세요. 입력 텍스트: " + inputText);

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API 호출 실패: " + response.code() + " " + response.message());
            }

            String responseBody = response.body().string();
            return parseResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return "오류 발생: " + e.getMessage();
        }
    }

    private static String parseResponse(String responseBody) {
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        return jsonObject.get("generated_text").getAsString();
    }
}