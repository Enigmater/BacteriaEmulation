package data;

import java.awt.*;

public class Bacteria {
    // x, y - coordinate bacteria
    public float x;
    public float y;
    //
    public float rotationX;
    public float rotationY;
    public float angleRotation;
    public float rotationSpeed = 0.2f;
    public float sightDistance = 100.0f;
    public float directionChangeRate = 0.05f;
    public boolean toBeDeleted;
    public float food;
    public float saturation;
    public int age;
    // dirX, dirY - velocity for OX & OY
    public float dirX;
    public float dirY;
    // type = 0 - predator
    // type = 1 - prey
    // type = -1 - nobody
    public int type;
    public Color color;
    public Bacteria(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        //
        rotationX = 0;
        rotationY = 0;
        angleRotation = 0;
        toBeDeleted = false;
        food = 3f;
        saturation = 100;
        age = 0;
        //
        dirX = dirY = 0.01f;
        type = -1;
        this.color = color;
    }
}
