package service.watermark.model;


import lombok.Data;

@Data
public class WatermarkImageMeta {
    /**
     * 画布长
     */
    int canvaWidth;
    /**
     * 画布高
     */
    int canvaHeight;

    byte[] originFile;

    byte[] watermarkFile;

    /**
     * 水印长
     */
    private int width;
    /**
     * 水印高
     */
    private int height;
    /**
     * 水印图片透明度
     */
    private float transparency;
    /**
     * 水印文字
     */
    private String text;
    /**
     * 水印字体信息
     */
    private WatermarkFont watermarkFont;
    /**
     * true 一个水印
     * 或平铺
     */
    private boolean isSingle = false;
    /**
     * isSingle优先级高
     * 水印方位
     */
    private LocationEnum locationEnum = LocationEnum.CENTER;
    /**
     * 水印行数
     */
    private int lineSize = 0;
    /**
     * 水印列数
     */
    private int columnSize = 0;
    /**
     * 同css
     * [30,10]， 上下30 左右10 距离外边框间距
     * [30,10,10,30] 上下右左
     */
    private double[] margin = {0, 0, 0, 0};
    /**
     * [30,10] 上下，左右间隔
     */
    private double[] gap = {0, 0};

    public WatermarkImageMeta() {
        this.watermarkFont = new WatermarkFont();
    }

    public void setGap(double rotateWidth, double rotateHeight) {
        //process priority: num > gap (如果行数列数指定则不会再看gap，但是如果行数列数*长度 大于画布的话，则按照gap去平铺)
        if (columnSize != 0 && columnSize * rotateWidth <= canvaWidth) {
            this.gap[1] = (canvaWidth - columnSize * rotateWidth) / (columnSize - 1);
        }

        if (lineSize != 0 && lineSize * rotateHeight < canvaHeight) {
            this.gap[0] = (canvaHeight - lineSize * rotateHeight) / (lineSize - 1);
        }
    }

    public void setCanva(int canvaWidth, int canvaHeight) {
        if (this.canvaWidth == 0) {
            this.canvaWidth = canvaWidth;
        }
        if (this.canvaHeight == 0) {
            this.canvaHeight = canvaHeight;
        }
    }

    public double getMarginLR() {
        return margin.length == 2 ? margin[1]
                                  : (margin[1] + margin[3]) / 2;
    }

    public double getMarginTB() {
        return margin.length == 2 ? margin[0]
                                  : (margin[0] + margin[2]) / 2;
    }
}