import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen(){
        setTitle("Circular Motion");
        setSize(1200,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        SimulationModel model = new SimulationModel();

        AnimationsPanel animPanel = new AnimationsPanel(model);
        animPanel.setPreferredSize(new Dimension(600,600));
        ControlsPanel ctrlPanel = new ControlsPanel(animPanel.getTimer(), animPanel);
        ctrlPanel.setPreferredSize(new Dimension(800,40));
        SlidersPanel slidePanel = new SlidersPanel(model);
        slidePanel.setPreferredSize(new Dimension(220,200));


        add(ctrlPanel, BorderLayout.SOUTH);
        add(slidePanel, BorderLayout.EAST);
        add(animPanel, BorderLayout.CENTER);

        setVisible(true);

    }

}
