package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName; // String 으로 넘어온 값
    private UploadFile attachFile; // 파일은 하나
    private List<UploadFile> imageFiles; // 이미지는 여러개 가능
}
