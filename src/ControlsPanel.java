import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements ActionListener {
    private boolean start = false;
    private boolean stop = false;
    private boolean restart = false;

    private AnimationsPanel AnimPanel;
    private Timer timer;

    JButton buttonStart;
    JButton buttonStop;
    JButton buttonRestart;

    Dimension buttonSize = new Dimension(30,30);

    public ControlsPanel(AnimationsPanel AnimPanel){
        this.timer = timer;
        this.AnimPanel=AnimPanel;

        ImageIcon startIcon = new ImageIcon(Main.class.getResource("start.png"));
        ImageIcon stopIcon = new ImageIcon(Main.class.getResource("stop.png"));
        ImageIcon restartIcon = new ImageIcon(Main.class.getResource("restart.png"));

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonStart){
            //if started, start animation (angle gets increased by timer)
            AnimPanel.setPause(false);
        }
        if(e.getSource()== buttonStop){
            //if stopped, the timer stops adding to angle
            AnimPanel.setPause(true);
        }
        if(e.getSource() == buttonRestart){
            //if restarted, set the angle to 0 and repaint to show it.
            AnimPanel.setAngle(0);
            AnimPanel.repaint();
        }
    }
}
