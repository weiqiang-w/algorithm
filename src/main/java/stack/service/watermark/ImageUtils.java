package stack.service.watermark;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;


public class ImageUtils {

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image scaledInstance = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(scaledInstance, 0, 0, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    public static String getImageType(File file) throws IOException {
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
        while (imageReaders.hasNext()) {
            ImageReader reader = imageReaders.next();
            if (StringUtils.isNotBlank(reader.getFormatName())) {
                return reader.getFormatName().toLowerCase();
            }
        }

        return FilenameUtils.getExtension(file.getName());
    }

    public static byte[] getByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, Watermark.PNG, os);
        return os.toByteArray();
    }
}
