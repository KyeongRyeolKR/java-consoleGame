package service;

import inventory.Inventory;
import item.Item;
import item.ball.Ball;
import life.player.Player;
import life.pokemon.Ggobugi;
import life.pokemon.Pikachu;
import life.pokemon.Pokemon;
import life.pokemon.Pyree;
import shop.Shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlayerServiceLogic implements PlayerService {
    private boolean isAlreadyStarted;
    private Player player;
    private Inventory inventory;
    private Scanner scanner;

    public PlayerServiceLogic(){
        this.scanner = new Scanner(System.in);
        this.isAlreadyStarted = false;
    }

    @Override
    public void newGame() {
        setAlreadyStarted(initialize());
        if(!isAlreadyStarted()) {
            showStory();
            while (true) {
                int selectNum = selectStartPokemon();
                System.out.println();
                System.out.println();
                if (selectNum != 0) {
                    switch (selectNum) {
                        case 1:
                            System.out.println("피카츄와 함께 떠나는 모험!\n\n");
                            getInventory().setPokemons(new Pikachu());
                            return;
                        case 2:
                            System.out.println("파이리와 함께 떠나는 모험!\n\n");
                            getInventory().setPokemons(new Pyree());
                            return;
                        case 3:
                            System.out.println("꼬부기와 함께 떠나는 모험!\n\n");
                            getInventory().setPokemons(new Ggobugi());
                            return;
                        default:
                            break;
                    }
                }
            }
        } else {
            System.out.println("이미 시작 포켓몬을 고르셨습니다.");
        }
    }

    @Override
    public void loadGame() {
    }

    @Override
    public void myPage() {
        System.out.println("\n\n------------------ << 내정보 >> ------------------");
        System.out.println("| HP : " + getPlayer().getHp());
        System.out.println("| -----------------------------------------------");
        System.out.printf("| 보유 머니 : %d\n", getPlayer().getMoney());
        System.out.println("-------------------------------------------------\n");
        System.out.println("------------------ << 인벤토리 >> -----------------");
        List<Integer> counts = countItems(getInventory().getItems());
        System.out.printf("[몬스터볼 x%d | 수퍼볼 x%d | 하이퍼볼 x%d | 마스터볼 x%d]\n", counts.get(0), counts.get(1), counts.get(2), counts.get(3));
        System.out.printf("[엘릭서 x%d | 파워엘릭서 x%d]\n", counts.get(4), counts.get(5));
        System.out.println("-------------------------------------------------");
        for(Pokemon pokemon : getInventory().getPokemons()) {
            System.out.println(pokemon);
        }
        System.out.println("-------------------------------------------------\n\n");
    }

    @Override
    public void shopping() {
        Shop shop = new Shop();
        shop.shopping();
    }

    @Override
    public void catchMon() {
        int totalBallCount = 0;
        for(Item item : getInventory().getItems()){
            if(item instanceof Ball){
                totalBallCount += item.getQuantity();
            }
        }
        if(totalBallCount == 0){
            System.out.println("보유하신 볼이 하나도 없습니다.");
            System.out.println("상점에서 볼을 구매하세요.");
        } else {
            System.out.println("보유하신 볼이 있습니다.");
            Pokemon creature = searchCreature();
            Pokemon myPokemon = selectPokemon();
            fightWith(creature, myPokemon);
            player.setHp(player.getHp() - 10);
        }
    }

    @Override
    public void fight() {
        Pokemon rocketdan = searchRocketdan();
        Pokemon myPokemon = selectPokemon();
        fightWith(rocketdan, myPokemon);
        player.setHp(player.getHp() - 10);
    }

    @Override
    public void relax() {

        while(true) {
            System.out.println("\n\n휴식의 방에 입장하셨습니다.");
            int selectNumber = selectRelax();
            switch (selectNumber) {
                case 1:
                    whichRelax("의자", 20, 500, 3000);
                    break;
                case 2:
                    whichRelax("침대", 40, 1000, 5000);
                    break;
                case 3:
                    whichRelax("사우나", 100, 2000, 10000);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }

    }

    @Override
    public void saveGame() {

    }

    public boolean isAlreadyStarted() {
        return isAlreadyStarted;
    }

    public void setAlreadyStarted(boolean alreadyStarted) {
        isAlreadyStarted = alreadyStarted;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    private boolean initialize(){
        if(Inventory.getInstance().getPokemons().size() != 0){
            return true;
        } else {
            this.player = Player.getInstance();
            this.inventory = Inventory.getInstance();
            System.out.println("\n\n초기화 진행...\n\n");
            return false;
        }
    }

    private int selectStartPokemon() {
        int selectNum = 0;
        System.out.println("시작 포켓몬을 골라주세요.");
        System.out.println("1. 피카츄 | 2. 파이리 | 3. 꼬부기");
        selectNum = scanner.nextInt();
        if(selectNum >= 1 && selectNum <= 3) {
            return selectNum;
        } else {
            System.out.println("잘못된 선택입니다.");
            return 0;
        }

    }

    private int selectRelax(){
        int selectNum = 0;
        while(true) {
            System.out.println("어떤 휴식을 할지 선택해주세요.");
            System.out.println("1. 앉기, 가격 : 500, 소요시간 : 3초, HP 회복량 : 20");
            System.out.println("2. 눕기, 가격 : 1000, 소요시간 : 5초, HP 회복량 : 40");
            System.out.println("3. 사우나, 가격 : 2000, 소요시간 : 10초, HP 회복량 : 100");
            System.out.println("0. 돌아가기");
            selectNum = scanner.nextInt();
            if(selectNum >= 0 && selectNum <= 3){
                return selectNum;
            } else {
                System.out.println("잘못된 선택입니다.");
                return -1;
            }
        }
    }

    private void showStory() {
        System.out.println("시작 스토리 출력...\n\n");
    }

    private void whichRelax(String which, int increaseHp, int price, int time){
        System.out.printf("\n\n지우가 %s에서 휴식을 합니다...\n", which);
        getPlayer().setHp(getPlayer().getHp() + increaseHp);
        getPlayer().setMoney(getPlayer().getMoney() - price);
        try {
            int sec = time / 1000;
            for(int i=sec; i>0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            System.out.println("휴식을 끝마쳤습니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("현재 HP : %d\n", getPlayer().getHp());
    }

    private List<Integer> countItems(List<Item> items){
        List<Integer> counts = new ArrayList<>();
        for(Item item : items){
            counts.add(item.getQuantity());
        }

        return counts;
    }


    private Pokemon selectPokemon(){
        int i = 1;
        List<Pokemon> myPokemons = inventory.getPokemons();
        System.out.println("어떤 포켓몬으로 싸우시겠습니까?");
        for(Pokemon pokemon : myPokemons){
            System.out.printf("%d. %s  |  %d  |  %s\n", i, pokemon.getName(), pokemon.getHp(), pokemon.getType());
            i++;
        }
        int selectNum = scanner.nextInt();

        return myPokemons.get(selectNum-1);
    }

    private Pokemon searchCreature(){
        System.out.print("포켓몬을 찾는 중");
        Pokemon creature = randomCreature();
        try {
            for(int i=0; i<3; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("\n\n야생의 %s(이)가 나타났다!!\n", creature.getName());
        System.out.printf("체력 : %d, 타입 : %s\n\n", creature.getHp(), creature.getType());
        return creature;
    }
    
    private Pokemon searchRocketdan(){
        System.out.println("로켓단을 찾는 중");
        Pokemon creature = randomCreature();
        try {
            for(int i=0; i<3; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("\n\n로켓단의 %s(이)가 나타났다!!\n", creature.getName());
        System.out.printf("체력 : %d, 타입 : %s\n\n", creature.getHp(), creature.getType());
        return creature;
    }

    private void fightWith(Pokemon creature, Pokemon myPokemon){
        System.out.println("------------------------------");
        System.out.println("1. 싸운다.");
        System.out.println("2. 도망간다.");
        System.out.println("------------------------------");
        int menuNum = scanner.nextInt();
        if(menuNum == 1){
            while(creature.getHp() > 0 && myPokemon.getHp() > 0) {
                System.out.println("\n\n1. 공격하기");
                System.out.println("2. 회복하기");
                System.out.println("3. 포획하기");
                System.out.println("4. 도망가기");
                int selectNum = scanner.nextInt();
                if(selectNum == 1){
                    int creatureDamage = (int) (Math.random() * 30 + 1);
                    // 공격하기
                    System.out.println("\n\n------------------------------");
                    System.out.println("사용할 스킬을 입력해주세요. ( 1 ~ 3 )");
                    System.out.println("------------------------------");
                    int selectSkill = scanner.nextInt();
                    switch (selectSkill){
                        case 1:
                            myPokemon.skill1(creature);
                            System.out.printf("\n(상대) %s(이)가 %d만큼 데미지를 주었습니다.\n", creature.getName(), creatureDamage);
                            myPokemon.setHp(myPokemon.getHp() - creatureDamage);
                            System.out.printf("(나) %s의 현재 체력 : %d\n", myPokemon.getName(), myPokemon.getHp());
                            break;
                        case 2:
                            myPokemon.skill2(creature);
                            System.out.printf("\n(상대) %s(이)가 %d만큼 데미지를 주었습니다.\n", creature.getName(), creatureDamage);
                            myPokemon.setHp(myPokemon.getHp() - creatureDamage);
                            System.out.printf("(나) %s의 현재 체력 : %d\n", myPokemon.getName(), myPokemon.getHp());
                            break;
                        case 3:
                            myPokemon.skill3(creature);
                            System.out.printf("\n(상대) %s(이)가 %d만큼 데미지를 주었습니다.\n", creature.getName(), creatureDamage);
                            myPokemon.setHp(myPokemon.getHp() - creatureDamage);
                            System.out.printf("(나) %s의 현재 체력 : %d\n", myPokemon.getName(), myPokemon.getHp());
                            break;
                    }
                } else if(selectNum == 2){
                    // 회복하기
                    System.out.println("\n\n------------------------------");
                    System.out.println("사용할 포션을 선택해주세요.");
                    System.out.printf("1. %s x%d\n", inventory.getItems().get(4).getName(), inventory.getItems().get(4).getQuantity());
                    System.out.printf("2. %s x%d\n", inventory.getItems().get(5).getName(), inventory.getItems().get(5).getQuantity());
                    System.out.println("------------------------------");
                    int selectNumber = scanner.nextInt();
                    if(inventory.getItems().get(selectNumber+3).getQuantity() == 0){
                        System.out.printf("%s 개수 0개!! 상점에서 구매해주세요.\n", inventory.getItems().get(3+selectNumber).getName());
                    } else if(inventory.getItems().get(selectNumber+3).getQuantity() != 0){
                        if(selectNumber == 1){
                            myPokemon.setHp(myPokemon.getHp() + 50);
                            System.out.printf("(나) %s의 HP를 50 회복했습니다. HP : %d\n", myPokemon.getName(), myPokemon.getHp());
                            inventory.getItems().get(4).decreaseQuantity();
                        } else if(selectNumber == 2){
                            myPokemon.setHp(myPokemon.getHp() + 100);
                            System.out.printf("(나) %s의 HP를 100 회복했습니다. HP : %d\n", myPokemon.getName(), myPokemon.getHp());
                            inventory.getItems().get(5).decreaseQuantity();
                        } else {
                            System.out.println("잘못 입력하셨습니다.");
                        }
                    }
                } else if(selectNum == 3){
                    // 포획하기
                    System.out.println("\n\n------------------------------");
                    System.out.println("사용할 볼을 선택해주세요.");
                    System.out.printf("1. %s x%d\n", inventory.getItems().get(0).getName(), inventory.getItems().get(0).getQuantity());
                    System.out.printf("2. %s x%d\n", inventory.getItems().get(1).getName(), inventory.getItems().get(1).getQuantity());
                    System.out.printf("3. %s x%d\n", inventory.getItems().get(2).getName(), inventory.getItems().get(2).getQuantity());
                    System.out.printf("4. %s x%d\n", inventory.getItems().get(3).getName(), inventory.getItems().get(3).getQuantity());
                    System.out.println("------------------------------");
                    int selectNumber = scanner.nextInt();
                    if(inventory.getItems().get(selectNumber-1).getQuantity() == 0){
                        System.out.printf("%s 개수 0개!! 상점에서 구매해주세요.\n", inventory.getItems().get(selectNumber-1).getName());
                    } else if(inventory.getItems().get(selectNumber-1).getQuantity() != 0){
                        if(selectNumber == 1){
                            if(creature.getHp() <= 30){
                                System.out.println("포획 성공!!");
                                inventory.setPokemons(creature);
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                                return;
                            } else {
                                System.out.println("포획 실패!!");
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                            }
                        } else if(selectNumber == 2){
                            if(creature.getHp() <= 50){
                                System.out.println("포획 성공!!");
                                inventory.setPokemons(creature);
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                                return;
                            } else {
                                System.out.println("포획 실패!!");
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                            }
                        } else if(selectNumber == 3){
                            if(creature.getHp() <= 70){
                                System.out.println("포획 성공!!");
                                inventory.setPokemons(creature);
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                                return;
                            } else {
                                System.out.println("포획 실패!!");
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                            }
                        } else if(selectNumber == 4){
                            if(creature.getHp() <= 100){
                                System.out.println("포획 성공!!");
                                inventory.setPokemons(creature);
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                                return;
                            } else {
                                System.out.println("포획 실패!!");
                                inventory.getItems().get(selectNumber-1).decreaseQuantity();
                            }
                        } else {
                            System.out.println("잘못 입력하셨습니다.");
                        }
                    }
                } else if(selectNum == 4){
                    // 도망가기
                    System.out.println("호 다 닥 !!");
                    return;
                } else {
                    System.out.println("잘못 입력하셨습니다.");
                }
                if(creature.getHp() <= 0){
                    System.out.printf("\n\n(상대) %s(이)가 죽었습니다.\n", creature.getName());
                } else if(myPokemon.getHp() <= 0){
                    System.out.printf("\n\n(나) %s(이)가 죽었습니다.\n", myPokemon.getName());
                }
            }
        } else if(menuNum == 2){
            System.out.println("호 다 닥 !!");
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    private Pokemon randomCreature(){
        List<Pokemon> creatures = new ArrayList<>();
        creatures.add(new Pikachu());
        creatures.add(new Ggobugi());
        creatures.add(new Pyree());

        Collections.shuffle(creatures);
        return creatures.get(0);
    }

}
