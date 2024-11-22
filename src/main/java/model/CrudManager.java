package model;

import dto.BoardDto;

import java.util.Vector;

public class CrudManager {

    private final Vector<BoardDto> db;

    // 생성자: 게시판 데이터베이스 초기화
    public CrudManager() {
        db = new Vector<>();
    }

    // 게시글 생성
    public void requestCreate(BoardDto boardDto) {
        db.add(boardDto);
    }

    // 게시글 조회 (index로 조회)
    public BoardDto requestRead(int index) {
        if (index >= 0 && index < db.size()) {
            return db.get(index);
        } else {
            throw new IndexOutOfBoundsException("유효하지 않은 인덱스입니다.");
        }
    }

    // 게시글 수정
    public void requestUpdate(int index, BoardDto updatedBoardDto) {
        if (index >= 0 && index < db.size()) {
            db.set(index, updatedBoardDto);
        } else {
            throw new IndexOutOfBoundsException("유효하지 않은 인덱스입니다.");
        }
    }

    // 게시글 삭제
    public void requestDelete(int index) {
        if (index >= 0 && index < db.size()) {
            db.remove(index);
        } else {
            throw new IndexOutOfBoundsException("유효하지 않은 인덱스입니다.");
        }
    }

    // 전체 게시글 조회
    public Vector<BoardDto> requestFull() {
        return new Vector<>(db);
    }

    // 데이터베이스 Getter 및 Setter (파일 입출력용)
    public Vector<BoardDto> getDb() {
        return db;
    }

    public void setDb(Vector<BoardDto> dbList) {
        db.clear();
        db.addAll(dbList);
    }
}
