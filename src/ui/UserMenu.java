package ui;

import service.PlayerService;
import service.PlayerServiceLogic;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner;
    PlayerService service = new PlayerServiceLogic();

    public UserMenu(){
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        int inputNumber = 0;

        while(true){
            displayMenu();
            inputNumber = selectMenu();
            try {
                switch (inputNumber) {
                    case 1:
                        // 새로 시작하기
                        service.newGame();
                        break;
                    case 2:
                        // 파일 불러오기
                        service.loadGame();
                        break;
                    case 3:
                        // 내정보 보기
                        service.myPage();
                        break;
                    case 4:
                        // 상점
                        service.shopping();
                        break;
                    case 5:
                        // 포획하기
                        service.catchMon();
                        break;
                    case 6:
                        // 다음 로켓단 상대하기
                        service.fight();
                        break;
                    case 7:
                        // 휴식하기
                        service.relax();
                        break;
                    case 8:
                        // 저장하기
                        service.saveGame();
                        break;
                    case 0:
                        // 종료하기
                        return;
                    default:
                        System.out.println("메뉴를 잘못 입력하셨습니다.");
                }
            } catch (NullPointerException e){
                System.out.println("내 정보가 없습니다.");
                System.out.println("새 게임을 시작해주세요.");
            }
        }
    }
    private void displayMenu(){
        System.out.println("\n\n========================= 지우의 모험 =========================");
        System.out.println("1. 새로 시작하기");
        System.out.println("2. 파일 불러오기  // 미구현");
        System.out.println("3. 내정보 보기");
        System.out.println("4. 상점");
        System.out.println("5. 포획하기");
        System.out.println("6. 다음 로켓단 상대하기");
        System.out.println("7. 휴식하기");
        System.out.println("8. 파일 저장하기  // 미구현");
        System.out.println("0. 종료하기");
        System.out.println("=============================================================");
    }

    private int selectMenu(){
        int menuNum = scanner.nextInt();
        if(menuNum >= 0 && menuNum <= 8){
            scanner.nextLine();
            return menuNum;
        } else {
            System.out.println("잘못된 메뉴 선택입니다.");
            return -1;
        }
    }
}
