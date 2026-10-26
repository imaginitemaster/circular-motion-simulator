import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen(){
        setTitle("Circular Motion");
        setSize(1200,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        JPanel infoPanel = new JPanel();
        Information info = new Information("Project Info","This project models Circular Motion in a way that should be useful for a student\nIt allows students to adjust different values related to circular motion in order to get a better understanding of how it works.\n\nThere are info icons like this one throughout that can provide extra information on how each object works.\n\nNOTE:\nThis simulation assumes Uniform Circular Motion (the ball moves in a circular path at constant speed)\nThe simulation's model is of the HORIZONTAL PLANE","Project");
        SimulationModel model = new SimulationModel();

        DisplayFormulae formulae = new DisplayFormulae(model);
        formulae.setPreferredSize(new Dimension(245,500));
        AnimationsPanel animPanel = new AnimationsPanel(model,formulae);
        animPanel.setPreferredSize(new Dimension(600,600));
        ControlsPanel ctrlPanel = new ControlsPanel(animPanel,model);
        ctrlPanel.setPreferredSize(new Dimension(800,40));
        SettingsPanel settPanel = new SettingsPanel(model);
        settPanel.setPreferredSize(new Dimension(245,800));


        infoPanel.add(info,BorderLayout.NORTH);
        settPanel.add(formulae,BorderLayout.SOUTH);
        add(infoPanel,BorderLayout.WEST);
        add(ctrlPanel, BorderLayout.SOUTH);
        add(settPanel, BorderLayout.EAST);
        add(animPanel, BorderLayout.CENTER);

        setVisible(true);

    }

}
//ⓘ