
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class HtmlElement implements HtmlNode {

    private final String tag;
    private final List<HtmlNode> children;
    private final Map<String, String> attributes;

    public HtmlElement(String tag) {
        this.tag = tag;
        this.children = new ArrayList<>();
        this.attributes = new LinkedHashMap<>();
    }

    public HtmlElement addChild(HtmlNode child) {
        children.add(child);
        return this;
    }

    public HtmlElement setAttribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    @Override
    public String render() {

        StringBuilder html = new StringBuilder();

        html.append("<").append(tag);

        attributes.forEach((name, value) ->
                html.append(" ")
                        .append(name)
                        .append("=\"")
                        .append(escape(value))
                        .append("\"")
        );

        html.append(">");

        String content = children
                .parallelStream()
                .map(HtmlNode::render)
                .collect(Collectors.joining());

        html.append(content);

        html.append("</").append(tag).append(">");

        return html.toString();
    }

    private String escape(String value) {
        return value
                .replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    public List<HtmlNode> getChildren() {
        return List.copyOf(children);
    }

}
