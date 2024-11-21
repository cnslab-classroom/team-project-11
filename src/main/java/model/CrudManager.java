package model;

import dto.BoardDto;

import java.util.NoSuchElementException;
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

    // 게시글 조회 (ID로 조회)
    public BoardDto requestRead(String id) {
        for (BoardDto post : db) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        throw new NoSuchElementException("해당 ID의 게시글이 없습니다.");
    }

    // 게시글 수정
    public void requestUpdate(String id, BoardDto updatedBoardDto) {
        for (int i = 0; i < db.size(); i++) {
            BoardDto post = db.get(i);
            if (post.getId().equals(id)) {
                db.set(i, updatedBoardDto);
                return;
            }
        }
        throw new NoSuchElementException("해당 ID의 게시글이 없습니다.");
    }

    // 게시글 삭제
    public void requestDelete(String id) {
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getId().equals(id)) {
                db.remove(i);
                return;
            }
        }
        throw new NoSuchElementException("해당 ID의 게시글이 없습니다.");
    }

    // 전체 게시글 조회
    public Vector<BoardDto> requestAll() {
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
