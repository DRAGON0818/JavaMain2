package Chapter12Native.printf;

public class Printf1 {
    static{
        System.loadLibrary("Printf1");
    }

    public static native int print(int width, int precision, double x);
}
