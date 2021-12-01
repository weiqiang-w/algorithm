package service.watermark;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


import service.watermark.model.WatermarkImageMeta;


public class ImageWatermark {

    public static BufferedImage render(WatermarkImageMeta meta, InputStream imageIs) throws IOException {
        BufferedImage bi = ImageIO.read(imageIs);
        meta.setCanvaWidth(bi.getWidth());
        meta.setCanvaHeight(bi.getHeight());
        BufferedImage watermark = Watermark.createWatermark(meta);
        return addWatermark2Image(bi, watermark);
    }


    public static BufferedImage render(InputStream watermarkIs, InputStream imageIs) throws IOException {
        return addWatermark2Image(ImageIO.read(imageIs), ImageIO.read(watermarkIs));
    }

    private static BufferedImage addWatermark2Image(BufferedImage image, BufferedImage watermark) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Watermark.DEFAULT_ALPHA);
        graphics2D.setComposite(alphaChannel);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(watermark, (image.getWidth() - watermark.getWidth()) >> 1,
                (image.getHeight() - watermark.getHeight()) >> 1, null);
        graphics2D.dispose();
        return image;
    }

}
