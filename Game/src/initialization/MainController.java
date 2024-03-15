package initialization;

import characters.Enemy;
import characters.MainCharacter;
import draw.Drawer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.Main;

import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class MainController {
    //Level Ups
    @FXML
    public Label levelCurrent;
    @FXML
    public Label statPointsCurrent;
    @FXML
    public Label upgradeStrengthLevel;
    @FXML
    public Label upgradeVitalityLevel;
    @FXML
    public Label upgradeAgilityLevel;
    @FXML
    public Label upgradeIntelligentLevel;
    @FXML
    public Label upgradeLuckLevel;
    @FXML
    public Button upgradeStrength;
    @FXML
    public Button upgradeVitality;
    @FXML
    public Button upgradeAgility;
    @FXML
    public Button upgradeIntelligent;
    @FXML
    public Button upgradeLuck;
    @FXML
    public Label upgradeStrengthCost;
    @FXML
    public Label upgradeVitalityCost;
    @FXML
    public Label upgradeAgilityCost;
    @FXML
    public Label upgradeIntelligentCost;
    @FXML
    public Label upgradeLuckCost;
    //End Level Ups
    //Stats
    @FXML
    public Label APCurrent;
    @FXML
    public Label CCCurrent;
    @FXML
    public Label SCurrent;
    @FXML
    public Label HCurrent;
    @FXML
    public Label BCCurrent;
    @FXML
    public Label SPCurrent;
    @FXML
    public Label BPCurrent;
    @FXML
    public Label BECurrent;
    @FXML
    public Label ASCurrent;
    @FXML
    public Label CPCurrent;
    @FXML
    public Label ACurrent;
    @FXML
    public Label HRCurrent;
    @FXML
    public Label DCCurrent;
    @FXML
    public Label SSCurrent;
    @FXML
    public Label BGCurrent;
    //End Stats
    //Upgrades
    @FXML
    public Button upgradeAP;
    @FXML
    public Label upgradeAPLevel;
    @FXML
    public Label upgradeAPCurrent;
    @FXML
    public Label upgradeAPCost;
    @FXML
    public Button upgradeAS;
    @FXML
    public Label upgradeASLevel;
    @FXML
    public Label upgradeASCurrent;
    @FXML
    public Label upgradeASCost;
    @FXML
    public Button upgradeCC;
    @FXML
    public Label upgradeCCLevel;
    @FXML
    public Label upgradeCCCurrent;
    @FXML
    public Label upgradeCCCost;
    @FXML
    public Button upgradeCP;
    @FXML
    public Label upgradeCPLevel;
    @FXML
    public Label upgradeCPCurrent;
    @FXML
    public Label upgradeCPCost;
    @FXML
    public Button upgradeS;
    @FXML
    public Label upgradeSLevel;
    @FXML
    public Label upgradeSCurrent;
    @FXML
    public Label upgradeSCost;
    @FXML
    public Button upgradeA;
    @FXML
    public Label upgradeALevel;
    @FXML
    public Label upgradeACurrent;
    @FXML
    public Label upgradeACost;
    @FXML
    public Button upgradeH;
    @FXML
    public Label upgradeHLevel;
    @FXML
    public Label upgradeHCurrent;
    @FXML
    public Label upgradeHCost;
    @FXML
    public Button upgradeHR;
    @FXML
    public Label upgradeHRLevel;
    @FXML
    public Label upgradeHRCurrent;
    @FXML
    public Label upgradeHRCost;
    @FXML
    public Button upgradeBC;
    @FXML
    public Label upgradeBCLevel;
    @FXML
    public Label upgradeBCCurrent;
    @FXML
    public Label upgradeBCCost;
    @FXML
    public Button upgradeDC;
    @FXML
    public Label upgradeDCLevel;
    @FXML
    public Label upgradeDCCurrent;
    @FXML
    public Label upgradeDCCost;
    @FXML
    public Button upgradeSS;
    @FXML
    public Label upgradeSSLevel;
    @FXML
    public Label upgradeSSCurrent;
    @FXML
    public Label upgradeSSCost;
    @FXML
    public Button upgradeSP;
    @FXML
    public Label upgradeSPLevel;
    @FXML
    public Label upgradeSPCurrent;
    @FXML
    public Label upgradeSPCost;
    //End Upgrades
    @FXML
    public Group battleFieldGroup = new Group();
    @FXML
    public ProgressBar expBar;
    @FXML
    public ProgressBar healthBar;
    @FXML
    public Label goldLabel = new Label();
    @FXML
    public Spinner<Integer> enemyLevelSpinner = new Spinner<>();
    @FXML
    public Spinner<Integer> spawnTimeSpinner = new Spinner<>();
    @FXML
    public Spinner<Integer> spawnCountSpinner = new Spinner<>();
    @FXML
    public Label enemyKilledLabel;

    @FXML
    public Button saveButton;
    @FXML
    public Button loadButton;
    @FXML
    public Button cheatsButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button exitBarButton;
    @FXML
    public Button resetButton;

    @FXML
    public Slider gameSpeedSlider;

    private MainCharacter mainCharacter;

    public MainController() {
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public MainCharacter getMainCharacter() {
        return this.mainCharacter;
    }

    @FXML
    public void redrawBattlefield(Group enemyGroup) {
        battleFieldGroup.getChildren().clear();
        battleFieldGroup.getChildren().add(Drawer.drawMainCharacter(mainCharacter));
        battleFieldGroup.getChildren().add(Drawer.drawMainAttackRadius(mainCharacter));
        battleFieldGroup.getChildren().add(enemyGroup);
        enemyKilledLabel.setText(Drawer.convert(mainCharacter.getEnemyKilled()));
        goldLabel.setText(Drawer.convert(mainCharacter.getGold()));
    }

    private void redrawSpawn() {
        spawnTimeSpinner.getValueFactory().setValue(mainCharacter.getSpawnTime());
        enemyLevelSpinner.getValueFactory().setValue(mainCharacter.getEnemyLevel());
        spawnCountSpinner.getValueFactory().setValue(mainCharacter.getSpawnCount());
    }

    public void initialize() {
        upgradeStrength.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeStrengthClicked());
        upgradeVitality.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeVitalityClicked());
        upgradeAgility.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeAgilityClicked());
        upgradeIntelligent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeIntelligentClicked());
        upgradeLuck.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeLuckClicked());
        upgradeAP.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeAPClicked());
        upgradeAS.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeASClicked());
        upgradeCC.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeCCClicked());
        upgradeCP.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeCPClicked());
        upgradeS.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeSClicked());
        upgradeA.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeAClicked());
        upgradeH.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeHClicked());
        upgradeHR.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeHRClicked());
        upgradeBC.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeBCClicked());
        upgradeDC.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeDCClicked());
        upgradeSP.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeSPClicked());
        upgradeSS.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> upgradeSSClicked());
        spawnTimeSpinner.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> spawnTimeSpinnerClicked());
        spawnCountSpinner.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> spawnCountSpinnerClicked());
        enemyLevelSpinner.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> enemyLevelSpinnerClicked());
        redrawSpawn();
        redrawLevel();
        redrawStats();
        redrawUpgrades();
        redrawBattlefield(Drawer.drawBattleField());
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> saveDialog());
        loadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> loadDialog());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.exit());
        exitBarButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.exit());
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Drawer.restartBattle(mainCharacter));
        cheatsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> CheatsController.startCheats(this));
        gameSpeedSlider.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> Drawer.restartBattle(mainCharacter));
    }

    public double getSpeed() {
        if (this.gameSpeedSlider != null) {
            return this.gameSpeedSlider.getValue();
        } else {
            return 1;
        }
    }

    private void enemyLevelSpinnerClicked() {
        mainCharacter.setEnemyLevel(enemyLevelSpinner.getValue());
    }

    private void spawnCountSpinnerClicked() {
        mainCharacter.setSpawnCount(spawnCountSpinner.getValue());
    }

    private void spawnTimeSpinnerClicked() {
        mainCharacter.setSpawnTime(spawnTimeSpinner.getValue());
    }

    private void loadDialog() {
        mainCharacter = loadMaincharacter();
        redrawLevel();
        redrawUpgrades();
        redrawStats();
        redrawSpawn();
    }

    public MainCharacter loadMaincharacter() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load");
        fc.setInitialDirectory(new File("C:\\Users\\Торн\\OneDrive\\Курсач\\5 семестр (Графика)\\Game"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fc.setInitialFileName("save.txt");
        File file = fc.showOpenDialog(Main.stage);
        if (file != null) {
            try {
                FileReader fr = new FileReader(file);
                char[] buffer = new char[256];
                StringBuilder string = new StringBuilder();
                while (fr.ready()) {
                    fr.read(buffer);
                }
                for (char c : buffer) {
                    if (c != '\u0000') {
                        string.append(c);
                    }
                }
                fr.close();
                return new MainCharacter(string.toString());
            } catch (FileNotFoundException ex) {
                System.out.println("Ошибка чтения из файла!");
            } catch (IOException e) {
                System.out.println("Ошибка потоков чтения!");
            }
        }
        System.out.println("Ошибка считывания персонажа, создан новый!");
        return new MainCharacter();
    }

    private void saveDialog() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.setInitialDirectory(new File("C:\\Users\\Торн\\OneDrive\\Курсач\\5 семестр (Графика)\\Game"));
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fc.setInitialFileName("save.txt");
        File file = fc.showSaveDialog(Main.stage);
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.write(mainCharacter.toString());
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл!");
            }
        }
    }

    @FXML
    public void addEnemy() {
        for (int i = 0; i < mainCharacter.getSpawnCount(); i++) {
            Random r = new Random();
            Drawer.addEnemy(r.nextInt(738), r.nextInt(2) * 718, mainCharacter);
        }
        redrawSpawn();
        redrawBattlefield(Drawer.drawBattleField());
    }

    @FXML
    public void move() {
        try {
            for (Enemy en : Drawer.getEnemyList()) {
                en.moveEnemy(mainCharacter);
            }
        } catch (ConcurrentModificationException ex) {
            System.out.println("Ошибка потоков!");
        }
        mainCharacter.moveMain(Drawer.getEnemyList());
        redrawBattlefield(Drawer.drawBattleField());
        redrawLevel();
    }

    @FXML
    public void upgradeStrengthClicked() {
        if (mainCharacter.incStrength()) {
            redrawLevel();
            redrawStats();
        }
    }

    @FXML
    public void upgradeVitalityClicked() {
        if (mainCharacter.incVitality()) {
            redrawLevel();
            redrawStats();
        }
    }

    @FXML
    public void upgradeAgilityClicked() {
        if (mainCharacter.incAgility()) {
            redrawLevel();
            redrawStats();
            Main.startAttackTimeline(mainCharacter);
        }
    }

    @FXML
    public void upgradeIntelligentClicked() {
        if (mainCharacter.incIntelligent()) {
            redrawLevel();
            redrawStats();
        }
    }

    @FXML
    public void upgradeLuckClicked() {
        if (mainCharacter.incLuck()) {
            redrawLevel();
            redrawStats();
        }
    }

    @FXML
    public void upgradeAPClicked() {
        if (mainCharacter.incUpgrade(0, 0)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeASClicked() {
        if (mainCharacter.incUpgrade(1, 0)) {
            redrawStats();
            redrawUpgrades();
            Main.startAttackTimeline(mainCharacter);
        }
    }

    @FXML
    public void upgradeCCClicked() {
        if (mainCharacter.incUpgrade(0, 1)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeCPClicked() {
        if (mainCharacter.incUpgrade(1, 1)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeSClicked() {
        if (mainCharacter.incUpgrade(0, 2)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeAClicked() {
        if (mainCharacter.incUpgrade(1, 2)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeHClicked() {
        if (mainCharacter.incUpgrade(0, 3)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeHRClicked() {
        if (mainCharacter.incUpgrade(1, 3)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeBCClicked() {
        if (mainCharacter.incUpgrade(0, 4)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeDCClicked() {
        if (mainCharacter.incUpgrade(1, 4)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeSPClicked() {
        if (mainCharacter.incUpgrade(0, 5)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void upgradeSSClicked() {
        if (mainCharacter.incUpgrade(1, 5)) {
            redrawStats();
            redrawUpgrades();
        }
    }

    @FXML
    public void updateBars() {
        healthBar.setProgress((double) mainCharacter.getHp() / mainCharacter.getHealth());
        expBar.setProgress((double) mainCharacter.getExperience() / mainCharacter.getLevelCost());
    }


    private void redrawLevel() {
        levelCurrent.setText("Level " + this.mainCharacter.getLevel());
        statPointsCurrent.setText(Drawer.convert(this.mainCharacter.getSPoints()));
        upgradeStrengthLevel.setText(Drawer.convert(mainCharacter.getStrength()));
        upgradeStrengthCost.setText(Drawer.convert(mainCharacter.getStrengthCost()));
        upgradeVitalityLevel.setText(Drawer.convert(mainCharacter.getVitality()));
        upgradeVitalityCost.setText(Drawer.convert(mainCharacter.getVitalityCost()));
        upgradeAgilityLevel.setText(Drawer.convert(mainCharacter.getAgility()));
        upgradeAgilityCost.setText(Drawer.convert(mainCharacter.getAgilityCost()));
        upgradeIntelligentLevel.setText(Drawer.convert(mainCharacter.getIntelligent()));
        upgradeIntelligentCost.setText(Drawer.convert(mainCharacter.getIntelligentCost()));
        upgradeLuckLevel.setText(Drawer.convert(mainCharacter.getLuck()));
        upgradeLuckCost.setText(Drawer.convert(mainCharacter.getLuckCost()));
    }

    private void redrawStats() {
        upgradeAPCurrent.setText(Drawer.convert(mainCharacter.getAttackPower()));
        APCurrent.setText(Drawer.convert(mainCharacter.getAttackPower()));
        upgradeASCurrent.setText(Drawer.convert(mainCharacter.getAttackSpeed()) + "%");
        ASCurrent.setText(Drawer.convert(mainCharacter.getAttackSpeed()) + "%");
        upgradeCCCurrent.setText(Drawer.convert(mainCharacter.getCritChance()) + "%");
        CCCurrent.setText(Drawer.convert(mainCharacter.getCritChance()) + "%");
        upgradeCPCurrent.setText(Drawer.convert(mainCharacter.getCritPower()) + "%");
        CPCurrent.setText(Drawer.convert(mainCharacter.getCritPower()) + "%");
        upgradeSCurrent.setText(Drawer.convert(mainCharacter.getSpeed()) + "%");
        SCurrent.setText(Drawer.convert(mainCharacter.getSpeed()) + "%");
        upgradeACurrent.setText(Drawer.convert(mainCharacter.getArmor()));
        ACurrent.setText(Drawer.convert(mainCharacter.getArmor()));
        upgradeHCurrent.setText(Drawer.convert(mainCharacter.getHealth()));
        HCurrent.setText(Drawer.convert(mainCharacter.getHealth()));
        upgradeHRCurrent.setText(Drawer.convert(mainCharacter.getHealthRegeneration()) + "%");
        HRCurrent.setText(Drawer.convert(mainCharacter.getHealthRegeneration()) + "%");
        upgradeBCCurrent.setText(Drawer.convert(mainCharacter.getBlockChance()) + "%");
        BCCurrent.setText(Drawer.convert(mainCharacter.getBlockChance()) + "%");
        upgradeDCCurrent.setText(Drawer.convert(mainCharacter.getDodgeChance()) + "%");
        DCCurrent.setText(Drawer.convert(mainCharacter.getDodgeChance()) + "%");
        upgradeSPCurrent.setText(Drawer.convert(mainCharacter.getSkillPower()));
        SPCurrent.setText(Drawer.convert(mainCharacter.getSkillPower()));
        upgradeSSCurrent.setText(Drawer.convert(mainCharacter.getSkillSpeed()) + "%");
        SSCurrent.setText(Drawer.convert(mainCharacter.getSkillSpeed()) + "%");
        BGCurrent.setText(Drawer.convert(mainCharacter.getBonusGold()));
        BECurrent.setText(Drawer.convert(mainCharacter.getBonusExp()));
        BPCurrent.setText(Drawer.convert(mainCharacter.getBlockPower()));
    }

    private void redrawUpgrades() {
        upgradeAPLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 0)));
        upgradeAPCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 0)));
        upgradeASLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 0)));
        upgradeASCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 0)));
        upgradeCCLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 1)));
        upgradeCCCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 1)));
        upgradeCPLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 1)));
        upgradeCPCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 1)));
        upgradeSLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 2)));
        upgradeSCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 2)));
        upgradeALevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 2)));
        upgradeACost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 2)));
        upgradeHLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 3)));
        upgradeHCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 3)));
        upgradeHRLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 3)));
        upgradeHRCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 3)));
        upgradeBCLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 4)));
        upgradeBCCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 4)));
        upgradeDCLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 4)));
        upgradeDCCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 4)));
        upgradeSPLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(0, 5)));
        upgradeSPCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(0, 5)));
        upgradeSSLevel.setText(Drawer.convert(mainCharacter.getUpgradeLevel(1, 5)));
        upgradeSSCost.setText(Drawer.convert(mainCharacter.getUpgradeCost(1, 5)));
    }

    public void updateMain() {
        mainCharacter.healthRegen();
    }
}
