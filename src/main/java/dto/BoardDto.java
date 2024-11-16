package dto;
//게시글의 데이터 구조 정의하는 객체
public class BoardDto {
    private int index;
    private String title;
    private String content;

    // 생성자
    public BoardDto(int index, String title, String content) {
        this.index = index;
        this.title = title;
        this.content = content;
    }

    // Getter 메서드
    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setter 메서드
    public void setIndex(int index) {
        this.index = index;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}