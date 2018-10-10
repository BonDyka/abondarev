package ru.job4j.sql.jdbc;

/**
 * Class for app's entries.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 30.12.2017.
 */

public class Entry {

    private int field;

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        return field == entry.field;

    }

    @Override
    public int hashCode() {
        return field;
    }
}
