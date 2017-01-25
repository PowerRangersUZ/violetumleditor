package com.horstmann.violet.application.help;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.injection.bean.ManiocFramework;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adrian on 2017-01-24.
 */
public class TipOfTheDayDialog extends JDialog {

    @ResourceBundleBean(key="dialog.title")
    private String titleDialog;

    @ResourceBundleBean(key="dialog.topPanelTitle")
    private String topTitlePanel;

    @ResourceBundleBean(key="dialog.checkbox.showTips")
    private String showTipsText;

    @ResourceBundleBean(key="dialog.button.closeButton")
    private String closeButtonText;

    private final int widthDialog = 450;
    private final int heightDialog = 300;

    public TipOfTheDayDialog(JFrame parent, boolean modal){
        super(parent, modal);

        ResourceBundleInjector.getInjector().inject(this);
        ManiocFramework.BeanInjector.getInjector().inject(this);

        initUI();

        setLocation(parent);
    }

    private void initUI(){

        this.setTitle(titleDialog);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(widthDialog, heightDialog));

        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));

        basic.add(createTopPanel());
        basic.add(createTextPanel());
        basic.add(createCheckBoxPanel());
        basic.add(createBottomPanel());

        this.getContentPane().add(basic);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Start creating TopPanel
    private JPanel createTopPanel(){

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setPreferredSize(new Dimension(450, 0));

        JLabel hint = createTopHintLabel(topTitlePanel);
        JSeparator separator = createSeparator();

        topPanel.add(hint);
        topPanel.add(separator, BorderLayout.SOUTH);

        return topPanel;
    }

    private JLabel createTopHintLabel(String text){

        JLabel hint = new JLabel(text);
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        return hint;
    }

    private JSeparator createSeparator(){

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        return separator;
    }
    // TopPanel - created

    // Start creating TextPanel
    private JPanel createTextPanel(){

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        textPanel.add(createTipOfTheDayPane());

        return textPanel;
    }

    private JTextPane createTipOfTheDayPane(){

        JTextPane pane = new JTextPane();

        pane.setContentType("text/html");
        pane.setText(getTipOfTheDay());
        pane.setEditable(false);

        return pane;
    }

    private String getTipOfTheDay(){

        String text = "<p><b>Closing windows using the mouse wheel</b></p>" +
                "<p>Clicking with the mouse wheel on an editor tab closes the window. " +
                "This method works also with dockable windows or Log window tabs.</p>";

        return text;
    }
// TextPanel - created

    // Start creating CheckBoxPanel
    private JPanel createCheckBoxPanel(){

        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

        JCheckBox checkBox = new JCheckBox(showTipsText);

        checkBoxPanel.add(checkBox);

        return checkBoxPanel;
    }
// CheckBoxPanel - created

    // Start creating BottomPanel
    private JPanel createBottomPanel(){

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setMaximumSize(new Dimension(450, 0));

        JButton closeButton = createCloseButton(closeButtonText);

        bottomPanel.add(closeButton);

        return bottomPanel;
    }

    private JButton createCloseButton(String text){

        JButton closeButton = new JButton(text);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.getRootPane().setDefaultButton(closeButton);

        return closeButton;
    }
// BottomPanel - created

    private void setLocation(JFrame parent)
    {
        Point point = parent.getLocationOnScreen();
        int x = (int) point.getX() + parent.getWidth() / 2;
        int y = (int) point.getY() + parent.getHeight() / 2;
        setLocation(x - getWidth() / 2, y - getHeight() / 2);
    }
}
