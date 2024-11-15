import controller.ProjectController;
import model.TextToPromptManager;
public class App {
    public static void main(String[] args) {
        //ProjectController projectController = new ProjectController();
        //projectController.run();
        TextToPromptManager textToPromptManager = new TextToPromptManager();
        //영어일기 내용 입력
        String inputText = "The girl is reading a book under the maple tree.";
        String prompt = textToPromptManager.convertToPrompt(inputText);
        System.out.println("Prompt: " + prompt);
    }
}
