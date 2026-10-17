import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        PageData pageData = new PageData();
        HtmlGenerator generator = new HtmlGenerator();

        // Генерируем всю страницу NoteShare
        HtmlElement body = generator.generatePage(pageData);

        String html = """
                <!DOCTYPE html>
                <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>NoteShare - обмен нотами</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                """ + body.render() + """
                
                </html>
                """;

        File output = new File("web/output.html");

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(output),
                        StandardCharsets.UTF_8
                ))) {

            writer.write(html);

            System.out.println("HTML успешно создан!");
            System.out.println("Файл: " + output.getAbsolutePath());

        } catch (IOException e) {
            System.err.println(
                    "Ошибка записи файла: " + e.getMessage()
            );
        }
    }
}
