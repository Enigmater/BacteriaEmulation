package data;

import java.awt.*;

public class RedBact extends Bacteria {
    public float damage;
    public RedBact(float x, float y){
        super(x, y);
        this.color = Color.RED;
        damage = 10;
        type = 0;
    }
}
