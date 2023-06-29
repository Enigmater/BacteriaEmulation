package logic;

import data.Bacteria;
import data.Food;
import data.RedBact;
import data.YellowBact;
import gui.control.BacteriaParam;
import gui.main.MainPanel;
import gui.map.MapPanel;
import runner.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Force {
    private static Timer timer;
    private static int TIMER_DELAY = 5;
    private static final float SPEED = 4f;
    private static final float BORDER = 50;
    private static float SPEED_BACTERIA = 2f;
    private static float GRAVITY = 5;
    private static float RADIUS_INTERACTION = 100;
    private static int frame = 0;
    private static int SPAWNRATE_FOOD = 5;
    private static int MAX_FOOD = 50;


    public static void update() {
        // get count red bacteria
        int countRed = BacteriaParam.START_COUNT_RED;
        try {
            countRed = MainPanel.bacteriaParamPanel.getCountRed();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // generate red
        clearBacteria(MapPanel.red);
        generateBacteria(MapPanel.red, countRed, 0, Color.red);

        // get count yellow bacteria
        int countYellow = BacteriaParam.START_COUNT_YELLOW;
        try {
            countYellow = MainPanel.bacteriaParamPanel.getCountYellow();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // generate yellow
        clearBacteria(MapPanel.yellow);
        generateBacteria(MapPanel.yellow, countYellow, 1, Color.yellow);

        clearFood(MapPanel.food);
    }
    public static void start() {
        if (timer == null) {
            timer = new Timer(TIMER_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setParameters();
                    setFrame();
                    spawnFood();
                    // get bacteria lisst
                    ArrayList<Bacteria> redList = MapPanel.red;
                    ArrayList<Bacteria> yellowList = MapPanel.yellow;
                    // action
                    gravityRule(yellowList, redList, GRAVITY);
                    for (Bacteria a : redList) {
                        foodSearch_pred(a, yellowList);
                        bacteriaMove(a);
                    }
                    for (Bacteria a : yellowList) {
                        foodSearch_prey(a);
                        bacteriaMove(a);
                    }
                    deleteBacteria(redList);
                    deleteBacteria(yellowList);
                    deleteFood();
                    reproduction(redList);
                    reproduction(yellowList);
                    // repaint map
                    MainPanel.mapPanel.repaint();
                }
            });
        }
        timer.start();
    }
    public static void pause() {
        timer.stop();
    }
    public static void stop() {
        timer.stop();
        update();
    }
    private static void generateBacteria(ArrayList<Bacteria> bacteria, int countBacteria, int type, Color color){
        for (int i = 0; i < countBacteria; i++) {
            // generate coordinate
            float x = (float) (Math.random() * MapPanel.WIDTH) + 25;
            float y = (float) (Math.random() * MapPanel.HEIGHT) + 25;
            // add bacteria in list
            if (type == 0) {
                bacteria.add(new RedBact(x, y, color));
            }
            else if (type == 1) {
                bacteria.add(new YellowBact(x, y, color));
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
            a.dirX += (float)Math.cos(a.angleRotation) * SPEED_BACTERIA;
            a.dirY += (float)Math.sin(a.angleRotation) * SPEED_BACTERIA;
        }
    }
    private static void borderRepulsion(Bacteria a) {
        int mapPanelWidth = MainPanel.mapPanel.getWidth();
        int mapPanelHeight = MainPanel.mapPanel.getHeight();
        // border repulsion
        if (a.x < BORDER) {
            a.dirX += SPEED * 0.05f;
            if (a.x < 0) {
                a.x = -a.x + MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }
        else if (a.x > mapPanelWidth - BORDER) {
            a.dirX -= SPEED * 0.05f;
            if (a.x > mapPanelWidth) {
                a.x = mapPanelWidth * 2 - a.x - MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }

        if (a.y < BORDER) {
            a.dirY += SPEED * 0.05f;
            if(a.y < 0) {
                a.y = -a.y + MapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
        else if (a.y > mapPanelHeight - BORDER) {
            a.dirY -= SPEED * 0.05f;
            if (a.y > mapPanelHeight) {
                a.y = mapPanelHeight * 2 - a.y - MapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
    }
    // food search - bacteria "a" look for closest food in array "bact"
    private static void foodSearch_pred(Bacteria a, ArrayList<Bacteria> bact) {
        if (bact.size() == 0) return;
        int width = MainPanel.mapPanel.getWidth();
        int height = MainPanel.mapPanel.getHeight();
        float minFoodDist = (width * width) + (height * height);
        if (a.type == 0) {
            Bacteria closestFood = null;
            // find minFoodDist
            for (Bacteria b : bact) {
                if (a == b) continue;
                if (b.toBeDeleted) continue;
                float dist2 = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
                if (dist2 < a.sightDistance * a.sightDistance) {
                    if (dist2 < minFoodDist) {
                        minFoodDist = dist2;
                        closestFood = b;
                    }
                }
            }
            // if find food - change rotation to food
            if (closestFood != null) {
                a.rotationX = closestFood.x - a.x;
                a.rotationY = closestFood.y - a.y;
                if (minFoodDist < MapPanel.RADIUS_BACTERIA * MapPanel.RADIUS_BACTERIA + MapPanel.RADIUS_BACTERIA * MapPanel.RADIUS_BACTERIA) {
                    closestFood.toBeDeleted = true;
                    a.food += closestFood.food * 0.1f;
                }
            }
            // else - random rotation
            else {
                if (Math.random() < a.directionChangeRate) {
                    double randomAngle = Math.random() * Math.PI * 2;
                    a.rotationX = (float) Math.cos(randomAngle) * 2;
                    a.rotationY = (float) Math.sin(randomAngle) * 2;
                }
            }
        }
    }
    private static void foodSearch_prey(Bacteria a) {
        if (MapPanel.food.size() == 0) return;
        int width = MainPanel.mapPanel.getWidth();
        int height = MainPanel.mapPanel.getHeight();
        float minFoodDist = (width * width) + (height * height);
        if (a.type == 1) {
            Food closestFood = null;
            // find min food distance
            for (Food f : MapPanel.food) {
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
                if (minFoodDist < MapPanel.FOOD_RADIUS * MapPanel.FOOD_RADIUS + MapPanel.RADIUS_BACTERIA * MapPanel.RADIUS_BACTERIA) {
                    closestFood.toBeDeleted = true;
                    a.food++;
                }
            }
            // else set random rotation speed
            else {
                if(Math.random() < a.directionChangeRate) {
                    double randomAngle = Math.random() * Math.PI * 2;
                    a.rotationX = (float)Math.cos(randomAngle) * 2;
                    a.rotationY = (float)Math.sin(randomAngle) * 2;
                }
            }
        }
    }
    private static void spawnFood() {
        if (MapPanel.food.size() < MAX_FOOD && frame == SPAWNRATE_FOOD) {
            Food fd = new Food((float)(Math.random() * (MapPanel.WIDTH - 100) + 50), (float)(Math.random() * (MapPanel.HEIGHT - 100) + 50));
            MapPanel.food.add(fd);
        }
    }
    private static void reproduction(ArrayList<Bacteria> bact) {
        for (int i = 0; i < bact.size(); i++) {
            Bacteria a = bact.get(i);
            if (a.food >= 6) {
                a.food -= 3;
                int type = a.type;
                if (Math.random() < 0.05) {
                    type = (int) (Math.random() * 2);
                }
                Bacteria b = new Bacteria(0, 0, Color.BLACK);
                if (type == 0) {
                    b = new RedBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5, Color.red);
                }
                else if (type == 1) {
                    b = new YellowBact(a.x + (float) Math.random() * 10 - 5, a.y + (float) Math.random() * 10 - 5, Color.yellow);
                }
                /*if(Math.random() < 0.5) {
                    if(Math.random() < 0.5) b.speed -= 0.1;
                    else b.speed += 0.1;
                    if(b.speed < 0.1f) b.speed = 0.1f;
                    else if(b.speed > 10f) b.speed = 10f;
                }*/
                bact.add(b);
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
        for (int i = 0; i < MapPanel.food.size(); i++) {
            Food fd = MapPanel.food.get(i);
            if (fd.toBeDeleted) {
                MapPanel.food.remove(i--);
            }
        }
    }
    private static void setFrame() {
        frame++;
        if (frame == SPAWNRATE_FOOD + 1) frame = 0;
    }
    private static void setParameters() {
        setGravity();
        setRadiusInteraction();
        setMaxCountFood();
        setSpawnrateFood();
        setBacteriaSpeed();
        setTimerDelay();
    }
    private static void setBacteriaSpeed() {
        SPEED_BACTERIA = MainPanel.bacteriaParamPanel.getSpeed();
    }
    private static void setMaxCountFood() {
        MAX_FOOD = MainPanel.foodParamPanel.getMaxcountFood();
    }
    private static void setSpawnrateFood() {
        SPAWNRATE_FOOD = MainPanel.foodParamPanel.getSpawnrate();
        if (frame > SPAWNRATE_FOOD) frame = SPAWNRATE_FOOD;
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

}
