package file_stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 转换流
 * InputStreamReader
 *
 * @author Bean
 */
public class InputStreamReader_read {
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream("osw.txt");
        Reader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		/*int d=-1;
		while((d=isr.read())!=-1) {
			System.out.print((char)d);
		}*/

		/*char[] data=new char[100];
		int len=isr.read(data);
		String str=new String(data,0,len);
		System.out.println(str);*/

        StringBuilder sb = new StringBuilder();
        int d = -1;
        while ((d = isr.read()) != -1) {
            sb.append((char) d);
        }
        String str = sb.toString();
        System.out.println(str);
        isr.close();
    }
}
