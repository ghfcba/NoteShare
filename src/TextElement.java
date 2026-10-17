public class TextElement implements HtmlNode {

    private final String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
