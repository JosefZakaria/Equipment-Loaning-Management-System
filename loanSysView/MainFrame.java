package loanSysView;
import javax.swing.*;

import loanSysControllers.Controller;

import java.awt.*;


//This class sets ups the main view. The only component it contains is a
//main panel which covers the whole drawing surface. The main panel in turn contains
//other panels. For details see the MainPanel. The size of the MainPanel is
//relative to the width and height set in this class.
//See the line:  panel = new MainPanel(controller,width, height);
public class MainFrame extends JFrame
{
    private int width = 800;
    private int height = 600;

    Controller controller;
    MainPanel panel;

    public MainFrame(Controller controller)
    {
        this.controller = controller;
        setupFrame();

    }
    //standard settings
    public void setupFrame()
    {
        final int offsetX = width/5;
        final int offsetY = height / 5;

        //setSize(width, height);
        setPreferredSize(new Dimension(width+20, height+20));
        setTitle("Equipment Loaning System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(offsetX, offsetY);

        panel = new MainPanel(controller,width, height);
        setContentPane(panel);
        pack();
        setResizable(false);
        //center to screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Methods called by the controller to access the components drawn on the Main panel and its
    //sub-panels.
    public void updateItemsList(String [] stringList, boolean clearList)
    {
        panel.getPnlEast().updateItemsList(stringList,clearList);
    }
    public void errMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }
    public void updateEventLog(String [] stringList)
    {
       panel.getPnlEast().updateItemsList(stringList, false);
    }
    public void updateEventLog(String stringInfo)
    {
        panel.getPnlCenter().updateProductList(stringInfo);
    }
    public int getListIndex()
    {
        return panel.getPnlCenter().getListIndex();
    }
}
