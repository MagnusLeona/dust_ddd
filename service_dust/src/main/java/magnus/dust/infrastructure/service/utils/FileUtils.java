package magnus.dust.infrastructure.service.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String readFileAsString(String name) throws IOException {
        // 从本地读取文件
        // 路径： C:\Magnus\articles
        FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        StringBuilder markdown2HtmlBuilder = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null) {
            // parse
            markdown2HtmlBuilder.append(s).append("\n");
        }
        return markdown2HtmlBuilder.toString();
    }

    public static void saveFile(String name, String fileContent) throws IOException {
        // 将文件写入本地
        // 保存为markdown格式
        Files.writeString(Paths.get(name), fileContent);
        // ok
    }
}
