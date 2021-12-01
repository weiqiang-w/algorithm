package service.watermark.model;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    JPG("FFD8FF", "jpg"),
    PNG("89504E47", "png"),
    GIF("47494638", "gif"),
    XLS("D0CF11E0", "xls"),
    DOC("D0CF11E0", "doc"),
    XLSX("504B0304", "xlsx"),
    DOCX("504B0304", "docx"),
    PDF("255044462D312E", "pdf"),
    ;
    String code;
    String name;
}
