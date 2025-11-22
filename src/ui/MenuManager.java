package ui;
import blackjack.Game;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private static Map<String, Menu> menuList = new HashMap<>();

    public static void setMenuList(Map<String, Menu> menuList) {
        MenuManager.menuList = menuList;
    }
    public static Map<String, Menu> getMenuList() {
        return menuList;
    }
    public static void initMenus(Game game) {
        menuList.put("MAIN", new MainMenu(game));
        menuList.put("PLAY", new PlayScreen(game));
        menuList.put("STATS", new StatsMenu(game.getPlayer()));
        menuList.put("USER", new UserMenu(game));
        menuList.put("MARKET", new MarketMenu(game.getPlayer()));
    }
    public static void showMenu(String menuName){
        for(String name : menuList.keySet()){
            if(name.equals(menuName)){
                menuList.get(name).showOptions();
                return;
            }
        }
    }
}
