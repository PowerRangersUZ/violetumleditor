package com.horstmann.violet.framework.theme;

import java.awt.*;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.pagosoft.plaf.PgsLookAndFeel;
import com.pagosoft.plaf.PgsTheme;

public class BlackOrangeTheme extends AbstractTheme {

    @Override
    public ThemeInfo getThemeInfo() {
        return new ThemeInfo("Dark Orange", BlackOrangeTheme.class, PgsLookAndFeel.class);
    }

    @Override
    protected void configure() {
        PgsLookAndFeel.setCurrentTheme(new OrangeTheme());
    }

    @Override
    public Color getWhiteColor() {
        return Color.WHITE;
    }

    @Override
    public Color getBlackColor() {
        return Color.BLACK;
    }

    @Override
    public Color getGridColor() { return new Color(0xB1B1B1); }

    @Override
    public Color getBackgroundColor() { return new Color( 0x484848 ); }

    @Override
    public Color getMenubarBackgroundColor() { return new Color( 0x4B4B4B ); }

    @Override
    public Color getMenubarForegroundColor() { return new Color(0x4B4B4B); }

    @Override
    public Color getRolloverButtonDefaultColor() { return new Color( 0x4B4B4B ); }

    @Override
    public Color getRolloverButtonRolloverColor() { return getWhiteColor(); }

    @Override
    public Color getRolloverButtonRolloverBorderColor() { return new Color( 0x4B4B4B ); }

    @Override
    public Color getSidebarBackgroundStartColor() { return new Color( 0x484848 ); }

    @Override
    public Color getSidebarBackgroundEndColor() { return new Color( 0x484848 ); }

    @Override
    public Color getSidebarElementBackgroundColor() { return new Color( 0x484848 ); }

    @Override
    public Color getSidebarElementTitleBackgroundStartColor() { return new Color( 0x574E42 ); }

    @Override
    public Color getSidebarElementTitleBackgroundEndColor() { return new Color( 0x574E42 ); }

    @Override
    public Color getSidebarElementForegroundColor() { return getBlackColor(); }

    @Override
    public Color getSidebarElementTitleOverColor() { return getBlackColor(); }

    @Override
    public Color getSidebarBorderColor() { return new Color( 0x484848 ); }

    @Override
    public Color getStatusbarBackgroundColor() { return new Color( 0xA6A6A6 ); }

    @Override
    public Color getStatusbarBorderColor() { return new Color( 0xB44B4B ); }

    @Override
    public Color getToggleButtonSelectedColor() { return new Color( 0xF79200 ); }

    @Override
    public Color getToggleButtonSelectedBorderColor() { return new Color(0x967246); }

    @Override
    public Color getToggleButtonUnselectedColor() { return new Color( 0x484848 ); }

    @Override
    public Color getWelcomeBackgroundStartColor() { return new Color( 0x909090 ); }

    @Override
    public Color getWelcomeBackgroundEndColor() { return new Color(0x7B7B7B); }

    @Override
    public Color getWelcomeBigForegroundColor() { return getWhiteColor(); }

    @Override
    public Color getWelcomeBigRolloverForegroundColor() { return new Color( 0xFFCB97 ); }

    @Override
    public Font getWelcomeBigFont() {
        return MetalLookAndFeel.getWindowTitleFont().deriveFont((float) 12.0).deriveFont(Font.PLAIN);
    }

    @Override
    public Font getToggleButtonFont() {
        return MetalLookAndFeel.getWindowTitleFont().deriveFont((float) 12.0).deriveFont(Font.PLAIN);
    }

    @Override
    public Font getMenubarFont() {
        return MetalLookAndFeel.getWindowTitleFont().deriveFont((float) 12.0).deriveFont(Font.PLAIN);
    }

    @Override
    public Font getWelcomeSmallFont() {
        return MetalLookAndFeel.getWindowTitleFont().deriveFont((float) 12.0).deriveFont(Font.PLAIN);
    }

    private class OrangeTheme extends PgsTheme{

