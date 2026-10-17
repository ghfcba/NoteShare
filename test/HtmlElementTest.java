import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlElementTest {

    @Test
    void testRenderTag() {

        HtmlElement element = new HtmlElement("h1");

        element.addChild(
                new TextElement("NoteShare")
        );

        String expected = "<h1>NoteShare</h1>";

        assertEquals(expected, element.render());
    }
}
