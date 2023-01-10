import ui.UserMenu;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
        Math.random();
    }

    public void start(){
        UserMenu userMenu = new UserMenu();
        userMenu.show();
    }
}