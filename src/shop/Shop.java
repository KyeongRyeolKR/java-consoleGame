package shop;

import inventory.Inventory;
import item.ball.*;
import item.potion.Elixir;
import item.potion.Potion;
import item.potion.PowerElixir;
import life.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    private List<Ball> balls;
    private List<Potion> potions;
    private Inventory inventory = Inventory.getInstance();
    private Player player = Player.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private int itemIndex = 1;

    public Shop(){
        this.balls = new ArrayList<>();
        this.potions = new ArrayList<>();
        initializeShop();
    }

    public void shopping(){
        while(true){
            displayShopItemList();
            int selectNumber = selectItem();
            if(selectNumber == -1){
                System.out.println("잘못 입력하셨습니다.");
            } else if(selectNumber == 0){
                break;
            } else {
                String yesOrNo = "";
                System.out.printf("%s를 구매하시겠습니까?( Y / N ) ", inventory.getItems().get(selectNumber - 1).getName());
                yesOrNo = scanner.nextLine();
                if (yesOrNo.toLowerCase().equals("y")) {
                    if(player.getMoney() < inventory.getItems().get(selectNumber - 1).getPrice()){
                        System.out.printf("보유한 머니가 부족합니다. (잔액 : %d)\n", player.getMoney());
                    } else {
                        player.setMoney(player.getMoney() - inventory.getItems().get(selectNumber - 1).getPrice());
                        inventory.getItems().get(selectNumber - 1).increaseQuantity();
                        System.out.println("구매 성공!");
                        System.out.printf("보유한 %s의 개수 : %d\n", inventory.getItems().get(selectNumber - 1).getName(), inventory.getItems().get(selectNumber - 1).getQuantity());
                    }
                } else if (yesOrNo.toLowerCase().equals("n")) {
                    System.out.println("구매를 취소하셨습니다.");
                } else {
                    System.out.println("잘못 입력하셨습니다.");
                }
            }
        }
    }

    private int selectItem() {
        int selectNumber = scanner.nextInt();
        if(selectNumber > balls.size() + potions.size() || selectNumber < 1){
            if(selectNumber == 0){
                return 0;
            }
            return -1;
        } else {
            scanner.nextLine();
            return selectNumber;
        }
    }

    public void displayShopItemList(){
        System.out.println();
        System.out.println();
        System.out.println("------------------- <<아이템샵>> -------------------");
        for(Ball ball : balls) {
            System.out.printf("%d. %s, 가격 : %d\n", itemIndex, ball.getName(), ball.getPrice());
            itemIndex++;
        }
        for(Potion potion : potions){
            System.out.printf("%d. %s, 가격 : %d\n", itemIndex, potion.getName(), potion.getPrice());
            itemIndex++;
        }
        System.out.println("0. 돌아가기");
        System.out.println("--------------------------------------------------");
        itemIndex = 1;
    }

    private void initializeShop(){
        balls.add(new MonsterBall());
        balls.add(new SuperBall());
        balls.add(new HyperBall());
        balls.add(new MasterBall());
        potions.add(new Elixir());
        potions.add(new PowerElixir());
    }
}
