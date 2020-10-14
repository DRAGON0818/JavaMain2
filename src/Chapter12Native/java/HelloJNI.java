package Chapter12Native.java;

public class HelloJNI {
    //MAC下用该方法将代码置于共享类库中
    //gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.7.0_72.jdk/Contents/Home/include/ HelloWorldImpl.c -o libhello.jnilib
    static {
        //需要指定加载库文件
        //System.setProperty("java.library.path", "./jni");
        // hello.dll (Windows) or libhello.so(Unixes)
        System.loadLibrary("sayHello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        new HelloJNI().sayHello();
    }
}
