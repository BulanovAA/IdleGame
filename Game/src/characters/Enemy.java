package characters;

import draw.Drawer;

import java.util.Random;

import static main.Main.mainController;

public class Enemy {
    private int level = 1;
    private int hp = 10;
    private int attack = 1;
    private double speed = 1;
    private int x;
    private int y;

    private int[] distance;
    private int[] pos;

    public Enemy(int x, int y, MainCharacter mainCharacter) {
        this.level = mainCharacter.getEnemyLevel();
        this.hp *= level;
        this.attack *= level;
        this.speed += (int) (level * 0.01);
        this.x = x;
        this.y = y;
        this.distance = new int[]{mainCharacter.getX() - x, mainCharacter.getY() - y};
        this.pos = new int[]{(int) Math.signum(x - mainCharacter.getX()), (int) Math.signum(y - mainCharacter.getY())};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return (int) calcGyp(this.distance[0], this.distance[1]);
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void slay(MainCharacter mainCharacter) {
        Random random = new Random();
        if (random.nextInt(100) < mainCharacter.getDodgeChance()) {
        } else if (random.nextInt(100) < mainCharacter.getBlockChance()) {
            mainCharacter.dealDamage((int) (getAttack() - mainCharacter.getBlockPower()));
        } else {
            mainCharacter.dealDamage((int) (getAttack() - mainCharacter.getArmor()));
        }
    }

    public void moveEnemy(MainCharacter mainCharacter) {
        if (this.intersects(mainCharacter)) {
            slay(mainCharacter);
        } else {
            x -= (int) (calcDegree() * speed * 10 * mainController.getSpeed()) * pos[0];
            y -= (int) (calcDegreeRe() * speed * 10 * mainController.getSpeed()) * pos[1];
            reCalcDistance(mainCharacter);
            reCalcPos(mainCharacter);
        }
    }

    public void reCalcDistance(MainCharacter mainCharacter) {
        this.distance = new int[]{Math.abs(mainCharacter.getX() - this.x), Math.abs(mainCharacter.getY() - this.y)};
    }

    public void reCalcPos(MainCharacter mainCharacter) {
        this.pos = new int[]{(int) Math.signum(this.x - mainCharacter.getX()), (int) Math.signum(this.y - mainCharacter.getY())};
    }

    public boolean intersects(MainCharacter mainCharacter) {
        return Math.abs(mainCharacter.getX() - getX()) < 30 && Math.abs(mainCharacter.getY() - getY()) < 30;
    }

    public double calcDegree() {
        return (double) distance[0] / calcGyp(distance[0], distance[1]);
    }

    public double calcDegreeRe() {
        return (double) distance[1] / calcGyp(distance[0], distance[1]);
    }

    private double calcGyp(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getLevel() {
        return level;
    }

    public double getSpeed() {
        return speed;
    }

    public void dealDamage(int i, MainCharacter mainCharacter) {
        if (hp <= i) {
            Drawer.destroyEnemy(this, mainCharacter);
        } else {
            hp -= i;
        }
    }
}
