package Chapter02IO.Path;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.stream.Stream;

public class pathTest {
    public static void main(String[] args) throws IOException {
       /* Path path = Paths.get("Path");
        //  p.resolve(q)   如果q是绝对路径，返回q，否则返回 p+q;
        Path path1 = path.resolve(Paths.get("C:\\Chapter02IO"));
        System.out.println(path1.getParent());


        //resolve() 解析指定路径的父路径产生其兄弟路径。
        Path temp = path1.resolveSibling("temp");
        System.out.println(temp);

        //relativize  求得相对路径
        Path relativize = path1.relativize(Paths.get("C:\\Chapter01Stream"));
        System.out.println(relativize);

        //normalize()会取消所有冗余的部件   如/home/x/../fred   会返回/home/fred
        Path normalize = relativize.normalize();
        System.out.println(normalize);

        Path path2 = normalize.toAbsolutePath();
        System.out.println(path2);

        UserPrincipal owner = Files.getOwner(path1.getParent());
        System.out.println(owner);*/

        //通过Path实现文件的迁移
        /*Path source=Paths.get("C:\\Users\\donglxa\\Desktop\\A");
        Path target=Paths.get("C:\\Users\\donglxa\\Desktop\\B");
        Files.walk(source).forEach(p->{
            try {
                Path q = target.resolve(source.relativize(p));
                if (Files.isDirectory(p)) {
                    Files.createDirectory(q);
                }
                else
                    Files.copy(p, q);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });*/

        //通过Files 遍历目标目录下所有的文件   list值遍历改目录，walk还会进入子目录
        /*Stream<Path> list = Files.list(Paths.get("E:\\exe4j"));
        list.forEach(System.out::println);
        System.out.println("---------");
        Stream<Path> walk = Files.walk(Paths.get("E:\\exe4j"));
        walk.forEach(System.out::println);

        DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get("E:\\"));
        paths.forEach(System.out::println);*/

        //walk虽然可以进入子目录，但是不能进行删除。因为在子目录不为空的情况下，不能删除父目录。所以需要通过walkFileTree方法，结合FileVistor<> 来实现删除
       /* Files.walkFileTree(Paths.get("E:\\exe4j"),new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //return super.visitFile(file, attrs);
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                //return super.preVisitDirectory(dir, attrs);
                System.out.println(dir+" @ " +attrs);
                return FileVisitResult.CONTINUE;
            }


            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
               // return super.postVisitDirectory(dir, exc);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
               // return super.visitFileFailed(file, exc);

                return FileVisitResult.SKIP_SUBTREE;
            }
        });*/


        /*
        Files.walkFileTree(Paths.get("C:\\Users\\donglxa\\Desktop\\B"),new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //return super.visitFile(file, attrs);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
            //postVisitDirectory 是在访问完文件之后
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                //return super.postVisitDirectory(dir, exc);
               if(exc!=null) throw exc;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });*/


        //文件系统 FileSystem
      /*  FileSystem fileSystem = FileSystems.newFileSystem(Paths.get("C:\\Users\\donglxa\\Desktop\\Desktop.zip"), null);
        Files.walkFileTree(fileSystem.getPath("\\"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });*/

    }


}
