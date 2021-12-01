package service.watermark;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import lombok.SneakyThrows;
import service.watermark.model.WatermarkImageMeta;

public class ExcelWatermark {

    //单独绘制
    @SneakyThrows
    public static void render(WatermarkImageMeta watermarkImageMeta) {
        BufferedImage image = Watermark.createWatermark(watermarkImageMeta);
        image = Watermark.drawGap(watermarkImageMeta, image);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, Watermark.PNG, os);

        ByteArrayInputStream is = new ByteArrayInputStream(watermarkImageMeta.getOriginFile());
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        POIXMLDocumentPart poixmlDocumentPart = workbook.getAllPictures()
                .get(workbook.addPicture(os.toByteArray(), Workbook.PICTURE_TYPE_PNG));
        PackagePartName ppn = poixmlDocumentPart.getPackagePart().getPartName();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            //add relation from sheet to the picture data
            PackageRelationship pr = sheet.getPackagePart()
                    .addRelationship(ppn, TargetMode.INTERNAL, XSSFRelation.IMAGES.getRelation(), null);
            //set background picture to sheet
            sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
        }
        File fileOut = new File("");
        workbook.write(new FileOutputStream(fileOut));
    }
}
