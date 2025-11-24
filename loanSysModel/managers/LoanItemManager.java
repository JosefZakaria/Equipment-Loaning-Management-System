package loanSysModel.managers;

import loanSysModel.LoanItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoanItemManager {
    private final List<LoanItem> loanItems = new ArrayList<>();
    private final Random random = new Random();
    private boolean isRunning = false;

    public void add(LoanItem item) {
        loanItems.add(item);
    }

    public void remove(LoanItem item) {
        loanItems.remove(item);
    }

    public int size() {
        return loanItems.size();
    }

    public LoanItem get(int index) {
        if (index >= 0 && index < loanItems.size()) {
            return loanItems.get(index);
        }
        return null;
    }

    public boolean isEmpty() {
        return loanItems.isEmpty();
    }

    public LoanItem getRandomItem() {
        if (!loanItems.isEmpty()) {
            int index = random.nextInt(loanItems.size());
            return loanItems.get(index);
        }
        return null;
    }

    // Denna används för att uppdatera GUI:t
    public String[] getLoanedItemInfoStrings() {
        if (loanItems.isEmpty()) {
            return new String[]{"Loaned items: ", " "};
        }

        String[] info = new String[loanItems.size() + 2];
        info[0] = "Number of loaned items: " + loanItems.size();
        info[1] = "";

        for (int i = 0; i < loanItems.size(); i++) {
            info[i + 2] = loanItems.get(i).toString();
        }

        return info;
    }
}

