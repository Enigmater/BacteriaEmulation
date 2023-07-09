package logic;

import data.*;
import data.Bacteria.*;
import gui.parameters.BacteriaParam;
import gui.main.MainPanel;
import gui.map.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Force {
    private static Timer timer;
    private static int TIMER_DELAY = 5;
    private static final float BORDER_SPEED = 4f;
    private static final float BORDER = 50;
    private static float SPEED_BACTERIA = 2f;
    private static float GRAVITY = 5;
    private static float RADIUS_INTERACTION = 100;
    private static int frameFood = 0;
    private static int frameAge = 0;

    // every time in period age++
    private static int TIME_PERIOD = 300;
    private static int SPAWNRATE_FOOD = 5;
    private static int MAX_FOOD_ON_SCREEN = 50;
    private static final float FOOD_FOR_AGE = 0.5f;
    public static void update() {
        // get count red bacteria
        int countRed = BacteriaParam.START_COUNT_RED;
        try {
            countRed = MainPanel.bacteriaParamPanel.getCountRed();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // generate red
        clearBacteria(MapPanel.redBactList);
        generateBacteria(MapPanel.redBactList, countRed, 0);

        // get count yellow bacteria
        int countYellow = BacteriaParam.START_COUNT_YELLOW;
        try {
            countYellow = MainPanel.bacteriaParamPanel.getCountYellow();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // generate yellow
        clearBacteria(MapPanel.yellowBactList);
        generateBacteria(MapPanel.yellowBactList, countYellow, 1);

        clearBacteria(MapPanel.blueBactList);
        clearBacteria(MapPanel.greenBactList);

        clearFood(MapPanel.foodList);
    }
    public static void start() {
        if (timer == null) {
            timer = new Timer(TIMER_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setParameters();
                    setFrame();
                    spawnFood();
                    actionPerFrame();
                    // repaint map
                    MainPanel.mapPanel.repaint();
                }
            });
        }
        timer.start();
    }
    private static void actionPerFrame() {
        // get bacteria list
        ArrayList<Bacteria> redList = MapPanel.redBactList;
        ArrayList<Bacteria> yellowList = MapPanel.yellowBactList;
        ArrayList<Bacteria> blueList = MapPanel.blueBactList;
        actionForBacteria(redList);
        actionForBacteria(yellowList);
        actionForBacteria(blueList);
        // attractive and repulsion for bacteria
        gravityRule(yellowList, yellowList, GRAVITY * 0.01f);
        gravityRule(yellowList, MapPanel.greenBactList, GRAVITY * -0.1f);
        gravityRule(blueList, MapPanel.greenBactList, GRAVITY * -0.01f);
        gravityRule(redList, redList, GRAVITY * 0.01f);
        gravityRule(blueList, yellowList, GRAVITY * -0.02f);
        deleteFood();
    }
    private static void actionForBacteria(ArrayList<Bacteria> bact) {
        if (bact.size() == 0) return;
        die(bact);
        for (Bacteria a : bact) {
            if (a.type == 0) foodSearch_pred(a);
            else foodSearch_prey(a);
            bacteriaMove(a);
        }
        heal(bact);
        aging(bact);
        deleteBacteria(bact);       // delete bacteria if object.toBeDeleted == true
        reproduction(bact);
    }
    public static void pause() {
        timer.stop();
    }
    public static void stop() {
        timer.stop();
        update();
    }
    private static void generateBacteria(ArrayList<Bacteria> bacteria, int countBacteria, int type){
        for (int i = 0; i < countBacteria; i++) {
            // generate coordinate
            float x = (float) (Math.random() * MapPanel.WIDTH) + 25;
            float y = (float) (Math.random() * MapPanel.HEIGHT) + 25;
            // add bacteria in list
            if (type == 0) {
                bacteria.add(new RedBact(x, y));
            }
            else if (type == 1) {
                bacteria.add(new YellowBact(x, y));
            }
            else if (type == 2) {
                bacteria.add(new BlueBact(x, y));
            }
        }
    }
    private static void bacteriaMove(Bacteria a) {
        a.x += a.dirX;
        a.y += a.dirY;
        borderRepulsion(a);
        rotation(a);
    }
    // attraction and repulsion for bacteria
    // F = G * 1 / r
    private static void gravityRule(ArrayList<Bacteria> bact1, ArrayList<Bacteria> bact2, float G) {
        for (int i = 0; i < bact1.size(); i++) {
            float fx = 0;
            float fy = 0;
            Bacteria a = bact1.get(i);
            for (int j = 0; j < bact2.size(); j++) {
                if (i != j) {
                    Bacteria b = bact2.get(j);
                    // find radius between a & b
                    float rx = a.x - b.x;
                    float ry = a.y - b.y;
                    float r = (float) (Math.sqrt(rx * rx + ry * ry));
                    if (r > 0 && r < RADIUS_INTERACTION) {
                        float F = G / r;
                        fx += F * rx;
                        fy += F * ry;
                    }
                }
            }
            a.dirX = (a.dirX + fx) * 0.5f;
            a.dirY = (a.dirY + fy) * 0.5f;
            a.x += a.dirX;
            a.y += a.dirY;

            borderRepulsion(a);
        }
    }
    private static void rotation(Bacteria a) {
        // find rotation
        double targetAngle = Math.atan2(a.rotationY, a.rotationX);
        if (targetAngle < 0) {
            targetAngle = targetAngle + (float) (Math.PI * 2.0);
        }
        if ((Math.abs(a.angleRotation - targetAngle) < a.rotationSpeed) || (Math.abs(a.angleRotation - targetAngle) > Math.PI * 2 - a.rotationSpeed)) {
            a.angleRotation = (float)targetAngle;
        }
        else if (((a.angleRotation < targetAngle) && (a.angleRotation + Math.PI > targetAngle)) || ((a.angleRotation > targetAngle) && (a.angleRotation - Math.PI > targetAngle))) {
            a.angleRotation += a.rotationSpeed;
        }
        else {
            a.angleRotation -= a.rotationSpeed;
        }
        if (a.angleRotation < 0)
            a.angleRotation += (float)(Math.PI * 2.0);
        else if (a.angleRotation > Math.PI * 2.0)
            a.angleRotation -= (float)(Math.PI * 2.0);
        // change speed bacteria
        if(a.rotationX * a.rotationX + a.rotationY * a.rotationY > 1) {
            float acs = 1f;
            if (a.type == 0) acs = 1.2f;
            a.dirX += (float)Math.cos(a.angleRotation) * SPEED_BACTERIA * acs;
            a.dirY += (float)Math.sin(a.angleRotation) * SPEED_BACTERIA * acs;
        }
    }
    private static void borderRepulsion(Bacteria a) {
        int mapPanelWidth = MainPanel.mapPanel.getWidth();
        int mapPanelHeight = MainPanel.mapPanel.getHeight();
        // border repulsion
        if (a.x < BORDER) {
            a.dirX += BORDER_SPEED * 0.05f;
            if (a.x < 0) {
                a.x = -a.x + MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }
        else if (a.x > mapPanelWidth - BORDER) {
            a.dirX -= BORDER_SPEED * 0.05f;
            if (a.x > mapPanelWidth) {
                a.x = mapPanelWidth * 2 - a.x - MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }

        if (a.y < BORDER) {
            a.dirY += BORDER_SPEED * 0.05f;
            if(a.y < 0) {
                a.y = -a.y + MapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
        else if (a.y > mapPanelHeight - BORDER) {
            a.dirY -= BORDER_SPEED * 0.05f;
            if (a.y > mapPanelHeight) {
                a.y = mapPanelHeight * 2 - a.y - MapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
    }
    // food search - bacteria "a" look for closest food in array "bact"
    private static void foodSearch_pred(Bacteria a) {
        // predator won't eat prey if his 6 > food > 9 or count preys < 6
        if (MapPanel.yellowBactList.size() < 6 && MapPanel.blueBactList.size() < 6) {
            randomRotation(a);
            return;
        }
        if (a.type == 0) {
            Bacteria closestFood = null;
            closestFood = closestBact(a, MapPanel.yellowBactList, closestFood, distBetween(a, closestFood));
            closestFood = closestBact(a, MapPanel.blueBactList, closestFood, distBetween(a, closestFood));
            // if find food - change rotation to food
            if (closestFood != null && closestFood.energy < 3.5f) {
                a.rotationX = closestFood.x - a.x;
                a.rotationY = closestFood.y - a.y;
                int distToEat = MapPanel.RADIUS_BACTERIA * MapPanel.RADIUS_BACTERIA + MapPanel.RADIUS_BACTERIA * MapPanel.RADIUS_BACTERIA;
                if (distBetween(a, closestFood) < distToEat) {
                    float protection = 1f;
                    if (closestFood.type == 2) protection = Math.abs(1 - ((BlueBact) closestFood).protection);
                    ((YellowBact)closestFood).health -= ((RedBact)a).damage * protection;
                    if (((YellowBact)closestFood).health <= 0) {
                        closestFood.toBeDeleted = true;
                        float getFood = closestFood.energy * 0.75f;
                        a.energy += getFood;
                    }
                }
            }
            // else - random rotation
            else {
                randomRotation(a);
            }
        }
    }
    private static Bacteria closestBact(Bacteria a, ArrayList<Bacteria> bact, Bacteria closestBact, float minDist) {
        // find minDist
        for (Bacteria b : bact) {
            if (a == b || b.toBeDeleted) continue;
            float dist2 = distBetween(a, b);
            if (dist2 < a.sightDistance * a.sightDistance && dist2 < minDist ) {
                minDist = dist2;
                closestBact = b;
            }
        }
        return closestBact;
    }
    private static float distBetween(Bacteria a, Bacteria b) {
        if (b == null) return MapPanel.WIDTH * MapPanel.WIDTH + MapPanel.HEIGHT * MapPanel.HEIGHT;
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    private static void randomRotation(Bacteria a) {
        if (Math.random() < a.directionChangeRate) {
            double randomAngle = Math.random() * Math.PI * 2;
            a.rotationX = (float) Math.cos(randomAngle) * 2;
            a.rotationY = (float) Math.sin(randomAngle) * 2;
        }
    }
    private static void foodSearch_prey(Bacteria a) {
        if (MapPanel.foodList.size() == 0) return;
        int width = MainPanel.mapPanel.getWidth();
        int height = MainPanel.mapPanel.getHeight();
        float minFoodDist = (width * width) + (height * height);
        if (a.type != 0) {
            Food closestFood = null;
            // find min food distance
            for (Food f : MapPanel.foodList) {
                if (f.toBeDeleted) continue;
                float dist2 = (a.x - f.x) * (a.x - f.x) + (a.y - f.y) * (a.y - f.y);
                if(dist2 < a.sightDistance * a.sightDistance) {
                    if (dist2 < minFoodDist) {
                        minFoodDist = dist2;
                        closestFood = f;
                    }
                }
            }
            // if closestFood != 0, set rotation speed to food
            if (closestFood != null) {
                a.rotationX = closestFood.x - a.x;
                a.rotationY = closestFood.y - a.y;
                int distToEat = MapPanel.FOOD_RADIUS * MapPanel.FOOD_RADIUS + MapPanel.FOOD_RADIUS * MapPanel.FOOD_RADIUS;
                if (minFoodDist < distToEat) {
                    closestFood.toBeDeleted = true;
                    a.energy++;
                }
            }
            // else set random rotation speed
            else {
                randomRotation(a);
            }
        }
    }
    private static void spawnFood() {
        if (MapPanel.foodList.size() < MAX_FOOD_ON_SCREEN && frameFood == SPAWNRATE_FOOD) {
            Food fd = new Food((float)(Math.random() * (MapPanel.WIDTH - 100) + 50), (float)(Math.random() * (MapPanel.HEIGHT - 100) + 50));
            MapPanel.foodList.add(fd);
        }
    }
    private static void reproduction(ArrayList<Bacteria> bact) {
        for (int i = 0; i < bact.size(); i++) {
            Bacteria a = bact.get(i);
            int maxCountChild = 5;
            if (a.energy >= 6f && a.countChild < maxCountChild) {
                a.energy -= 3;
                a.countChild++;
                int type = a.type;
                if (Math.random() < 0.05f) {
                    type = (int) (Math.random() * 4);
                }
                if (type == 0) {
                    RedBact b = new RedBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5);
                    if (Math.random() > 0.4f) {
                        if (b.damage < RedBact.startDamage + 20) b.damage++;
                    }
                    else {
                        if (b.damage > RedBact.startDamage) b.damage--;
                    }
                    if (a.countChild == 5) a.color = new Color(139,0,0);
                    bact.set(i, a);
                    MapPanel.redBactList.add(b);
                }
                else if (type == 1) {
                    YellowBact b = new YellowBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5);
                    if (Math.random() > 0.4f) {
                        if (b.health < 200) b.health += 10;
                    }
                    else {
                        if (b.health > 50) b.health -= 10;
                    }
                    if (a.countChild == 5) a.color = new Color(255,186,0);
                    bact.set(i, a);
                    MapPanel.yellowBactList.add(b);
                }
                else if (type == 2) {
                    BlueBact b = new BlueBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5);
                    if (Math.random() > 0.4f) {
                        if (b.protection < 0.8f) b.protection += 0.05f;
                    }
                    else {
                        if (b.protection > 0.2f) b.protection -= 0.05f;
                    }
                    if (a.countChild == 5) a.color = new Color(0,0,139);
                    bact.set(i, a);
                    MapPanel.blueBactList.add(b);
                }
                else if (type == 3 && MapPanel.greenBactList.size() < 10) {
                    float distForSpawnGreen = GreenBact.healDistance * GreenBact.healDistance;
                    boolean canSpawn = true;
                    for (Bacteria gr : MapPanel.greenBactList){
                        if (distBetween(a, gr) < distForSpawnGreen) {
                            canSpawn = false;
                            break;
                        }
                    }
                    if (canSpawn) {
                        Bacteria b = new GreenBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5);
                        MapPanel.greenBactList.add(b);
                    }
                }
            }
        }
    }
    private static void die(ArrayList<Bacteria> bact) {
        for (Bacteria a : bact) {
            if (a.age >= 80 || a.energy <= 0) a.toBeDeleted = true;
        }
    }
    private static void aging(ArrayList<Bacteria> bact) {
        if (frameAge == TIME_PERIOD) {
            for (Bacteria a : bact) {
                a.age++;
                a.energy -= FOOD_FOR_AGE;
            }
        }
    }
    private static void heal(ArrayList<Bacteria> bact) {
        float minDist = MapPanel.WIDTH * MapPanel.WIDTH + MapPanel.HEIGHT * MapPanel.HEIGHT;
        for (Bacteria a : bact) {
            if (a.type != 0) {
                Bacteria closestGreenBact = null;
                closestGreenBact = closestBact(a, MapPanel.greenBactList, closestGreenBact, minDist);
                if (distBetween(a, closestGreenBact) < GreenBact.healDistance && ((YellowBact)a).health < YellowBact.startHealth) {
                    ((YellowBact)a).health += GreenBact.healPerTime;
                }
            }
        }
    }
    private static void clearBacteria(ArrayList<Bacteria> bacteria) {
        bacteria.clear();
    }
    private static void deleteBacteria(ArrayList<Bacteria> bacteria) {
        for (int i = 0; i < bacteria.size(); i++) {
            Bacteria bact = bacteria.get(i);
            if (bact.toBeDeleted) {
                bacteria.remove(i--);
            }
        }
    }
    private static void clearFood(ArrayList<Food> food) {
        food.clear();
    }
    private static void deleteFood() {
        for (int i = 0; i < MapPanel.foodList.size(); i++) {
            Food fd = MapPanel.foodList.get(i);
            if (fd.toBeDeleted) {
                MapPanel.foodList.remove(i--);
            }
        }
    }
    private static void setFrame() {
        frameFood++;
        if (frameFood == SPAWNRATE_FOOD + 1) frameFood = 0;

        frameAge++;
        if (frameAge == TIME_PERIOD + 1) frameAge = 0;
    }
    private static void setParameters() {
        setGravity();
        setRadiusInteraction();
        setMaxCountFood();
        setSpawnrateFood();
        setBacteriaSpeed();
        setTimerDelay();
        setTimePeriod();
        //
        setRedDmg();
        setPreysHealth();
        setBlueProtection();
        setGreenHeal();
    }

    private static void setGreenHeal() {
        GreenBact.healPerTime = MainPanel.bacteriaParamPanel.getHeal();
    }
    private static void setBlueProtection() {
        BlueBact.startProtection = MainPanel.bacteriaParamPanel.getProtection();
        for (Bacteria b : MapPanel.blueBactList)
            ((BlueBact)b).protection = BlueBact.startProtection;
    }
    private static void setPreysHealth() {
        YellowBact.startHealth = MainPanel.bacteriaParamPanel.getHealth();
        for (Bacteria b : MapPanel.yellowBactList)
            if (((YellowBact)b).health > YellowBact.startHealth) ((YellowBact)b).health = YellowBact.startHealth;
        for (Bacteria b : MapPanel.blueBactList)
            if (((BlueBact)b).health > YellowBact.startHealth) ((BlueBact)b).health = YellowBact.startHealth;
    }
    private static void setRedDmg() {
        RedBact.startDamage = MainPanel.bacteriaParamPanel.getDmg();
        for (Bacteria a : MapPanel.redBactList)
            ((RedBact)a).damage = RedBact.startDamage;
    }
    private static void setBacteriaSpeed() {
        SPEED_BACTERIA = MainPanel.bacteriaParamPanel.getSpeed();
    }
    private static void setMaxCountFood() {
        MAX_FOOD_ON_SCREEN = MainPanel.foodParamPanel.getMaxcountFood();
    }
    private static void setSpawnrateFood() {
        SPAWNRATE_FOOD = MainPanel.foodParamPanel.getSpawnrate();
        if (frameFood > SPAWNRATE_FOOD) frameFood = SPAWNRATE_FOOD;
    }
    private static void setRadiusInteraction() {
        RADIUS_INTERACTION = MainPanel.physParamPanel.getRadiusInteractionValue();
    }
    private static void setGravity() {
        GRAVITY = MainPanel.physParamPanel.getGravityValue();
    }
    private static void setTimerDelay() {
        TIMER_DELAY = MainPanel.controlPanel.getTimerDelay();
        timer.setDelay(TIMER_DELAY);
    }
    private static void setTimePeriod() {
        TIME_PERIOD = MainPanel.bacteriaParamPanel.getAgingTime();
        if (frameAge > TIME_PERIOD) frameAge = TIME_PERIOD;
    }
}
