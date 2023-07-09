package data.Bacteria;

import data.Bacteria.Bacteria;

import java.awt.*;

public class YellowBact extends Bacteria {
    public static int startHealth = 100;
    public float health;
    public YellowBact(float x, float y){
        super(x, y);
        this.color = Color.YELLOW;
        type = 1;
        health = startHealth;
    }
}
