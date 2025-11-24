package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.*;
import loanSysModel.managers.*;

import java.util.Random;

public class LoanTask extends Thread {
    private final ProductManager productManager;
    private final MemberManager memberManager;
    private final LoanItemManager loanItemManager;
    private final Controller controller;
    private boolean isRunning = true;
    private final Random random = new Random();

    public LoanTask(ProductManager productManager,
                    MemberManager memberManager,
                    LoanItemManager loanItemManager,
                    Controller controller) {
        this.productManager = productManager;
        this.memberManager = memberManager;
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
                if (productManager.noProductsAvailable()) {
                    Thread.sleep(1000);
                    continue;
                }

                Product product = productManager.get(random.nextInt(productManager.size()));
                Member member = memberManager.getRandomMember();

                if (product != null && member != null) {
                    LoanItem loanItem = new LoanItem(product, member);
                    loanItemManager.add(loanItem);
                    productManager.remove(product);

                    controller.updateView("Loaned: " + loanItem.toString());
                }

                int sleepTime = 2000 + random.nextInt(4000); // 2–6 sekunder
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
