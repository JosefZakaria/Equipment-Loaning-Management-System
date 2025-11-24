package loanSysModel.tasks;

import loanSysControllers.Controller;

import javax.swing.*;
import java.util.Random;

/*
    The task of this thread class is to send request to the controller to
    update the list of products that are on loand as wekk as  the list
    available products on the UI using at certain intervals (e.g 2 seconds).
 */
public class UpdateGUI extends Thread {
    private Random random;
    private boolean isRunning = true; //to start and stop the thread
    private Controller controller;

    //constructor
    public UpdateGUI( Controller controller) {
        this.controller = controller;
         random = new Random();
    }

    //Sets isRunning to true/fals; when true, the thread continues processing and
    //if false, it stops
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
        this.interrupt();
    }

    //This method is run by the thread assigned to perform the task. It requests
    //updating the list of products and the list of items on loan by the controller.
    public void run() {
        try {
            while (isRunning) {
                // Update any UI  - Ensures updates are performed on the Event Dispatch Thread
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        controller.updateAllItems();
                    }
                });
                Thread.sleep(2000); // Simulate some operation
            }
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
}

