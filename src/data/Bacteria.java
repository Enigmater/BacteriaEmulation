package data;

import java.awt.*;

public class Bacteria {
    // x, y - coordinate bacteria
    public float x;
    public float y;
    // dirX, dirY - velocity for OX & OY
    public float dirX;
    public float dirY;
    public Color color;
    public Bacteria(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        dirX = dirY = 0f;
        this.color = color;
    }
}
