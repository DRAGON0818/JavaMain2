package Chapter02IO.ZIP;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * @version 1.41 2012-06-01
 * @author Cay Horstmann
 */
public class ZipTest
{
    public static void main(String[] args) throws IOException
    {
        String zipname = args[0];
        System.out.println(zipname);
        showContents(zipname);
       /* System.out.println("---");
        showContents2(zipname);*/
    }

    public static void showContents(String zipname) throws IOException
    {
        // Here, we use the classic zip API
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname),Charset.forName("GBK")))
        {
            ZipEntry entry;

            while ((entry = zin.getNextEntry()) != null)
            {
                System.out.println(entry.getName());
                Path path = Paths.get("C:\\Users\\donglxa\\Desktop"+File.separator+entry.getName());
                //OutputStreamWriter 是 两个IO机制（Reader/Writer和InputStream/OutputStream） 转换的桥梁
                PrintWriter  buff = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path.toString()),Charset.forName("GBK")),true);
                Scanner in = new Scanner(zin, "GBK");
                while (in.hasNextLine())
                {
                    buff.write(in.nextLine());
                }
                // DO NOT CLOSE in
                buff.flush();
                buff.close();
                zin.closeEntry();
            }
        }
    }

   /* public static void showContents2(String zipname) throws IOException
    {
        // Here, we make a Java SE 7 file system
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
        Files.walkFileTree(fs.getPath("\\"), new SimpleFileVisitor<Path>()
        {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException
            {
                System.out.println(path);
                for (String line : Files.readAllLines(path, Charset.forName("GBK")))
                    System.out.println("   " + line);
                return FileVisitResult.CONTINUE;
            }
        });
    }*/
}