        private final Color Primary1 = new Color(0xC47900);
        private final Color Primary2 = new Color(0xF8C585);
        private final Color Primary3 = new Color(0xFCF0DF);
        private final Color Secondary1 = new Color(0x3B3E44);
        private final Color Secondary2 = new Color(0x515151);
        private final Color Secondary3 = new Color(0x858686);
        private final Color MenuBarColor = new Color(0xD5D5D5);
        private final Color MenuBarMenuColor = new Color(0xA8A8A8);

        public OrangeTheme(){
            super("Orange");

            setUIPrimaryColor();
            setUISecondaryColor();
            setDefaultsColors();
        }

        public void setUIPrimaryColor(){
            setPrimary1(new ColorUIResource(Primary1));
            setPrimary2(new ColorUIResource(Primary2));
            setPrimary3(new ColorUIResource(Primary3));

            setBlack(new ColorUIResource(Color.BLACK));
            setWhite(new ColorUIResource(Color.WHITE));
        }

        public void setUISecondaryColor(){
            setSecondary1(new ColorUIResource(Secondary1));
            setSecondary2(new ColorUIResource(Secondary2));
            setSecondary3(new ColorUIResource(Secondary3));
        }

        private void setDefaultsColors() {
            Object[] defaults = new Object[] {
                    PgsConst.MenuBarIsFlat, Boolean.FALSE,
                    PgsConst.MenuBarGradientStart, new ColorUIResource(MenuBarColor),
                    PgsConst.MenuBarGradientMiddle, new ColorUIResource(MenuBarColor),
                    PgsConst.MenuBarGradientEnd, new ColorUIResource(MenuBarColor),

                    PgsConst.MenuBarMenuIsFlat, Boolean.FALSE,
                    PgsConst.MenuBarMenuForeground, getWhite(),
                    PgsConst.MenuBarMenuRolloverBckgGradientStart, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuRolloverBckgGradientMiddle, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuRolloverBckgGradientEnd, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuSelectedBckgGradientStart, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuSelectedBckgGradientMiddle, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuSelectedBckgGradientEnd, new ColorUIResource(MenuBarMenuColor),
                    PgsConst.MenuBarMenuRolloverBorder, new ColorUIResource(Color.gray),
                    PgsConst.MenuBarMenuSelectedBorder, new ColorUIResource(Color.gray),

                    PgsConst.MenuIsFlat, Boolean.FALSE,
                    PgsConst.MenuGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.MenuGradientMiddle, new ColorUIResource(Color.gray),
                    PgsConst.MenuGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.MenuItemIsFlat, Boolean.FALSE,
                    PgsConst.MenuItemGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.MenuItemGradientMiddle, new ColorUIResource(Color.gray),
                    PgsConst.MenuItemGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.CheckBoxMenuItemIsFlat, Boolean.FALSE,
                    PgsConst.CheckBoxMenuItemGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.CheckBoxMenuItemGradientMiddle, new ColorUIResource(Color.gray),
                    PgsConst.CheckBoxMenuItemGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.RadioBtnMenuItemIsFlat, Boolean.FALSE,
                    PgsConst.RadioBtnMenuItemGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.RadioBtnMenuItemGradientMiddle, new ColorUIResource(Color.gray),
                    PgsConst.RadioBtnMenuItemGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.ButtonRolloverGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.ButtonRolloverGradientEnd, new ColorUIResource(Color.gray),
                    PgsConst.ButtonRolloverVistaStyle, new ColorUIResource(Color.gray),
                    PgsConst.ButtonSelectedGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.ButtonSelectedGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.ToggleBtnRolloverGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.ToggleBtnRolloverGradientEnd, new ColorUIResource(Color.gray),
                    PgsConst.ToggleBtnSelectedGradientStart, new ColorUIResource(Color.gray),
                    PgsConst.ToggleBtnSelectedGradientEnd, new ColorUIResource(Color.gray),

                    PgsConst.TabbedPaneSelected, new ColorUIResource(Color.gray),
                    PgsConst.TabbedPaneBackground, new ColorUIResource(Color.gray),
                    PgsConst.TabbedPaneSelectedForeground, new ColorUIResource(Color.gray),
            };
        }
    }
}

