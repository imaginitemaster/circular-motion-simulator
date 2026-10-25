import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen(){
        setTitle("Circular Motion");
        setSize(1200,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JPanel infoPanel = new JPanel();
        Information info = new Information("Project Info","This project models Circular Motion in a way that should be useful for a student\nIt allows students to adjust different values related to circular motion in order to get a better understanding of how it works.\n\nThere are info icons like this one throughout that can provide extra information on how each object works.","Project");
        SimulationModel model = new SimulationModel();

        AnimationsPanel animPanel = new AnimationsPanel(model);
        animPanel.setPreferredSize(new Dimension(600,600));
        ControlsPanel ctrlPanel = new ControlsPanel(animPanel,model);
        ctrlPanel.setPreferredSize(new Dimension(800,40));
        SettingsPanel slidePanel = new SettingsPanel(model);
        slidePanel.setPreferredSize(new Dimension(220,200));


        infoPanel.add(info,BorderLayout.NORTH);
        add(infoPanel,BorderLayout.WEST);
        add(ctrlPanel, BorderLayout.SOUTH);
        add(slidePanel, BorderLayout.EAST);
        add(animPanel, BorderLayout.CENTER);

        setVisible(true);

    }

}
//ⓘ