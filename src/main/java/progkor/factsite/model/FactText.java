package progkor.factsite.model;

import java.util.Objects;

public class FactText {

    private Long id;
    private String context;
    private Theme theme;

    public FactText() {
    }

    public FactText(final Long id, final String context, final Theme theme) {
        this.id = id;
        this.context = context;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(final String context) {
        this.context = context;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(final Theme theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactText)) {
            return false;
        }
        final FactText factText = (FactText) o;
        return Objects.equals(id, factText.id) && Objects.equals(context, factText.context) && theme == factText.theme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, context, theme);
    }

    @Override
    public String toString() {
        return "FactSite{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", theme=" + theme +
                '}';
    }
}
