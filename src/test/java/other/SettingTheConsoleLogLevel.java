package other;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class SettingTheConsoleLogLevel {

    public void setConsoleLogLevel(java.util.logging.Level level) {
        Logger topLogger = java.util.logging.Logger.getLogger("io.github.seleniumquery");

        Handler consoleHandler = null;
        for (Handler handler : topLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                consoleHandler = handler;
                break;
            }
        }

        if (consoleHandler == null) {
            consoleHandler = new ConsoleHandler();
            topLogger.addHandler(consoleHandler);
        }
        consoleHandler.setLevel(level);
    }

}