package data.Bacteria;

import data.Bacteria.Bacteria;

import java.awt.*;

public class RedBact extends Bacteria {
    public static float startDamage = 7.5f;
    public float damage;
    public RedBact(float x, float y){
        super(x, y);
        this.color = Color.RED;
        damage = startDamage;
        type = 0;
    }
}
