import javax.swing.*;

public class Information extends JLabel {
    String hoverText;
    String clickText;
    String title;
    public Information(String hoverText, String clickText, String title){
        super();
        this.setText("ⓘ");
        this.hoverText = hoverText;
        this.clickText = clickText;
        this.title = title;
        this.setToolTipText(this.hoverText);
    }
}
