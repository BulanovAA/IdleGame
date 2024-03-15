package draw;

import characters.Enemy;
import characters.MainCharacter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import main.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Drawer {
    private static List<Enemy> enemyList = new ArrayList<>();
    private static final Paint enemyAttackRadiusPaint = new Color(0.5, 0, 0, 0.5);
    private static final Paint mainAttackRadiusPaint = new Color(0, 0.5, 0, 0.5);

    public static Group drawBattleField() {
        Group group = new Group();
        for (Enemy en : enemyList) {
            group.getChildren().add(drawEnemy(en));
            group.getChildren().add(drawEnemyAttackRadius(en));
        }
        return group;
    }

    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    public static int getNearestEnemy(List<Enemy> enemyList) {
        if (enemyList.isEmpty()) {
            return 0;
        }
        ListIterator<Enemy> iter = enemyList.listIterator();
        int old = 1000;
        int index = 0;
        int indexRe = 0;
        while (iter.hasNext()) {
            int dist = iter.next().getDistance();
            index++;
            if (dist < old) {
                old = dist;
                indexRe = index;
            }
        }
        return indexRe;
    }

    public static void destroyEnemy(Enemy enemy, MainCharacter mainCharacter) {
        enemyList.remove(enemy);
        mainCharacter.addGold(enemy.getLevel() + mainCharacter.getBonusGold());
        mainCharacter.addExperience((enemy.getLevel() + mainCharacter.getBonusExp()));
        mainCharacter.incEnemyKilled();
    }

    public static void addEnemy(int x, int y, MainCharacter mainCharacter) {
        enemyList.add(new Enemy(x, y, mainCharacter));
    }

    public static Node drawEnemy(Enemy enemy) {
        return new Circle(enemy.getX(), enemy.getY(), 10);
    }

    public static Node drawMainCharacter(MainCharacter mainCharacter) {
        return new Circle(mainCharacter.getX(), mainCharacter.getY(), 20);
    }

    public static Node drawEnemyAttackRadius(Enemy enemy) {
        Circle circle = new Circle(enemy.getX(), enemy.getY(), 30);
        circle.setFill(enemyAttackRadiusPaint);
        return circle;
    }

    public static Node drawMainAttackRadius(MainCharacter mainCharacter) {
        Group group = new Group();
        Circle circle = new Circle(mainCharacter.getX(), mainCharacter.getY(), 40);
        circle.setFill(mainAttackRadiusPaint);
        Circle point = new Circle(mainCharacter.getX(), mainCharacter.getY(), 10);
        if (Main.attack) {
            point.setFill(mainAttackRadiusPaint);
        } else {
            point.setFill(enemyAttackRadiusPaint);
        }
        if (drawCrit(mainCharacter) != null) {
            group.getChildren().add(drawCrit(mainCharacter));
        }
        group.getChildren().add(circle);
        group.getChildren().add(point);
        return group;
    }

    public static Node drawCrit(MainCharacter mainCharacter) {
        if (mainCharacter.getCrit()) {
            Text text = new Text("Crit!");
            text.setLayoutX(mainCharacter.getX() - 10);
            text.setLayoutY(mainCharacter.getY() - 40);
            return text;
        }
        return null;
    }

    public static void restartBattle(MainCharacter mainCharacter) {
        mainCharacter.restart();
        while (!enemyList.isEmpty()) {
            destroyEnemy(enemyList.get(0), mainCharacter);
        }
    }

    public static String convert(double count) {
        int k = 0;
        int countW = (int) count;
        while (countW >= 1000) {
            k++;
            countW = countW / 1000;
        }
        switch (k) {
            case 0:
                if(count%1==0){
                    return String.valueOf(countW);
                }
                return String.valueOf(count);
            case 1:
                return countW + "k";
            case 2:
                return countW + "m";
            case 3:
                return countW + "b";
            case 4:
                return countW + "t";
            default:
                return null;
        }
    }
}
