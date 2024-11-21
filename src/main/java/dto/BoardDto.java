package dto;

import java.io.Serializable;
import java.util.UUID;

public class BoardDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id; // 고유 식별자
    private String title;
    private String content;

    // 기본 생성자
    public BoardDto() {
        this.id = UUID.randomUUID().toString();
    }

    // 매개변수 있는 생성자
    public BoardDto(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
    }

    // Getter 메서드
    public String getId() {
        return id;
    }

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
        return "ID: " + id + "\n제목: " + title + "\n내용: " + content;
    }
}
