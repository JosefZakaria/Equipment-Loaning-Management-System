package loanSysModel.managers;

import loanSysControllers.Controller;
import loanSysModel.tasks.*;

public class LoanSystemManager {

    private final ProductManager productManager;
    private final MemberManager memberManager;
    private final LoanItemManager loanItemManager;
    private final Controller controller;
    private boolean isRunning = false;

    private LoanTask loanTask;
    private ReturnTask returnTask;
    private AdminTask adminTask;
    private UpdateGUI updateGUITask;

    public LoanSystemManager(ProductManager productManager,
                             MemberManager memberManager,
                             LoanItemManager loanItemManager,
                             Controller controller) {
        this.productManager = productManager;
        this.memberManager = memberManager;
        this.loanItemManager = loanItemManager;
        this.controller = controller;
    }

    public void startAll() {
        if (isRunning) return; // redan igång
        isRunning = true;

        loanTask = new LoanTask(productManager, memberManager, loanItemManager, controller);
        returnTask = new ReturnTask(productManager, loanItemManager, controller);
        adminTask = new AdminTask(productManager, controller);
        updateGUITask = new UpdateGUI(controller);

        loanTask.start();
        returnTask.start();
        adminTask.start();
        updateGUITask.start();
    }


    public void stopAll() {
        isRunning = false;

        if (loanTask != null)
            loanTask.setIsRunning(false);

        if (returnTask != null)
            returnTask.setIsRunning(false);

        if (adminTask != null)
            adminTask.setIsRunning(false);

        if (updateGUITask != null)
            updateGUITask.setIsRunning(false);
    }

}
