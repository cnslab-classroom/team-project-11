package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class TextToImagetextManager {

    // Hugging Face Inference API URL
    private static final String API_URL = "https://api-inference.huggingface.co/models/Qwen/Qwen2.5-Coder-32B-Instruct";
    private static final String API_KEY = "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"; // API Key 입력

    // OkHttpClient 객체 생성
    static OkHttpClient client = new OkHttpClient();

    /**
     * 일기를 이미지 생성을 위한 영어 프롬프트로 변환하는 메서드
     *
     * @param diaryText 입력된 영어 일기
     * @return 변환된 이미지 생성 프롬프트
     */
    public static String convertDiaryToPrompt(String diaryText) {
        // 요청 바디 생성
        JsonObject json = new JsonObject();
        //String diaryTextt ="Focusing on visual elements such as settings, characters, colors, and moods, we transform the "+"\""+diaryText+"\""+"into a detailed and descriptive prompt for image-generating AI.";
        json.addProperty("inputs", "Please convert "+"\""+diaryText +"\""+" into a description suitable for an image generation AI.");
    

        // 요청 바디 설정
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));

        // API 요청 생성
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        // API 호출 및 응답 처리
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API call failed: " + response.code() + " " + response.message());
            }

            // 응답 데이터 처리
            String responseBody = response.body().string();
            return parseResponse(responseBody);

        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    /**
     * API 응답 데이터를 파싱하여 생성된 텍스트 추출
     *
     * @param responseBody API 응답 데이터
     * @return 생성된 프롬프트 텍스트
     */
    private static String parseResponse(String responseBody) {
        // 응답 데이터를 JsonArray로 파싱
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        
        // 첫 번째 JsonObject 추출
        JsonElement firstElement = jsonArray.get(0);
        if (firstElement.isJsonObject()) {
            JsonObject jsonObject = firstElement.getAsJsonObject();
            if (jsonObject.has("generated_text")) {
                return jsonObject.get("generated_text").getAsString();
            }
        }
        return "Unexpected response format: " + responseBody;
    }
}