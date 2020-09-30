import java.io.*;
import java.nio.charset.StandardCharsets;

public class test {
   /* public static void main(String[] args) {
            String[] charsetNames={
                    "UTF-8",
                    "UTF-16",
                    "UTF-16BE",
                    "UTF-16LE",
                    "UTF-32",
                    "UTF-32BE",
                    "UTF-32LE",
                    "UNICODE",
                    "GBK",
                    "GB2312",
                    "GB18030",
                    "ISO8859-1",
                    "BIG5",
                    "ASCII"
            };


            for(int i=0;i<charsetNames.length;i++){
                printByteLength(charsetNames[i]);
            }

        try {
            InputStream in = new FileInputStream("A.txt");
            in.read();


            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("A.txt")), true);
            boolean b = out.checkError();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


*/

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("A.txt");
        byte[] bytes = new byte[20];
        int read = fileInputStream.read(bytes);
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }

        /**
         * String类的不带参数的getBytes()方法会以程序所运行平台的默认编码方式为准来进行转换，
         * 在不同环境下可能会有不同的结果，因此建议使用指定编码方式的getBytes(String charsetName)方法。
         */
        public static void printByteLength(String charsetName){
            String en="a";    //一个英文字符
            String zh="啊";    //一个中文字符
           // InputStream in = System.in;
            try {
                System.out.println(charsetName+"编码英文字符所占字节数:"+en.getBytes(charsetName).length);
                System.out.println(charsetName+"编码中文字符所占字节数:"+zh.getBytes(charsetName).length);
                System.out.println();
            } catch (UnsupportedEncodingException e) {
                System.out.println("非法编码格式！");
            }
        }
}
