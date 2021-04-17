package enums;

/**
 * Contains the Fields in which a search text will be inserted.
 */

public enum SearchField {
    NAME("//input[@name='fnm']"),
    MOL("//input[@name='mol']"),
    ADDRESS("//input[@name='adr']");

    private String selector;

    SearchField(String selector) {
        this.selector= selector;
    }

    public String getSelector() {
        return selector;
    }
}
