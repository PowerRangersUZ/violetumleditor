package com.horstmann.violet.application.help;


import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TipOfTheDay {

    public TipOfTheDay() throws IOException {
        ResourceBundleInjector.getInjector().inject(this);
        createTipWindow();
    }

    private void createTipWindow() {
        JPanel buttonPanel = new JPanel();
        tipFrame.setTitle(title);
        tipFrame.setSize(SIZE * 5, SIZE*2 );
        tipFrame.setResizable(false);
        tipFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JButton closeButton = new JButton(close);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tipFrame.dispose();
            }
        });

        JLabel labelWithTip = new JLabel("<html>" + "<center>" + tipList(string)+ "</center>" +"</html>", SwingConstants.CENTER);
        JLabel icon = new JLabel((image));
        labelWithTip.setFont(new Font("Times New Roman", Font.ITALIC, SIZE/5));

        buttonPanel.add(closeButton);
        tipFrame.add(buttonPanel, BorderLayout.SOUTH);
        tipFrame.add(labelWithTip, BorderLayout.CENTER);
        tipFrame.add(icon,BorderLayout.EAST);
        tipFrame.setLocationRelativeTo(null);
        tipFrame.setVisible(true);
    }
    /**
     * @return tip from list
     */
    public String tipList(String string) {
        ArrayList<String> tips = new ArrayList<String>();
        tips.add(tip2);
        tips.add(tip3);
        tips.add(tip4);
        tips.add(tip5);
        tips.add(tip6);
        tips.add(tip7);
        tips.add(tip8);
        tips.add(tip9);
        tips.add(tip10);
        tips.add(tip11);
        tips.add(tip12);
        tips.add(tip13);
        tips.add(tip14);
        tips.add(tip15);
        tips.add(tip16);
        tips.add(tip17);
        tips.add(tip18);
        tips.add(tip19);
        tips.add(tip20);
        tips.add(tip21);
        tips.add(tip22);
        tips.add(tip23);
        tips.add(tip24);
        tips.add(tip25);
        string = (String) tips.get((int)(Math.random()*tips.size()));
        return string;
    }

    final int SIZE = 90;
    public JFrame tipFrame = new JFrame();
    String string = null;
    //region Keys initialization
    @ResourceBundleBean(key="tip.title")
    private String title;
    @ResourceBundleBean(key="tip.closeWindow")
    private String close;
    @ResourceBundleBean(key="tip2.txt")
    private String tip2;
    @ResourceBundleBean(key="tip3.txt")
    private String tip3;
    @ResourceBundleBean(key="tip4.txt")
    private String tip4;
    @ResourceBundleBean(key="tip5.txt")
    private String tip5;
    @ResourceBundleBean(key="tip6.txt")
    private String tip6;
    @ResourceBundleBean(key="tip7.txt")
    private String tip7;
    @ResourceBundleBean(key="tip8.txt")
    private String tip8;
    @ResourceBundleBean(key="tip9.txt")
    private String tip9;
    @ResourceBundleBean(key="tip10.txt")
    private String tip10;
    @ResourceBundleBean(key="tip11.txt")
    private String tip11;
    @ResourceBundleBean(key="tip12.txt")
    private String tip12;
    @ResourceBundleBean(key="tip13.txt")
    private String tip13;
    @ResourceBundleBean(key="tip14.txt")
    private String tip14;
    @ResourceBundleBean(key="tip15.txt")
    private String tip15;
    @ResourceBundleBean(key="tip16.txt")
    private String tip16;
    @ResourceBundleBean(key="tip17.txt")
    private String tip17;
    @ResourceBundleBean(key="tip18.txt")
    private String tip18;
    @ResourceBundleBean(key="tip19.txt")
    private String tip19;
    @ResourceBundleBean(key="tip20.txt")
    private String tip20;
    @ResourceBundleBean(key="tip21.txt")
    private String tip21;
    @ResourceBundleBean(key="tip22.txt")
    private String tip22;
    @ResourceBundleBean(key="tip23.txt")
    private String tip23;
    @ResourceBundleBean(key="tip24.txt")
    private String tip24;
    @ResourceBundleBean(key="tip25.txt")
    private String tip25;
    @ResourceBundleBean(key="tip.image")
    private ImageIcon image;
    //endregion

}
