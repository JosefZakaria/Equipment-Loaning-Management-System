package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.Product;
import loanSysModel.managers.ProductManager;

import java.util.Random;

public class AdminTask extends Thread {
    private final ProductManager productManager;
    private final Controller controller;
    private boolean isRunning = true;
    private final Random random = new Random();

    public AdminTask(ProductManager productManager, Controller controller) {
        this.productManager = productManager;
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
                // Skapa ny testprodukt
                Product product = productManager.addNewTestProduct();
                controller.updateView("Admin added: " + product.toString());


                int sleepTime = 6000 + random.nextInt(10000); // 6–16 sekunder
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
