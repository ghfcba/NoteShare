
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReflectionTest {

    @Test
    void testReflection() {

        PageData data = new PageData();
        HtmlGenerator generator = new HtmlGenerator();

        HtmlElement result = generator.generate(data);

        String html = result.render();

        assertTrue(html.contains("Музыка начинается здесь!"));

        assertTrue(html.contains("Популярные ноты"));

        assertTrue(html.contains("Лунная соната"));

        assertTrue(html.contains("class=\"popular-list\""));
    }
}
