package com.horstmann.violet.application.help;

import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TipOfTheDayTest {
    TipOfTheDay tip;
    String string = null;

    @Test
    public void createTipWindow() throws Exception {

        assertEquals(string, null);
    }
    //region AddtipsToArrayList

    public String tipList() throws Exception {
        ArrayList<String> tips = new ArrayList<String>();
        tips.add("This is test in english");
        tips.add("/u0F00 /u0F79 u/0F03 /u0F0C /u0FD9");
        tips.add("/u30A0 /u30F4 /u31F6 /u3077 /u3085");

        string = (String) tips.get((int)(Math.random()*tips.size()));
        return string;
    }
    //endregion
}