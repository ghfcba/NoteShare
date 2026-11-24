import java.lang.reflect.Field;
import java.util.List;

public class HtmlGenerator {

    public HtmlElement generate(Object data) {
        HtmlElement root = new HtmlElement("div");

        for (Field field : data.getClass().getDeclaredFields()) {
            WebComponent annotation =
                    field.getAnnotation(WebComponent.class);

            if (annotation == null) {
                continue;
            }

            root.addChild(createFieldNode(data, field, annotation));
        }

        return root;
    }

    private HtmlElement createFieldNode(
            Object data, Field field, WebComponent annotation) {

        field.setAccessible(true);

        try {
            Object value = field.get(data);

            HtmlElement element = new HtmlElement(annotation.tag());

            if (!annotation.cssClass().isEmpty()) {
                element.setAttribute("class", annotation.cssClass());
            }

            if (value instanceof List<?> list) {
                for (Object item : list) {
                    if (item instanceof MusicNote note) {
                        element.addChild(createMusicNote(note));
                    }
                }
            } else if (value != null) {
                element.addChild(new TextElement(value.toString()));
            }

            return element;

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public HtmlElement generatePage(PageData data) {

        HtmlElement body = new HtmlElement("body");

        // ШАПКА
        HtmlElement header = new HtmlElement("header");
        HtmlElement menu = new HtmlElement("div")
                .setAttribute("class", "menu-container");

        HtmlElement button = new HtmlElement("button")
                .setAttribute("class", "menu-toggle")
                .setAttribute("type", "button")
                .setAttribute("aria-expanded", "false");

        button.addChild(new HtmlElement("span")
                .setAttribute("class", "menu-label")
                .addChild(new TextElement("МЕНЮ")));

        menu.addChild(button);

        HtmlElement nav = new HtmlElement("nav")
                .setAttribute("class", "drop_menu");

        HtmlElement ul = new HtmlElement("ul");

        addMenuItem(ul, "index.html", "Главная");
        addMenuItem(ul, "#catalog", "Галерея нот");
        addMenuItem(ul, "#composers", "Композиторы");
        addMenuItem(ul, "#about", "О проекте");

        nav.addChild(ul);
        menu.addChild(nav);
        header.addChild(menu);
        body.addChild(header);

        // ЧИТАЕМ АННОТАЦИИ ЧЕРЕЗ REFLECTION
        HtmlElement reflected = generate(data);

        List<HtmlNode> nodes = getChildren(reflected);

        HtmlElement main = new HtmlElement("main");

        HtmlElement hero = new HtmlElement("section")
                .setAttribute("class", "hero")
                .setAttribute("id", "home");

        HtmlElement heroContent = new HtmlElement("div")
                .setAttribute("class", "hero-content");

        heroContent.addChild(nodes.get(0));
        heroContent.addChild(nodes.get(1));

        HtmlElement buttons = new HtmlElement("div")
                .setAttribute("class", "hero-buttons");

        buttons.addChild(new HtmlElement("a")
                .setAttribute("href", "#upload")
                .setAttribute("class", "btn btn-upload")
                .addChild(new TextElement("Делиться нотами")));

        buttons.addChild(new HtmlElement("a")
                .setAttribute("href", "#search")
                .setAttribute("class", "btn btn-search")
                .addChild(new TextElement("Искать ноты")));

        heroContent.addChild(buttons);
        hero.addChild(heroContent);
        main.addChild(hero);

        // ПОПУЛЯРНЫЕ НОТЫ
        HtmlElement popular = new HtmlElement("section")
                .setAttribute("class", "popular-notes")
                .setAttribute("id", "catalog");

        popular.addChild(nodes.get(2));
        popular.addChild(nodes.get(3));
        popular.addChild(nodes.get(4));

        main.addChild(popular);
        body.addChild(main);

        // ПОДВАЛ
        HtmlElement footer = new HtmlElement("footer")
                .setAttribute("class", "footer");

        HtmlElement footerContent = new HtmlElement("div")
                .setAttribute("class", "footer-content");

        footerContent.addChild(new HtmlElement("div")
                .setAttribute("class", "footer-decoration")
                .addChild(new TextElement("✦")));

        footerContent.addChild(new HtmlElement("h2")
                .addChild(new TextElement("NoteShare")));

        footerContent.addChild(new HtmlElement("p")
                .setAttribute("class", "footer-description")
                .addChild(new TextElement(
                        "Музыка, которой хочется делиться")));

        footerContent.addChild(new HtmlElement("div")
                .setAttribute("class", "footer-line"));

        footerContent.addChild(new HtmlElement("p")
                .setAttribute("class", "footer-copyright")
                .addChild(new TextElement("© 2026 NoteShare")));

        footer.addChild(footerContent);
        body.addChild(footer);

        return body;
    }

    private List<HtmlNode> getChildren(HtmlElement element) {
        return element.getChildren();
    }

    private void addMenuItem(
            HtmlElement list, String href, String text) {

        list.addChild(new HtmlElement("li")
                .addChild(new HtmlElement("a")
                        .setAttribute("href", href)
                        .addChild(new TextElement(text))));
    }

    private HtmlElement createMusicNote(MusicNote note) {

        HtmlElement article = new HtmlElement("article")
                .setAttribute("class", "popular-item");

        article.addChild(new HtmlElement("span")
                .setAttribute("class", "popular-number")
                .addChild(new TextElement(note.getNumber())));

        HtmlElement info = new HtmlElement("div")
                .setAttribute("class", "popular-info");

        info.addChild(new HtmlElement("h3")
                .addChild(new TextElement(note.getTitle())));

        info.addChild(new HtmlElement("p")
                .addChild(new TextElement(note.getComposer())));

        article.addChild(info);

        article.addChild(new HtmlElement("span")
                .setAttribute("class", "popular-views")
                .addChild(new TextElement(
                        note.getViews() + " просмотров")));

        return article;
    }
}
