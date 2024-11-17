package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import okhttp3.*;
import com.google.gson.JsonParser;
import java.io.IOException;

public class TextToPromptManager {

    // GPT-Neo 모델 URL 단순히 영어로 번역
    private static final String API_URL = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-ko-en";
    private static final String API_KEY = "API_KEY"; // API 키

    // OkHttpClient 객체 생성
    static OkHttpClient client = new OkHttpClient();

    // 입력 텍스트를 프롬프트로 변환하는 메서드
    public static String convertToPrompt(String inputText) {
        // 요청 바디 생성
        JsonObject json = new JsonObject();
        json.addProperty("inputs",inputText );

        // 요청 바디 생성
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        
        // API 요청 생성
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        // 응답 처리
        try (Response response = client.newCall(request).execute()) {
            // 응답이 성공적이지 않은 경우 예외 처리
            if (!response.isSuccessful()) {
                throw new IOException("API call failed: " + response.code() + " " + response.message());
            }

            // 응답 바디를 문자열로 변환
            String responseBody = response.body().string();
            // 응답 바디에서 생성된 텍스트 추출
            return parseResponse(responseBody);
        // 예외 처리
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    // 응답 바디에서 생성된 텍스트 추출하는 메서드
    private static String parseResponse(String responseBody) {
        try {
            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            return jsonObject.get("translation_text").getAsString().trim();
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
