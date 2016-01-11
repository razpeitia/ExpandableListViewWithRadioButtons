package com.raz.testapi21;

public class Item  {
    int id;
    String name;
    int option;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
        this.option = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        return !(name != null ? !name.equals(item.name) : item.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: "+ name;
    }
}
