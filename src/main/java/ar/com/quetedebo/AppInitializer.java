package ar.com.quetedebo;

import ar.com.quetedebo.core.QueTeDebo;
import ar.com.quetedebo.storage.PaymentHistory;
import ar.com.quetedebo.ui.admin.AdminUI;
import ar.com.quetedebo.ui.debts.DebtsView;

public class AppInitializer {

    public void startApplication(String[] args) {
        validateArguments(args);

        String extensionsPath = args[0];
        String dataPath = args[1];
        String storagePath = args[2];

        final QueTeDebo queTeDebo = new QueTeDebo(extensionsPath, dataPath);

        new PaymentHistory(storagePath, queTeDebo);

        launchUI(queTeDebo);
    }

    private void validateArguments(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "All three 'extensionsPath', 'dataPath', and 'storagePath' are required. Please provide them as the three arguments."
            );
        }
    }

    private void launchUI(QueTeDebo queTeDebo) {
        java.awt.EventQueue.invokeLater(() -> {
            new AdminUI(queTeDebo).setVisible(true);
            new DebtsView(queTeDebo).setVisible(true);
        });
    }

}
