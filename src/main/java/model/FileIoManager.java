package model;

import dto.BoardDto;

import java.io.*;
import java.util.Vector;

public class FileIoManager {

    private final String filePath;

    public FileIoManager(String filePath) {
        this.filePath = filePath;
    }

    // 데이터베이스를 파일에 저장
    public void saveDbToFile(Vector<BoardDto> db) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(db);
            System.out.println("데이터베이스가 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("데이터베이스 저장 중 오류 발생: " + e.getMessage());
        }
    }

    // 파일에서 데이터베이스를 로드
    public Vector<BoardDto> loadDbFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("저장된 데이터베이스가 없습니다. 새로운 데이터베이스를 생성합니다.");
            return new Vector<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            Vector<BoardDto> loadedDb = (Vector<BoardDto>) ois.readObject();
            System.out.println("데이터베이스가 성공적으로 로드되었습니다.");
            return loadedDb;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("데이터베이스 로드 중 오류 발생: " + e.getMessage());
            return new Vector<>();
        }
    }
}
