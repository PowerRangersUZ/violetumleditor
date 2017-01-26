package com.horstmann.violet.framework.help.shortcuts;

import com.horstmann.violet.framework.injection.resources.ResourceShortcutProvider;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ShortcutsManager
{
    private HashMap<String, String> shortcutUserMap = new HashMap<String, String>();
    private HashMap<String, String> shortcutDefaultMap;
    private ShortcutReadWriter shortcutReadWriter = new ShortcutReadWriter();

    public ShortcutsManager()
    {
        shortcutDefaultMap = ResourceShortcutProvider.getInstance().getAllShortcuts();
        loadUserShortcuts();
    }

    public void loadUserShortcuts()
    {
        try{
            shortcutUserMap = shortcutReadWriter.readShortcutXML();

        }catch (FileNotFoundException e){
            shortcutUserMap = shortcutDefaultMap;
        }
    }

    public void saveUserShortcuts(HashMap<String, String> mapToSave)
    {
        if(mapToSave != null)
        {
            shortcutReadWriter.writeShortcutXML(mapToSave);
        }else
        {
            shortcutReadWriter.writeShortcutXML(shortcutUserMap);
        }
    }

    public String getShortcut(String key)
    {
        if(shortcutUserMap.containsKey(key))
        {
            return shortcutUserMap.get(key).replace('-', ' ');
        }else{
            return null;
        }
    }

    public void updateShortcut(String key, String value)
    {
        if(key != null && value != null)
        {
            shortcutUserMap.remove(key);
            shortcutUserMap.put(key, value);
        }
    }




}
