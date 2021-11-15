package stack.service.watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import stack.service.watermark.model.WatermarkFont;
import stack.service.watermark.model.WatermarkImageMeta;
import sun.font.FontDesignMetrics;


public class Watermark {

    public static final String PNG = "png";
    public final static Color DEFAULT_COLOR = Color.lightGray;
    public final static int DEFAULT_SIZE = 18;
    public final static String DEFAULT_FONT_NAME = "Default";
    public final static int DEFAULT_RADIANS = -30;
    public final static int DEFAULT_RESIZE_HEIGHT = 150;
    public final static int DEFAULT_RESIZE_WIDTH = 150;
    public final static float DEFAULT_ALPHA = 1f;

    public static BufferedImage createWatermark(WatermarkImageMeta meta) {
        BufferedImage watermark = null;
        setBase(meta);
        watermark = drawWatermark(meta);
        if (!meta.isSingle()) {
            watermark = flatWatermarks(meta, watermark);
        }
        watermark = drawMargin(meta, watermark);

        return watermark;
    }

    private static void setBase(WatermarkImageMeta meta) {
        Font font = meta.getWatermarkFont().getFont();
        FontRenderContext fontRenderContext = new FontRenderContext(new AffineTransform(), false, false);
        Rectangle2D rectangle2D = font.getStringBounds(meta.getText(), fontRenderContext);
        int fontHeight = (int) Math.round(rectangle2D.getHeight());
        int fontWidth = (int) Math.round(rectangle2D.getWidth());

        double rotateWidth = fontWidth;
        double diagonal = getDiagonal(fontWidth, fontHeight);
        double rotateHeight = getRotateHeight(diagonal, fontHeight, meta.getWatermarkFont().getRadians());
        double offsetWidth = (fontWidth - rotateWidth) / 2;
        double offsetHeight = (rotateHeight - fontHeight) / 2;
        meta.getWatermarkFont().setWidth((int) rotateWidth);
        meta.getWatermarkFont().setHeight((int) rotateHeight);
        meta.getWatermarkFont().setOffsetWidth(offsetWidth);
        meta.getWatermarkFont().setOffsetHeight(offsetHeight);
        meta.setGap(rotateWidth, rotateHeight);
        meta.setCanva((int) rotateWidth, (int) rotateHeight);
    }

    private static BufferedImage drawWatermark(WatermarkImageMeta meta) {
        WatermarkFont watermarkFont = meta.getWatermarkFont();
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(watermarkFont.getFont());

        BufferedImage watermark = new BufferedImage(
                (int) watermarkFont.getWidth(), (int) watermarkFont.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = (Graphics2D) watermark.getGraphics();
        watermark = graphics.getDeviceConfiguration().createCompatibleImage(
                (int) watermarkFont.getWidth(), (int) watermarkFont.getHeight(), Transparency.TRANSLUCENT);
        graphics = watermark.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, watermarkFont.getTransparency()));
        graphics.setColor(meta.getWatermarkFont().getColor());
        graphics.setFont(watermarkFont.getFont());
        graphics.rotate(Math.toRadians(
                meta.getWatermarkFont().getRadians()), watermarkFont.getWidth() / 2, watermarkFont.getHeight() / 2);
        graphics.drawString(meta.getText(),
                (float) watermarkFont.getOffsetWidth(), (float) watermarkFont.getOffsetHeight() + metrics.getAscent());
        graphics.dispose();

