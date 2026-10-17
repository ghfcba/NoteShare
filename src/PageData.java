import java.util.List;

public class PageData {

    @WebComponent(tag = "p")
    private final String subtitle = "Музыка начинается здесь!";

    @WebComponent(tag = "p")
    private final String description =
            "Находите, делитесь, вдохновляйтесь нотами";

    @WebComponent(tag = "h2")
    private final String popularTitle = "Популярные ноты";

    @WebComponent(tag = "p", cssClass = "section-description")
    private final String popularDescription =
            "Откройте для себя прекрасные музыкальные произведения";

    @WebComponent(tag = "div", cssClass = "popular-list")
    private final List<MusicNote> popularNotes = List.of(
            new MusicNote("01", "Лунная соната",
                    "Людвиг ван Бетховен", 1240),
            new MusicNote("02", "Ноктюрн № 2",
                    "Фридерик Шопен", 980),
            new MusicNote("03", "Времена года",
                    "Антонио Вивальди", 865)
    );
}
