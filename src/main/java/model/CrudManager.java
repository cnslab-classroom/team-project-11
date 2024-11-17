package model;

import dto.BoardDto;
import java.util.Vector;

public class CrudManager {
    private final Vector<BoardDto> boardDb;

    public CrudManager() {
        this.boardDb = new Vector<>();
    }

    // 게시글 생성
    public void requestCreate(BoardDto post) {
        boardDb.add(post);
        System.out.println("게시글 생성 완료: " + post.getTitle());
    }

    // 특정 인덱스 게시글 읽기
    public BoardDto requestRead(int index) {
        if (index >= 0 && index < boardDb.size()) {
            return boardDb.get(index);
        }
        throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
    }

    // 게시글 수정
    public void requestUpdate(int index, BoardDto post) {
        if (index >= 0 && index < boardDb.size()) {
            boardDb.set(index, post);
            System.out.println("게시글 수정 완료 (인덱스: " + index + ")");
        } else {
            throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
        }
    }

    // 게시글 삭제
    public void requestDelete(int index) {
        if (index >= 0 && index < boardDb.size()) {
            BoardDto removedPost = boardDb.remove(index);
            System.out.println("게시글 삭제 완료: " + removedPost.getTitle());
        } else {
            throw new IndexOutOfBoundsException("잘못된 인덱스입니다: " + index);
        }
    }

    // 전체 게시글 반환
    public Vector<BoardDto> requestAll() {
        return boardDb;
    }
}
