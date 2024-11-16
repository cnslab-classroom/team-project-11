package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import dto.HuggingfaceRequestDto;

public class PromptToImageManager {
    private static int OUTPUT_IMAGE_NUM = 0;
    private static final String userHome = System.getProperty("user.home");
    private static final String downloadDir = userHome + "\\Downloads";
    private final OkHttpClient client;

    public PromptToImageManager() {
        // OkHttpClient timeout 설정 - 이미지 생성 응답 2분정도 소요 될 것
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(150, TimeUnit.SECONDS)
                .build();
    }

    public void downloadImage(String prompt) throws IOException { //huggingface 통신 및 이미지 생성 메서드 하나로 통합
        OUTPUT_IMAGE_NUM++;
        System.out.println("이미지 생성 중 : 2분내로 완료됩니다");
        HuggingfaceRequestDto huggingfaceRequestDto = new HuggingfaceRequestDto(prompt);
        Request request = huggingfaceRequestDto.generateJsonRequest();
        String outputPath = downloadDir + "/output_image" + OUTPUT_IMAGE_NUM + ".png";

        try (Response response = client.newCall(request).execute(); //여러번 호출을 요구할 경우 1회 이후부터 사용 불가 문제 해결위한 try-with-resources 사용
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            if (!response.isSuccessful()) {
                throw new IOException("Image download failed: " + response);
            }
            fos.write(response.body().bytes());  //이미지 바이트 배열 파일로 저장 확인함
            System.out.println("다운로드 완료: " + outputPath);
        }
    }
}
