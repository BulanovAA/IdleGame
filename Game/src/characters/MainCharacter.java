package characters;

import draw.Drawer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.Main.*;

public class MainCharacter {

    private static final int LEVEL_COST_INCREASE = 10;
    private int level;
    private int levelCost = LEVEL_COST_INCREASE;
    private int sPoints;
    private int strength;
    private int vitality;
    private int agility;
    private int intelligent;
    private int luck;
    private int experience;
    private int hp;

    private int enemyLevel = 1;
    private int spawnCount = 0;
    private int spawnTime = 1;

    private int enemyKilled = 0;

    private int x;
    private int y;

    private int[][] upgrades;
    private static final int[][] upgradeInc = {{10, 50, 20, 10, 50, 10}, {100, 10, 20, 50, 50, 100}};
    private int[][] upgradeCost = {{10, 50, 20, 10, 50, 10}, {100, 10, 20, 50, 50, 100}};
    private int gold;
    private boolean crit = false;

    public MainCharacter() {
        this.level = 0;
        this.strength = 0;
        this.vitality = 0;
        this.agility = 0;
        this.intelligent = 0;
        this.luck = 0;
        this.experience = 0;
        this.upgrades = new int[2][6];
        this.hp = this.getHealth();
        this.x = 369;
        this.y = 359;
    }

    public MainCharacter(MainCharacter mainCharacter) {
        this.level = mainCharacter.getLevel();
        this.strength = mainCharacter.getStrength();
        this.vitality = mainCharacter.getVitality();
        this.agility = mainCharacter.getAgility();
        this.intelligent = mainCharacter.getIntelligent();
        this.luck = mainCharacter.getLuck();
        this.experience = mainCharacter.getExperience();
        this.upgrades = mainCharacter.getUpgrades();
        this.hp = mainCharacter.getHealth();
        this.sPoints = mainCharacter.getSPoints();
        this.gold = mainCharacter.getGold();
        this.enemyLevel = mainCharacter.getEnemyLevel();
        this.spawnCount = mainCharacter.getSpawnCount();
        this.spawnTime = mainCharacter.getSpawnTime();
        this.x = 369;
        this.y = 359;
    }

    public MainCharacter(String string) {
        String[] list = string.split("\n");
        this.level = Integer.parseInt(list[0]);
        this.sPoints = Integer.parseInt(list[1]);
        this.strength = Integer.parseInt(list[2]);
        this.vitality = Integer.parseInt(list[3]);
        this.agility = Integer.parseInt(list[4]);
        this.intelligent = Integer.parseInt(list[5]);
        this.luck = Integer.parseInt(list[6]);
        this.experience = Integer.parseInt(list[7]);
        this.enemyLevel = Integer.parseInt(list[8]);
        this.spawnCount = Integer.parseInt(list[9]);
        this.spawnTime = Integer.parseInt(list[10]);
        this.enemyKilled = Integer.parseInt(list[11]);
        this.upgrades = fromString(list[12]);
        this.upgradeCost = fromString(list[13]);
        this.gold = Integer.parseInt(String.valueOf(list[14]));
        this.hp = this.getHealth();
        this.x = 369;
        this.y = 359;
    }

    private int[][] fromString(String string) {
        int[][] list = new int[2][6];
        string = string.replaceAll("[\\Q[\\E]", "");
        string = string.replaceAll("[\\Q]\\E]", "");
        String[] sList = string.split(",");
        int k = 0;
        for (String s : sList) {
            list[(int) k / 6][k - 6 * ((int) k / 6)] = Integer.parseInt(s.trim());
            k++;
        }
        return list;
    }

    public int getLevel() {
        return this.level;
    }

