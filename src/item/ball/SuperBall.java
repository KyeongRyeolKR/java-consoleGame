package item.ball;

public class SuperBall extends Ball{


    public SuperBall(){
        this(0);
    }
    public SuperBall(int quantity) {
        super("수퍼볼", quantity, 2000, 50);
    }
}
