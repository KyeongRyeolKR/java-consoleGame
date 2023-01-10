package life.pokemon;

import java.io.Serializable;

public class Pokemon implements Skill {
    private String name;
    private int hp;
    private Type type;

    public Pokemon(String name, int hp, Type type){
        this.name = name;
        this.hp = hp;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if(hp < 0) {
            setHp(0);
        } else if(hp > 100){
            setHp(100);
        } else {
            this.hp = hp;
        }
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "이름 : " + name + " | 체력 : " + hp + " | 속성 : " + type;
    }

    @Override
    public void skill1(Pokemon pokemon) {

    }

    @Override
    public void skill2(Pokemon pokemon) {

    }

    @Override
    public void skill3(Pokemon pokemon) {

    }
}