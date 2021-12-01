package service.watermark;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;


import lombok.SneakyThrows;
import service.watermark.model.WatermarkImageMeta;

public class WordWatermark {

    /**
     * 参考
     * https://startbigthinksmall.wordpress.com/2010/01/04/points-inches-and-emus-measuring-units-in-office-open-xml/
     * 1px = 0.04cm
     * 1cm = 25px
     */
    @SneakyThrows
    public static byte[] render(WatermarkImageMeta watermarkImageMeta) {
        String fileName = "service/watermark";
        OPCPackage opcPackage = OPCPackage.open(new ByteArrayInputStream(watermarkImageMeta.getOriginFile()));
        XWPFDocument doc = new XWPFDocument(opcPackage);
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        CTPageSz pageSize = doc.getDocument().getBody().getSectPr().getPgSz();
        double width_cm = Math.round(pageSize.getW().doubleValue() / 20d / 72d * 2.54d * 100d) / 100d * 29;//*25 ->29
        double height_cm = Math.round(pageSize.getH().doubleValue() / 20d / 72d * 2.54d * 100d) / 100d * 29;//*25 ->29
        ByteArrayInputStream fileInputStream =
                new ByteArrayInputStream(ImageUtils.getByteArray(Watermark.createWatermark(watermarkImageMeta)));
        XWPFPicture picture = run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_PNG, fileName,
                Units.toEMU(width_cm), Units.toEMU(height_cm));
        fileInputStream.close();
        String blipId = header.getRelationId(picture.getPictureData());
        picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipId);

        CTDrawing drawing = run.getCTR().getDrawingArray(0);
        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();
        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, fileName,
                Units.toEMU(width_cm), Units.toEMU(height_cm),
                Units.toEMU(-95), Units.toEMU(-45), true);
        drawing.setAnchorArray(new CTAnchor[] {anchor});//添加浮动属性
        drawing.removeInline(0);//删除行内属性
        doc.close();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        doc.write(bos);

        return bos.toByteArray();
    }

    /**
     * @param ctGraphicalObject 图片数据
     * @param fileName 图片描述
     * @param width 宽
     * @param height 高
     * @param leftOffset 水平偏移 left
     * @param topOffset 垂直偏移 top
     * @param behind 文字上方，文字下方
     */
    @SneakyThrows
    private static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
            String fileName, int width, int height,
            int leftOffset, int topOffset, boolean behind) {
        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0)
                        + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">"
                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>"
                        + "<wp:positionV relativeFrom=\"paragraph\">"
                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
                        "</wp:positionV>"
                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
                        + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + fileName
                        + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = CTDrawing.Factory.parse(anchorXML);
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }
}
