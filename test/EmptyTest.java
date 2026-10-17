
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTest {

    @Test
    void testEmptyChildren() {

        HtmlElement element = new HtmlElement("div");

        String expected = "<div></div>";

        String actual = element.render();

        assertEquals(expected, actual);
    }
}
