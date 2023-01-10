package item.ball;

import item.Item;

public class MonsterBall extends Ball {

    public MonsterBall(){
        this(0);
    }

    public MonsterBall(int quantity) {
        super("몬스터볼", quantity, 1000, 30);
    }

    @Override
    public void use() {
        // 포켓볼을 사용해서 포켓몬을 잡다
    }
}
