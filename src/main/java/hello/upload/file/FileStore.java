package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    // 파일 이름을 받아서 fullPath 반환
    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    // 여러개 받아서 업로드
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                UploadFile uploadFile = storeFile(multipartFile);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }


    // 파일 받아서 저장하는 메서드(한개)
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName); // 서버에 저장하는 파일명 // 원래 파일이름 -> 저장할 파일이름
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFileName, storeFileName);
    }

    // 파일 뒤에 확장자 붙이기
    private String createStoreFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);
        // qwe-qwe-123-qwe-qqwe.qng
        return uuid + "." + ext;
    }

    // 파일 확장명 뽑아주기
    private String extractExt(String originalFileName) {
        int position = originalFileName.lastIndexOf(".");
        return originalFileName.substring(position + 1);
    }
}
