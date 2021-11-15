package stack.service.watermark;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import stack.service.watermark.model.FileTypeEnum;


public class FileUtils {

    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    static {
        FILE_TYPE_MAP.put(FileTypeEnum.JPG.getName(), FileTypeEnum.JPG.getCode());
        FILE_TYPE_MAP.put(FileTypeEnum.PNG.getName(), FileTypeEnum.PNG.getCode());
        FILE_TYPE_MAP.put(FileTypeEnum.GIF.getName(), FileTypeEnum.GIF.getCode());
        FILE_TYPE_MAP.put(FileTypeEnum.DOC.getName(), FileTypeEnum.DOC.getCode());
        FILE_TYPE_MAP.put(FileTypeEnum.DOCX.getName(), FileTypeEnum.DOCX.getCode());
        FILE_TYPE_MAP.put(FileTypeEnum.PDF.getName(), FileTypeEnum.PDF.getCode());
    }

    public static String getFileType(byte[] bype) {
        //FileMagic.valueOf(bype); ~哈哈 后来发现有这个更快的方法 。。。 wtf
        String filetypeHex = String.valueOf(getFileHexString(bype));
        for (Entry<String, String> entry : FILE_TYPE_MAP.entrySet()) {
            String fileTypeHexValue = entry.getValue();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                try {
                    XSSFWorkbook sheets = new XSSFWorkbook(new ByteArrayInputStream(bype));
                    sheets.close();
                } catch (Exception e) {
                    if (StringUtils.equals(filetypeHex, FileTypeEnum.DOC.getName())) {
                        return FileTypeEnum.XLS.getName();
                    } else {
                        return FileTypeEnum.XLSX.getName();
                    }
                }
                return entry.getKey();
            }
        }
        return null;
    }

    private static String getFileHexString(byte[] bype) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bype == null || bype.length <= 0) {
            return null;
        }
        for (int i = 0; i < bype.length; i++) {
            int v = bype[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
