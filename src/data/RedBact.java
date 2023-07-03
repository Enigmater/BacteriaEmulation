package data;

import java.awt.*;

public class RedBact extends Bacteria {
    public float saturation;
    public float damage;
    public RedBact(float x, float y){
        super(x, y);
        this.color = Color.RED;
        saturation = 100;
        damage = 5;
        type = 0;
    }


}
