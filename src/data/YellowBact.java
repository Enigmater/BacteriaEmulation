package data;

import java.awt.*;

public class YellowBact extends Bacteria {
    public float health;
    public YellowBact(float x, float y){
        super(x, y);
        this.color = Color.YELLOW;
        type = 1;
        health = 100;
    }
}
