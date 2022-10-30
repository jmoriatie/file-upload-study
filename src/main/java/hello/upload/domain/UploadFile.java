package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 고객이 업로드한 파일명 -> 중복이 있을 수 있음
    private String storeFileName; // 서버 내부에서 관리하는 파일명 -> 안겹치는 fileName

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
