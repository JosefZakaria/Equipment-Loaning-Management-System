package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.*;
import loanSysModel.managers.*;

import java.util.Random;

public class ReturnTask extends Thread {
    private final ProductManager productManager;
    private final LoanItemManager loanItemManager;
    private final Controller controller;
    private boolean isRunning = true;
    private final Random random = new Random();

    public ReturnTask(ProductManager productManager,
                      LoanItemManager loanItemManager,
                      Controller controller) {
        this.productManager = productManager;
        this.loanItemManager = loanItemManager;
        this.controller = controller;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
        this.interrupt();

    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (loanItemManager.isEmpty()) {
                    Thread.sleep(1000);
                    continue;
                }

                LoanItem loanItem = loanItemManager.getRandomItem();

                if (loanItem != null) {
                    productManager.add(loanItem.getProduct());
                    loanItemManager.remove(loanItem);

                    controller.updateView("Returned: " + loanItem.toString());
                }

                int sleepTime = 3000 + random.nextInt(12000); // 3–15 sekunder
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
