package data.Bacteria;

import java.awt.*;

public class Bacteria {
    public float x, y;
    public float rotationX, rotationY;
    public float angleRotation;
    public final float rotationSpeed = 0.2f;
    public float sightDistance = 100.0f;
    public final float directionChangeRate = 0.05f;
    public boolean toBeDeleted;
    public int countChild;
    public float energy;
    public int age;
    public float dirX, dirY;
    public int type;
    public Color color;
    public Bacteria(float x, float y) {
        this.x = x;
        this.y = y;
        rotationX = 0;
        rotationY = 0;
        angleRotation = 0;
        toBeDeleted = false;
        countChild = 0;
        energy = 3f;
        age = 0;
        dirX = dirY = 0.01f;
        type = -1;
        this.color = Color.BLACK;
    }
}
