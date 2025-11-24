package loanSysView;

import loanSysControllers.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

//MainPanel is a container for several other panels.
//It is using the BorderLayout.  This layout has 6 parts NORTH, SOUTH
//WEST, EAST and CENTER.  In this assignment the CENTER , the SOUTH
//and the EAST parts are used for drawing listboxes and buttons.
//Before drawing a component, a Panel is created in the part on which
//The component is drown. For example a panel is drown on the South
//part of the layout SouthPanel. Then the Start and Stop buttons are
//created and drawn on this panel.  To organize this in a structured
//way, each sub-panel (like the south panel) is coded in a separate
//class.  See SouthPanel, CenterPanel and EastPanel
public class MainPanel extends JPanel
{
    Controller controller;
     SouthPanel pnlSouth;  //used for drawing two buttons
    BorderLayout layout;
    CenterPanel pnlCenter;  //used for list of events
    EastPanel pnlEast;     //used for list of product items.
    int width, height;   //coming from MainFrame
    public MainPanel(Controller controller, int width, int height)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;
        setupPanel();
    }

    //The height and width of each sub-panel is taken as a part of the total
    //width and heigh
    private void setupPanel()
    {
        layout = new BorderLayout();
        setLayout(layout);

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        setBorder(new CompoundBorder(border, margin));

        //createInfoTextAreaAtEast();  //output at right
         //Buttons at south
        pnlSouth = new SouthPanel(controller);
        add(pnlSouth, layout.SOUTH);

        //This panel contains a JList for showing events like when a loan, or return
        //occurs.
        // PanelCenter should have a height less the height of the southPanel
        //Here it is estimated as 130 pixels, this and the width has beeen set
        //by some trial and error (as a simplification).
        pnlCenter = new CenterPanel(controller, width/2+100, height -130);
        add(pnlCenter, BorderLayout.CENTER);

        //This panel containa a JList for showing the items on loan and items
        //available
        pnlEast = new EastPanel(controller, width/2-60, height-130);

        add(pnlEast, BorderLayout.EAST);

    }

    //Providing access to the subpanels.
    public EastPanel getPnlEast() {return pnlEast;}

     public SouthPanel getPnlSouth()
    {
        return pnlSouth;
    }
    public CenterPanel getPnlCenter() {return pnlCenter;}
    //public int getListIndex(){return pnlEast.getListIndex();}

  }

