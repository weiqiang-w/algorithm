package stack.service.watermark;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;


import lombok.SneakyThrows;
import stack.service.watermark.model.WatermarkImageMeta;

public class PdfWatermark {

    @Deprecated
    public static OutputStream render(String text, FileInputStream pdfFileInputStream) throws IOException {
        float fontSize = Watermark.DEFAULT_SIZE;
        float fontWidth = text.length() * fontSize * 6 / 10;
        float startPoint = -100f;
        PDFont font = new PDType1Font(FontName.COURIER);
        PDExtendedGraphicsState egs = new PDExtendedGraphicsState();
        egs.setNonStrokingAlphaConstant(Watermark.DEFAULT_ALPHA);
        egs.setAlphaSourceFlag(true);

        //打开pdf文件
        PDDocument doc = Loader.loadPDF(pdfFileInputStream);
        doc.setAllSecurityToBeRemoved(true);

        for (PDPage page : doc.getPages()) {
            PDRectangle mediaBox = page.getMediaBox();
            float pageHeight = mediaBox.getHeight();
            float pageWidth = mediaBox.getWidth();

            PDPageContentStream pcs = new PDPageContentStream(doc, page,
                    PDPageContentStream.AppendMode.APPEND, true, true);
            pcs.setGraphicsStateParameters(egs);
            pcs.setNonStrokingColor(Watermark.DEFAULT_COLOR);
            pcs.beginText();
            pcs.setFont(font, fontSize);
            for (float i = startPoint; i < pageWidth; i += fontWidth) {
                for (float j = startPoint; j < pageHeight; j += fontWidth) {
                    pcs.setTextMatrix(
                            Matrix.getRotateInstance(Math.toRadians(Math.abs(Watermark.DEFAULT_RADIANS)), i, j));
                    pcs.showText(text);
                }
            }

            pcs.endText();
            pcs.restoreGraphicsState();
            pcs.close();
        }

        OutputStream bos = new ByteArrayOutputStream();
        doc.save(bos);
        return bos;
    }

    /**
     * 图片绘制
     */
    @SneakyThrows
    public static Resource render(WatermarkImageMeta meta) {
        //打开pdf文件
        PDDocument doc = Loader.loadPDF(meta.getOriginFile());
        doc.setAllSecurityToBeRemoved(true);

        PDExtendedGraphicsState egs = new PDExtendedGraphicsState();
        egs.setNonStrokingAlphaConstant(Watermark.DEFAULT_ALPHA);
        egs.setAlphaSourceFlag(true);

        PDPage page1 = doc.getPages().get(0);
        meta.setCanvaWidth((int) page1.getMediaBox().getWidth());
        meta.setCanvaHeight((int) page1.getMediaBox().getHeight());
        BufferedImage watermark = Watermark.createWatermark(meta);

        for (PDPage page : doc.getPages()) {
            PDPageContentStream pcs = new PDPageContentStream(doc, page,
                    PDPageContentStream.AppendMode.APPEND, true, true);
            pcs.setGraphicsStateParameters(egs);
            pcs.setNonStrokingColor(Watermark.DEFAULT_COLOR);
            PDRectangle mediaBox = page.getMediaBox();

            PDImageXObject pdImage = PDImageXObject.createFromByteArray(doc, ImageUtils.getByteArray(watermark), null);
            pcs.drawImage(pdImage, (mediaBox.getWidth() - watermark.getWidth()) / 2,
                    (mediaBox.getHeight() - watermark.getHeight()) / 2);
            pcs.close();
        }

        doc.close();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        doc.save(bos);

        return new ByteArrayResource(bos.toByteArray());
    }
}
