import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Information extends JLabel implements MouseListener{
    String hoverText;
    String clickText;
    String title;
    public Information(String hoverText, String clickText, String title){
        super();
        this.setText("ⓘ");
        this.setFont(new Font("Dialog", Font.BOLD, 15));
        addMouseListener(this);

        this.hoverText = hoverText;
        this.clickText = clickText;
        this.title = title;
        this.setToolTipText(this.hoverText);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==this){
            JOptionPane.showMessageDialog(null,clickText,title,JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //These here do absolutely nothing, but i don't get a choice in implementing them
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
