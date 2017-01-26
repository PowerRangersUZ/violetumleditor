package com.horstmann.violet.application.help;

import com.horstmann.violet.framework.help.shortcuts.ShortcutsManager;
import com.horstmann.violet.framework.injection.resources.ResourceBundleConstant;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.ResourceShortcutProvider;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Class responsible for create shortcut dialog
 * Created by piter on 02.01.16.
 */
public class ShortcutDialog extends JDialog
{

    /**
     * Default constructor of ShortcutDialog
     * @param parent JFrame parent
     */
    public ShortcutDialog(JFrame parent)
    {
        super(parent);
        ResourceBundleInjector.getInjector().inject(this);

        this.setTitle(dialogTitle);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buildShortcutPanel(), BorderLayout.CENTER);
        this.getContentPane().add(buildShortcutButton(), BorderLayout.SOUTH);
        pack();
        setCenterLocation(parent);
    }

    private JButton buildShortcutButton()
    {
        JButton saveButton = new JButton(save);
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateShortcut();
            }
        });

        return saveButton;
    }

    private void updateShortcut()
    {
        shortcutsManager.loadUserShortcuts();

        TableModel tableModel = table.getModel();
        HashMap<String, String> shortcutMap = new HashMap<String, String>();

        for(int i =0; i<tableModel.getRowCount();i++)
        {
            String shortcutKey = "";
            String shortcutValue = "";

            for(int j=0;j<tableModel.getColumnCount();j++)
            {
                if ((j % 2) == 0)
                {
                    shortcutKey = table.getModel().getValueAt(i, j).toString();
                }
                else
                {
                    shortcutValue = table.getModel().getValueAt(i, j).toString();
                }
            }
            shortcutMap.put(shortcutKey, shortcutValue);
        }
/*
        System.out.println(ResourceShortcutProvider.getInstance().getAllShortcuts().entrySet());
        HashMap<String, String> shortcutOldMap = ResourceShortcutProvider.getInstance().getAllShortcuts();*/
/*
        ResourceBundle.getBundle(ResourceBundleConstant.)
        /*ResourceBundle.getBundle(ResourceBundleConstant.OTHER_STRINGS, Locale.getDefault()).getString(
                                "file.link.text")).append(" ").append(dl.getFile().getFilename());*/

/*
        System.out.println(shortcutMap);

        ResourceShortcutProvider.getInstance().updateShortcut("Zakończ", "CTRL-B");*/

        shortcutsManager.saveUserShortcuts(shortcutMap);
    }

    private JPanel buildShortcutPanel()
    {
        if(shortcutPanel == null)
        {
            shortcutPanel = new JPanel(new BorderLayout());

            String[] columnNames = {behaviorName, shortcut};

            table = new JTable(prepareDataForTable(), columnNames)
            {
                public boolean isCellEditable(int data, int columns)
                {
                    return false;
                }
            };

            addTableListeners();
            table.setEnabled(true);
            table.setCellSelectionEnabled(false);
            table.setShowGrid(true);
            table.setGridColor(new Color(220, 220, 220));
            table.setRowHeight(30);
            table.setIntercellSpacing(new Dimension(15, 0));
            table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
            table.getTableHeader().setReorderingAllowed(false);


            JScrollPane scrollPane = new JScrollPane(table);
            shortcutPanel.add(scrollPane, BorderLayout.CENTER);
        }
        return this.shortcutPanel;
    }

    private void addTableListeners()
    {
        table.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent me)
            {
                JTable table =(JTable) me.getSource();
                Point clickedPoint = me.getPoint();
                if (me.getClickCount() == 2)
                {
                    if(table.getSelectedColumn() == 1)
                    {
                        table.setValueAt("", table.getSelectedRow(), table.getSelectedColumn());
                    }
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent lse)
            {
                if (!lse.getValueIsAdjusting())
                {
                    System.out.println("Selection Changed");
                }
            }
        });

        table.addKeyListener(new KeyListener()
        {
            private final Set<String> pressedKeys = new HashSet<String>();

            @Override
            public synchronized void keyPressed(KeyEvent e)
            {
                String pressedKey =  e.getKeyText(e.getKeyCode());
                pressedKeys.add(pressedKey);

                if (pressedKeys.size() > 1)
                {
                    if(table.getSelectedColumn() == 1)
                    {
                        String pressedKeysValue = StringUtils.join(pressedKeys, "-").toUpperCase();
                        table.setValueAt(pressedKeysValue, table.getSelectedRow(), table.getSelectedColumn());
                    }
                }
            }

            @Override
            public synchronized void keyReleased(KeyEvent e)
            {
                String releasedKey = e.getKeyText(e.getKeyCode());
                pressedKeys.remove(releasedKey);
            }

            @Override
            public void keyTyped(KeyEvent e) {/* Not used */ }
        });
    }

    private String[][] prepareDataForTable()
    {
        final int shortcutNumber = ResourceShortcutProvider.getInstance().getAllShortcuts().size();
        String[][] shortcutArray;

        if(shortcutNumber != 0)
        {
            shortcutArray = new String[shortcutNumber][2];

            int counter = 0;
            for (Map.Entry<String, String> entry : ResourceShortcutProvider.getInstance().getAllShortcuts().entrySet())
            {
                shortcutArray[counter][0] = entry.getKey();
                shortcutArray[counter][1] = entry.getValue();
                counter++;
            }
        } else
        {
            shortcutArray = new String[1][2];
            shortcutArray[0][0] = noData;
            shortcutArray[0][1] = noData;
        }
        return shortcutArray;
    }

    private void setCenterLocation(JFrame parent)
    {
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }

    private JPanel shortcutPanel;
    private JTable table;
    private ShortcutsManager shortcutsManager = new ShortcutsManager();

    @ResourceBundleBean(key="dialog.title")
    private String dialogTitle;

    @ResourceBundleBean(key="dialog.table.behavior")
    private String behaviorName;

    @ResourceBundleBean(key="dialog.table.shortcut")
    private String shortcut;

    @ResourceBundleBean(key="dialog.table.nodata")
    private String noData;

    @ResourceBundleBean(key="dialog.save")
    private String save;
}

