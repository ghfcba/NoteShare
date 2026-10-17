public class MusicNote {

    private final String number;
    private final String title;
    private final String composer;
    private final int views;

    public MusicNote(String number, String title,
                     String composer, int views) {
        this.number = number;
        this.title = title;
        this.composer = composer;
        this.views = views;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public int getViews() {
        return views;
    }
}
