package service;

import dto.BoardDto;
import model.CrudManager;

public class BoardService {
    private CrudManager crudManager;

    public BoardService() {
        this.crudManager = new CrudManager();
    }

    // 게시글 추가
    public void addboardDto(BoardDto boardDto) {
        if (boardDto == null) {
            System.out.println("Error! 유효하지 않은 게시글 데이터입니다.");
            return;
        }
        crudManager.requestCreate(boardDto);
        System.out.println("게시글이 추가되었습니다.");
    }

    // 게시글 제목 수정
    public void updatePostTitle(int index, String newTitle) 
    {
        BoardDto post = crudManager.requestRead(index);
        if (post != null) {
            post.setTitle(newTitle);
            crudManager.requestUpdate(post);
            System.out.println("제목이 수정되었습니다.");
        } else {
            System.out.println("Error! 해당 인덱스의 게시글이 없습니다.");
        }
    }

    // 게시글 내용 수정
    public void updatePostContent(int index, String newContent) 
    {
        BoardDto post = crudManager.requestRead(index);
        if (post != null) {
            post.setContent(newContent);
            crudManager.requestUpdate(post);
            System.out.println("내용이 수정되었습니다.");
        } else {
            System.out.println("Error! 해당 인덱스의 게시글이 없습니다.");
        }
    }

    // 게시글 제목과 내용 모두 수정
    public void updatePost(int index, String newTitle, String newContent) 
    {


        BoardDto post = crudManager.requestRead(index);
        if (post != null) {
            post.setTitle(newTitle);
            post.setContent(newContent);
            crudManager.requestUpdate(post);
            System.out.println("제목과 내용이 모두 수정되었습니다.");
        } else {
            System.out.println("Error! 해당 인덱스의 게시글이 없습니다.");
        }
    }

    // 게시글 삭제 요청
    public void deleteboardDto(int index) 
    {
    BoardDto post = crudManager.requestRead(index);
    if (post != null) 
    {
        crudManager.requestDelete(index);
        System.out.println("게시글이 삭제되었습니다.");
    } 
    else 
    {
        System.out.println("Error! 해당 인덱스의 게시글이 없습니다.");
    }
    }

    // 게시글이 존재하는지 확인
    public boolean exists(int index) {
        return crudManager.requestRead(index) != null;
    }



    // 모든 게시글 반환 
    public List<BoardDto> getAllPosts() {
        return crudManager.requestAll();
    }
}
