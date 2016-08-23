package ufo;

import ufo.entities.Item;

public class ItemDto {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public ItemDto(Item item) {
        this.name = item.getName();
    }
}
