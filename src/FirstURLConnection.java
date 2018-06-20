import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
This activity consists of making a program with the following functionality:
1) Given a URL, the program prints the file size, URL, the date of the last update of this file, and the type;
2) the program should also check the type of the file type of the URL. If the file is HTML, the program should
print the page title. If the file isn't HTML, the file must be downloaded and stored in a disk file.
Show the teacher an example with 2 types of URL: HTML, and Image.
*/

public class FirstURLConnection {
    public static void main(String[] args) {
        try {
            String search1 = "<title>.*\\d*";
            String search2 = "Edital.*\\d[0-9]";
            String search = search1;

            URL uefs = new URL("http://www.uefs.br");
            URL ufba = new URL("https://ppgci.ufba.br/processo-seletivo");
            URL pic = new URL("https://avatars2.githubusercontent.com/u/7550887?s=460&v=4");
            URL url = uefs;

            URLConnection con = url.openConnection();

            System.out.println(url.toString());
            System.out.println("Size: " + con.getContentLength()+" bytes");
            System.out.println("Last Update: " + new Date(con.getLastModified()));
            System.out.println("Type: " + con.getContentType());

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            Pattern ex = Pattern.compile(search);

            if (con.getContentType().substring(0,9).equalsIgnoreCase("text/html")) {
                while ((line = in.readLine()) != null) {
                    Matcher mat = ex.matcher(line);
                    while (mat.find()) {
                        System.out.println(mat.group());
                    }
                }
            } else {
                String name = "Pic.jpeg";
                InputStream is = url.openStream();
                FileOutputStream fos = new FileOutputStream(name);
                int umByte=0;
                while ((umByte = is.read()) != -1) {
                    fos.write(umByte);
                }
                System.out.println("File created!");
                is.close();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}