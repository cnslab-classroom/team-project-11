package model;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class PromptToImageManagerTest {

    private final PromptToImageManager manager = new PromptToImageManager();

    @Test
    public void testImage1() {
        String[] prompts = {"image of a cat", "image of a dog", "image of a bird"};
        try {
            for(String prompt : prompts) {
                manager.downloadImage(prompt);
            }
        } catch (IOException e) {
            System.out.println("1실패: " + e.getMessage());
        }
    }
}
