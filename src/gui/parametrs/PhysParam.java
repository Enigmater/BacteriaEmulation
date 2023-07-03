package gui.parametrs;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PhysParam extends JPanel {

    private static JLabel gravityTitle = new JLabel("Gravity");
    private static JPanel gravityPanel;
    private static JSlider gravitySlider;
    private static JLabel gravityValue;

    private static JLabel radiusInteractionTitle = new JLabel("Radius");
    private static JPanel radiusInteractionPanel;
    private static JSlider radiusInteractionSlider;
    private static JLabel radiusInteractionValue;

    public PhysParam() {
        setupView();
        setupSliders();
        setVisible(true);
    }
    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Physical Parameters"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }

    private void setupSliders() {
        // setup gravity block
        this.add(gravityTitle);
        setupGravityPanel();
        // setup radius interaction block
        this.add(radiusInteractionTitle);
        setupRadiusInteractionPanel();
    }

    private void setupGravityPanel() {
        gravityPanel = new JPanel();
        setupGravitySlider();
        setupGravityLabel();
        this.add(gravityPanel);
    }
    private void setupGravitySlider() {
        gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        gravitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gravityValue.setText(Integer.toString(gravitySlider.getValue()));
            }
        });
        gravityPanel.add(gravitySlider);
    }

    private void setupGravityLabel() {
        gravityValue = new JLabel();
        gravityValue.setText(Integer.toString(gravitySlider.getValue()));
        gravityPanel.add(gravityValue);
    }

    private void setupRadiusInteractionPanel() {
        radiusInteractionPanel = new JPanel();
        setupRadiusInteractionSlider();
        setupRadiusInteractLabel();
        this.add(radiusInteractionPanel);
    }
    private void setupRadiusInteractionSlider() {
        radiusInteractionSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 100);
        radiusInteractionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                radiusInteractionValue.setText(Integer.toString(radiusInteractionSlider.getValue()));
            }
        });
        radiusInteractionPanel.add(radiusInteractionSlider);
    }
    private void setupRadiusInteractLabel() {
        radiusInteractionValue = new JLabel();
        radiusInteractionValue.setText(Integer.toString(radiusInteractionSlider.getValue()));
        radiusInteractionPanel.add(radiusInteractionValue);
    }
    public float getGravityValue() {
        return gravitySlider.getValue();
    }

    public float getRadiusInteractionValue() {
        return radiusInteractionSlider.getValue();
    }
}
