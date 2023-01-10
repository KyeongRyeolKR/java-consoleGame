package item;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;
    private int quantity;
    private int price;

    public Item(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity(){
        this.quantity -= 1;
    }

    public int getPrice() {
        return price;
    }

    public abstract void use();

}
