package data;

import java.awt.*;

public class BlueBact extends YellowBact {
    public float protection;
    public BlueBact(float x, float y) {
        super(x, y);
        this.color = Color.BLUE;
        type = 2;
        protection = 0.5f;
    }
}