    public int getSPoints() {
        return this.sPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getStrengthCost() {
        return getStrength() + 1;
    }

    public boolean incStrength() {
        if (this.sPoints > getStrength()) {
            this.strength++;
            this.sPoints -= getStrength();
            return true;
        }
        return false;
    }

    public int getVitality() {
        return vitality;
    }

    public int getVitalityCost() {
        return getVitality() + 1;
    }

    public boolean incVitality() {
        if (this.sPoints > getVitality()) {
            this.vitality++;
            this.sPoints -= getVitality();
            return true;
        }
        return false;
    }

    public int getAgility() {
        return agility;
    }

    public int getAgilityCost() {
        return getAgility() + 1;
    }

    public boolean incAgility() {
        if (this.sPoints > getAgility()) {
            this.agility++;
            this.sPoints -= getAgility();
            return true;
        }
        return false;
    }

    public int getIntelligent() {
        return intelligent;
    }

    public int getIntelligentCost() {
        return getIntelligent() + 1;
    }

    public boolean incIntelligent() {
        if (this.sPoints > getIntelligent()) {
            this.intelligent++;
            this.sPoints -= getIntelligent();
            return true;
        }
        return false;
    }

    public int getLuck() {
        return luck;
    }

    public int getLuckCost() {
        return getLuck() * 5 + 5;
    }

    public boolean incLuck() {
        if (this.sPoints > getLuck() * 5 + 4) {
            this.luck++;
            this.sPoints -= getLuck() * 5;
            return true;
        }
        return false;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevelCost() {
        return levelCost;
    }

    public void addExperience(int experience) {
        this.experience += experience;
        if (this.experience >= levelCost) {
            this.experience -= levelCost;
            levelCost += LEVEL_COST_INCREASE;
            level++;
            sPoints += level / 5 + 1;
        }
    }

    public int getUpgradeLevel(int x, int y) {
        return this.upgrades[x][y];
    }

    public int[][] getUpgrades() {
        return upgrades;
    }

    public int[][] getUpgradesCost() {
        return upgradeCost;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public boolean removeGold(int cost) {
        if (this.gold >= cost) {
            this.gold -= cost;
            return true;
        }
        return false;
    }

    public boolean incUpgrade(int x, int y) {
        if (removeGold(getUpgradeCost(x, y))) {
            upgrades[x][y]++;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveMain(List<Enemy> enemyList) {
        if (enemyList.isEmpty()) {
            return;
        }
        Enemy en = enemyList.get(Drawer.getNearestEnemy(enemyList) - 1);
        if (en.intersects(this)) {
            if (attack) {
                attackTimeline.stop();
                attackTimeline.play();
                attack = false;
                slay(en);
            }
        } else {
            crit = false;
            x -= (int) (en.calcDegree() * getSpeed() / 10 * (-1) * en.getPos()[0] * mainController.getSpeed());
            y -= (int) (en.calcDegreeRe() * getSpeed() / 10 * (-1) * en.getPos()[1] * mainController.getSpeed());
        }
    }

    public void slay(Enemy en) {
        Random random = new Random();
        if (random.nextInt(100) < getCritChance()) {
            en.dealDamage(getAttackPower() * getCritPower() / 100, this);
            crit = true;
        } else {
            crit = false;
            en.dealDamage(getAttackPower(), this);
        }
    }

    public void dealDamage(int attack) {
        if (hp <= attack) {
            Drawer.restartBattle(this);
        } else {
            hp -= attack;
        }
    }

    public void restart() {
        x = 369;
        y = 359;
        hp = getHealth();
        gold = 0;
        enemyLevel = 1;
        timeline.stop();
        timeline.play();
        spawnTimeline.stop();
        startSpawnTimeline(this);
    }

    public void healthRegen() {
        hp += (int) (getHealth() * getHealthRegeneration() * 0.01);
    }

    public int getHp() {
        return hp;
    }

    public int getUpgradeCost(int x, int y) {
        return (upgradeCost[x][y] + upgradeInc[x][y] * getUpgradeLevel(x, y));
    }

    public int getAttackPower() {
        return (10 + getStrength() * 2 + getUpgradeLevel(0, 0));
    }

    public int getAttackSpeed() {
        return (100 + 5 * getAgility() + 2 * getUpgradeLevel(1, 0));
    }

    public double getCritChance() {
        return (getAgility() + 0.5 * getIntelligent() + getLuck() + 0.5 * getUpgradeLevel(0, 1));
    }

    public int getCritPower() {
        return (110 + 2 * getStrength() + getAgility() + getUpgradeLevel(1, 1));
    }

    public int getSpeed() {
        return (100 + 2 * getVitality() + getAgility() + getUpgradeLevel(0, 2));
    }

    public double getArmor() {
        return 0.5 * getVitality() + 0.5 * getUpgradeLevel(1, 2);
    }

    public int getHealth() {
        return 100 + 5 * getVitality() + 10 * getUpgradeLevel(0, 3);
    }

    public double getHealthRegeneration() {
        return getVitality() + 0.5 * getUpgradeLevel(1, 3);
    }

    public double getBlockChance() {
        return getStrength() + getVitality() + 0.1 * getUpgradeLevel(0, 4);
    }

    public double getDodgeChance() {
        return 2 * getAgility() + getLuck() + 0.1 * getUpgradeLevel(1, 4);
    }

    public int getSkillPower() {
        return 10 + getIntelligent() + getUpgradeLevel(0, 5);
    }

    public int getSkillSpeed() {
        return 100 + getAgility() + 5 * getVitality() + 2 * getUpgradeLevel(1, 5);
    }

    public int getBonusGold() {
        return getLuck();
    }

    public int getBonusExp() {
        return (int) (getLuck() + getIntelligent() * 0.5);
    }

    public double getBlockPower() {
        return getVitality() * 0.5 + getUpgradeLevel(1, 2);
    }

    public int getGold() {
        return gold;
    }

    public boolean getCrit() {
        return crit;
    }

    public int getEnemyLevel() {
        return enemyLevel;
    }

    public void setEnemyLevel(int i) {
        enemyLevel = i;
    }

    public int getSpawnCount() {
        return spawnCount;
    }

    public void setSpawnCount(int i) {
        spawnCount = i;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(int i) {
        spawnTime = i;
        spawnTimeline.stop();
        startSpawnTimeline(this);
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void incEnemyKilled() {
        enemyKilled++;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(getLevel()).append("\n");
        string.append(getSPoints()).append("\n");
        string.append(getStrength()).append("\n");
        string.append(getVitality()).append("\n");
        string.append(getAgility()).append("\n");
        string.append(getIntelligent()).append("\n");
        string.append(getLuck()).append("\n");
        string.append(getExperience()).append("\n");
        string.append(getEnemyLevel()).append("\n");
        string.append(getSpawnCount()).append("\n");
        string.append(getSpawnTime()).append("\n");
        string.append(getEnemyKilled()).append("\n");
        string.append(Arrays.deepToString(getUpgrades())).append("\n");
        string.append(Arrays.deepToString(getUpgradesCost())).append("\n");
        string.append(getGold());
        return string.toString();
    }

    public void addSPoints(int i) {
        this.sPoints += i;
    }
}