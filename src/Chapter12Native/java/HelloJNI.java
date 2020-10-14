package Chapter12Native.java;

public class HelloJNI {
    static {
        //需要指定加载库文件
        //System.setProperty("java.library.path", "./jni");
        // hello.dll (Windows) or libhello.so(Unixes)
        //loadLibrary是为了确保
        System.loadLibrary("sayHello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        new HelloJNI().sayHello();
    }
}
