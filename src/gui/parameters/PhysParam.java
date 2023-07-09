package gui.parameters;

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

    private static JLabel radiusInteractionTitle = new JLabel("R.Gravity");
    private static JPanel radiusInteractionPanel;
    private static JSlider radiusInteractionSlider;
    private static JLabel radiusInteractionValue;

    private static JLabel radiusBacteriaTitle = new JLabel("R.Bacteria");
    private static JPanel radiusBacteriaPanel;
    private static JSlider radiusBacteriaSlider;
    private static JLabel radiusBacteriaValue;

    private static JLabel radiusFoodTitle = new JLabel("R.Food");
    private static JPanel radiusFoodPanel;
    private static JSlider radiusFoodSlider;
    private static JLabel radiusFoodValue;

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
        setupGravityPanel();
        // setup radius interaction block
        setupRadiusInteractionPanel();
        setupRadiusBacteriaPanel();
        setupRadiusFoodPanel();
    }
    private void setupRadiusBacteriaPanel() {
        radiusBacteriaPanel = new JPanel();
        radiusBacteriaPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        radiusBacteriaPanel.add(radiusBacteriaTitle);
        setupRadiusBacteriaSlider();
        setupRadiusBacteriaLabel();
        this.add(radiusBacteriaPanel);
    }
    private void setupRadiusBacteriaSlider() {
        radiusBacteriaSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        radiusBacteriaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                radiusBacteriaValue.setText(Integer.toString(radiusBacteriaSlider.getValue()));
            }
        });
        radiusBacteriaPanel.add(radiusBacteriaSlider);
    }
    private void setupRadiusBacteriaLabel() {
        radiusBacteriaValue = new JLabel(Integer.toString(radiusBacteriaSlider.getValue()));
        radiusBacteriaPanel.add(radiusBacteriaValue);
    }
    private void setupRadiusFoodPanel() {
        radiusFoodPanel = new JPanel();
        radiusFoodPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        radiusFoodPanel.add(radiusFoodTitle);
        setupRadiusFoodSlider();
        setupRadiusFoodLabel();
        this.add(radiusFoodPanel);
    }
    private void setupRadiusFoodSlider() {
        radiusFoodSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        radiusFoodSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                radiusFoodValue.setText(Integer.toString(radiusFoodSlider.getValue()));
            }
        });
        radiusFoodPanel.add(radiusFoodSlider);
    }
    private void setupRadiusFoodLabel() {
        radiusFoodValue = new JLabel(Integer.toString(radiusFoodSlider.getValue()));
        radiusFoodPanel.add(radiusFoodValue);
    }
    private void setupGravityPanel() {
        gravityPanel = new JPanel();
        gravityPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gravityPanel.add(gravityTitle);
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
        radiusInteractionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        radiusInteractionPanel.add(radiusInteractionTitle);
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
    public int getRadiusBacteria() {
        return radiusBacteriaSlider.getValue();
    }
    public int getRadiusFood() {
        return radiusFoodSlider.getValue();
    }
}
