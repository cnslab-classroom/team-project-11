package service;

import dto.BoardDto;
import model.CrudManager;
import model.FileIoManager;

import java.util.NoSuchElementException;
import java.util.Vector;

public class BoardService {

    private final CrudManager crudManager;
    private final FileIoManager fileIoManager;

    // 생성자: 서비스 초기화
    public BoardService(String filePath) {
        this.crudManager = new CrudManager();
        this.fileIoManager = new FileIoManager(filePath);
    }

    // 서비스 시작 시 데이터베이스 로드
    public void initialize() {
        Vector<BoardDto> db = fileIoManager.loadDbFromFile();
        crudManager.setDb(db);
    }

    // 서비스 종료 시 데이터베이스 저장
    public void shutdown() {
        fileIoManager.saveDbToFile(crudManager.getDb());
    }

    // 게시글 추가
    public void addBoard(BoardDto boardDto) {
        if (boardDto == null) {
            throw new IllegalArgumentException("유효하지 않은 게시글 데이터입니다.");
        }
        crudManager.requestCreate(boardDto);
    }

    // 게시글 조회
    public BoardDto getPostById(String id) {
        try {
            return crudManager.requestRead(id);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // 게시글 제목 수정
    public void updatePostTitle(String id, String newTitle) {
        try {
            BoardDto post = crudManager.requestRead(id);
            post.setTitle(newTitle);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    // 게시글 내용 수정
    public void updatePostContent(String id, String newContent) {
        try {
            BoardDto post = crudManager.requestRead(id);
            post.setContent(newContent);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    // 게시글 제목과 내용 모두 수정
    public void updatePost(String id, String newTitle, String newContent) {
        try {
            BoardDto post = crudManager.requestRead(id);
            post.setTitle(newTitle);
            post.setContent(newContent);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    // 게시글 삭제 요청
    public void deleteBoard(String id) {
        try {
            crudManager.requestDelete(id);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    // 게시글이 존재하는지 확인
    public boolean exists(String id) {
        try {
            crudManager.requestRead(id);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // 모든 게시글 반환
    public Vector<BoardDto> getAllPosts() {
        return crudManager.requestAll();
    }
}
