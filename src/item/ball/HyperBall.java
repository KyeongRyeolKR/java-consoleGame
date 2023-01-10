package item.ball;

public class HyperBall extends Ball{



    public HyperBall(){
        this(0);
    }
    public HyperBall(int quantity) {
        super("하이퍼볼", quantity, 3000, 70);
    }
}
