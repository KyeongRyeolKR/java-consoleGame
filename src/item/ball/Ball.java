package item.ball;

import item.Item;

public class Ball extends Item {
    private int canCatch;

    public Ball(String name, int quantity, int price, int canCatch) {
        super(name, quantity, price);
        this.canCatch = canCatch;
    }

    public int getCanCatch() {
        return canCatch;
    }

    @Override
    public void use() {

    }
}
