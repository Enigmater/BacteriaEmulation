package data.Bacteria;

import java.awt.*;
public class BlueBact extends YellowBact {
    public static float startProtection = 0.5f;
    public float protection;
    public BlueBact(float x, float y) {
        super(x, y);
        this.color = Color.BLUE;
        type = 2;
        protection = startProtection;
    }
}
