package dto;

import java.io.Serializable;

public class BoardDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;

    // 기본 생성자
    public BoardDto() {}

    // 매개변수 있는 생성자
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter 메서드
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setter 메서드
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 객체 정보를 문자열로 반환
    @Override
    public String toString() {
        return "제목: " + title + "\n내용: " + content;
    }
}
