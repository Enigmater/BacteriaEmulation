package data.Bacteria;

import data.Bacteria.Bacteria;

import java.awt.*;

public class GreenBact extends Bacteria {
    public static int healPerTime = 10;
    public static final int healDistance = 200;
    public GreenBact(float x, float y) {
        super(x, y);
        color = Color.GREEN;
        type = 3;
    }
}
