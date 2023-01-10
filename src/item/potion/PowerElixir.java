package item.potion;

public class PowerElixir extends Potion {


    public PowerElixir(){
        this(0);
    }
    public PowerElixir(int quantity) {
        super("파워엘릭서", quantity, 2000, 100);
    }
}