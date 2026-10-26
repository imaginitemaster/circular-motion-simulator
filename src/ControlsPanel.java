import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements ActionListener {

    private AnimationsPanel animPanel;
    private SimulationModel model;
    private Information controlsInfo;

    JButton buttonStart;
    JButton buttonStop;
    JButton buttonRestart;

    Dimension buttonSize = new Dimension(30,30);

    public ControlsPanel(AnimationsPanel animPanel, SimulationModel simModel){
        this.animPanel = animPanel;
        this.model = simModel;

        ImageIcon startIcon = new ImageIcon(Main.class.getResource("start.png"));
        ImageIcon stopIcon = new ImageIcon(Main.class.getResource("stop.png"));
        ImageIcon restartIcon = new ImageIcon(Main.class.getResource("restart.png"));

        //Information
        controlsInfo = new Information("Controls Info","Start:\nPressing the start button will begin the animation \nPause:\nPressing the pause button will pause the animation\nRestart:\nPressing the restart button will pull the ball back to where it started \n\nBall Release:\nAfter releasing the ball, the start, pause and restart buttons will perform the exact same jobs, but for linear motion instead of circular motion","Controls");

        //start button
        buttonStart = new JButton();
        buttonStart.setBounds(540,30,20,20);
        buttonStart.setPreferredSize(buttonSize);
        buttonStart.addActionListener(this);
        buttonStart.setIcon(startIcon);
        //stop button
        buttonStop = new JButton();
        buttonStop.setBounds(590,30,20,20);
        buttonStop.setPreferredSize(buttonSize);
        buttonStop.addActionListener(this);
        buttonStop.setIcon(stopIcon);
        //restart button
        buttonRestart = new JButton();
        buttonRestart.setBounds(640,30,20,20);
        buttonRestart.setPreferredSize(buttonSize);
        buttonRestart.addActionListener(this);
        buttonRestart.setIcon(restartIcon);

        this.add(buttonStart);
        this.add(buttonStop);
        this.add(buttonRestart);
        this.add(controlsInfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonStart){
            if(model.getCircularPath()){
                //if started, start animation (angle gets increased by timer)
                model.setPauseCircular(false);
            }else{
                model.setPauseLinear(false);
            }
        }
        if(e.getSource()== buttonStop){
            if(model.getCircularPath()){
                //if stopped, the timer stops adding to angle
                model.setPauseCircular(true);
            }else{
                model.setPauseLinear(true);
            }
        }
        if(e.getSource() == buttonRestart){
            if(model.getCircularPath()){
                //if restarted, set the angle to 0 and repaint to show it.
                model.setAngle(0);
                animPanel.repaint();
            }else{
                model.resetBallPos();
            }
        }
    }
}
