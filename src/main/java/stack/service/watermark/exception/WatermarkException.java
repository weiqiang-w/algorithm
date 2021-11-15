package stack.service.watermark.exception;

public class WatermarkException extends RuntimeException{
    public WatermarkException() {
    }

    public WatermarkException(String message) {
        super(message);
    }

    public WatermarkException(Throwable cause) {
        super(cause);
    }
}
