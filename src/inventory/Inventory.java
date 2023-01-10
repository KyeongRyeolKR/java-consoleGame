package inventory;

import item.Item;
import item.ball.HyperBall;
import item.ball.MasterBall;
import item.ball.MonsterBall;
import item.ball.SuperBall;
import item.potion.Elixir;
import item.potion.PowerElixir;
import life.pokemon.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory inventoryInstance;
    private List<Item> items;
    private List<Pokemon> pokemons;

    private Inventory(){
        this.items = new ArrayList<>();
        this.pokemons = new ArrayList<>();
        initializeInventory();
    }

    public static Inventory getInstance(){
        if(inventoryInstance == null){
            inventoryInstance = new Inventory();
        }
        return inventoryInstance;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        items.add(item);
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    private void initializeInventory(){
        items.add(new MonsterBall());
        items.add(new SuperBall());
        items.add(new HyperBall());
        items.add(new MasterBall());
        items.add(new Elixir());
        items.add(new PowerElixir());
    }
}
