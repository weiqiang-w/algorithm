package stack.service.watermark.model;

import java.awt.Color;
import java.awt.Font;


import lombok.Data;
import stack.service.watermark.Watermark;


@Data
public class WatermarkFont {
    /**
     * //字体 目前是default
     */
    private String name;
    private double width;
    private double height;
    private double offsetWidth;
    private double offsetHeight;
    /**
     * 字号大小
     */
    private int size;
    /**
     * 字体样式默认Font.PLAIN; 粗体（Font.BOLD） 斜体（Font.ITALIC）
     */
    private int style;
    /**
     *  旋转角度
     */
    private int radians;
    /**
     * 字体透明度
     */
    private float transparency;
    /**
     * 字体颜色
     */
    private Color color;
    private Font font;



    public WatermarkFont() {
        this.size = Watermark.DEFAULT_SIZE;
        this.name = Watermark.DEFAULT_FONT_NAME;
        this.style = Font.PLAIN;
        this.color = Watermark.DEFAULT_COLOR;
        this.radians = Watermark.DEFAULT_RADIANS;
        this.transparency = Watermark.DEFAULT_ALPHA;
    }

    public Font getFont() {
        if (font == null) {
            this.font = new Font(name, style, size);

        }
        return this.font;
    }
}
