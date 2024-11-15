package model;


import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class TextToPromptManager {
    
    // API 키 및 URL 설정
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api-inference.huggingface.co/models/deepset/roberta-base-squad2";

    static OkHttpClient client = new OkHttpClient();

    /*
     * 입력된 텍스트를 AI 프롬프트로 변환하는 메서드
     *
     * @param inputText 사용자 입력 텍스트
     * @return 변환된 AI 프롬프트
     */
    public static String convertToPrompt(String inputText) {
        JsonObject json = createRequestJson(inputText);

        // HTTP 요청 생성
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        // 요청 실행 및 응답 처리
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 입력된 텍스트를 포함하는 요청 JSON 객체를 생성하는 메서드
     *
     * @param inputText 사용자 입력 텍스트
     * @return 요청 JSON 객체
     */
    public static JsonObject createRequestJson(String inputText) {
        // 요청 JSON 데이터 생성
        JsonObject json = new JsonObject();
        json.addProperty("question", inputText);
        json.addProperty("context", "당신은 AI 프롬프트 변환 전문가입니다. 입력된 텍스트를 이미지 생성 AI에 적합한 프롬프트로 변환하세요.");

        return json;
    }
}