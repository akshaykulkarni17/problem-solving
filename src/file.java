import java.io.File;

public class file {

    public static void main(String[] args) {
        File directory = new File("C:\\Users\\91942\\Downloads");
        String[] f = directory.list();
        String s= "";
        for (String str:
             f) {
            if(str.contains("kubernetes")) s=str ;
        }
        System.out.println(s);
    }
}