        return watermark;
    }

    private static BufferedImage flatWatermarks(WatermarkImageMeta meta, BufferedImage watermark) {
        int width = (int) (meta.getCanvaWidth() - meta.getMarginLR() * 2);
        int height = (int) (meta.getCanvaHeight() - meta.getMarginTB() * 2);
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = (Graphics2D) canvas.getGraphics();
        canvas = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics2D = canvas.createGraphics();

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Watermark.DEFAULT_ALPHA));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (double i = 0; i < meta.getCanvaWidth(); i += (watermark.getWidth() + meta.getGap()[1])) {
            for (double j = 0; j < meta.getCanvaHeight();
                    j += (watermark.getHeight() + meta.getGap()[0])) {
                graphics2D.drawImage(watermark, (int) i, (int) j, null);
            }
        }
        graphics2D.dispose();
        return canvas;
    }

    /**
     * excel 画 右侧和下方的 间隔
     * gap[30,60] 下方 30 右侧60
     */
    public static BufferedImage drawGap(WatermarkImageMeta meta, BufferedImage watermark) {
        WatermarkFont watermarkFont = meta.getWatermarkFont();
        int width = (int) (watermarkFont.getWidth() + meta.getGap()[1]);
        int height = (int) (watermarkFont.getHeight() + meta.getGap()[0]);
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = (Graphics2D) canvas.getGraphics();
        canvas = graphics2D.getDeviceConfiguration()
                .createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics2D = canvas.createGraphics();
        graphics2D.drawImage(watermark, 0, 0, null);
        graphics2D.dispose();
        return canvas;
    }

    private static BufferedImage drawMargin(WatermarkImageMeta meta, BufferedImage watermark) {
        if (!meta.isSingle() && !checkMargin(meta.getMargin())) {
            return watermark;
        }
        int x = (int) meta.getMarginLR();
        int y = (int) meta.getMarginTB();
        WatermarkFont watermarkFont = meta.getWatermarkFont();

        if (meta.isSingle()) {
            switch (meta.getLocationEnum()) {
                case TOP_LEFT:
                    break;
                case TOP_RIGHT:
                    x = (int) (meta.getCanvaWidth() - watermarkFont.getWidth() - x);
                    break;
                case BOTTOM_LEFT:
                    y = (int) (meta.getCanvaHeight() - watermarkFont.getHeight() - y);
                    break;
                case BOTTOM_RIGHT:
                    x = (int) (meta.getCanvaWidth() - watermarkFont.getWidth() - x);
                    y = (int) (meta.getCanvaHeight() - watermarkFont.getHeight() - y);
                    break;
                case CENTER:
                    x = (int) (meta.getCanvaWidth() / 2 - watermarkFont.getWidth() / 2);
                    y = (int) (meta.getCanvaHeight() / 2 - watermarkFont.getHeight() / 2);
                    break;
            }
        }

        BufferedImage canvas = new BufferedImage(
                meta.getCanvaWidth(), meta.getCanvaHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = (Graphics2D) canvas.getGraphics();
        canvas = graphics2D.getDeviceConfiguration()
                .createCompatibleImage(canvas.getWidth(), canvas.getHeight(), Transparency.TRANSLUCENT);
        graphics2D = canvas.createGraphics();
        graphics2D.drawImage(watermark, x, y, null);
        graphics2D.dispose();
        return canvas;
    }

    @Deprecated
    public static BufferedImage createImage1(WatermarkImageMeta meta) throws IOException {
        Font font = new Font(meta.getWatermarkFont().getName(),
                meta.getWatermarkFont().getStyle(), meta.getWatermarkFont().getSize());
        FontRenderContext fontRenderContext = new FontRenderContext(new AffineTransform(), false, false);
        Rectangle2D rectangle2D = font.getStringBounds(meta.getText(), fontRenderContext);
        int fontHeight = (int) Math.round(rectangle2D.getHeight());
        int fontWidth = (int) Math.round(rectangle2D.getWidth());
        double diagonal = getDiagonal(fontWidth, fontHeight);
        double rotateHeight = getRotateHeight(diagonal, fontHeight, meta.getWatermarkFont().getRadians());

        BufferedImage image = new BufferedImage(meta.getCanvaWidth(),
                meta.getCanvaHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        image = graphics.getDeviceConfiguration()
                .createCompatibleImage(meta.getCanvaWidth(), meta.getCanvaHeight(), Transparency.TRANSLUCENT);
        graphics = image.createGraphics();
        graphics.setColor(Watermark.DEFAULT_COLOR);
        AffineTransform affineTransform = graphics.getTransform();
        affineTransform.rotate(Math.toRadians(meta.getWatermarkFont().getRadians()), 0, 0);
        graphics.setFont(font.deriveFont(affineTransform));

        for (float i = (float) meta.getMarginLR(); i < meta.getCanvaWidth(); i += fontWidth) {
            for (float j = (float) meta.getMarginTB(); j < meta.getCanvaHeight(); j += rotateHeight) {
                graphics.drawString(meta.getText(), i, j);
            }
        }
        graphics.dispose();
        ImageIO.write(image, "png", new File(""));

        return image;
    }

    private static boolean checkMargin(double[] margin) {
        for (double v : margin) {
            if (v > 0) {
                return true;
            }
        }
        return false;
    }

    public static double getRotateHeight(double diagonal, int height, int radians) {
        double cPow = Math.pow(height, 2);
        double aPow = Math.pow(diagonal / 2, 2);
        double sinAlfa = Math.abs(Math.sin(Math.toRadians(radians)));
        double cosBeta = 1 - cPow / (2 * aPow);
        double cosAlfa = Math.abs(Math.cos(Math.toRadians(radians)));
        double sinBeta = Math.sqrt(1 - Math.pow(cosBeta, 2));

        return radians == height ? 0 : (diagonal / 2) * (sinAlfa * cosBeta + cosAlfa * sinBeta) * 2;
    }

    public static double getDiagonal(int width, int height) {
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }
}


