package io.github.seleniumquery;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import io.github.seleniumquery.globalfunctions.SeleniumQueryDefaultDriver;

/**
 * Represents the seleniumQuery global object.
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class SeleniumQueryStatic {
	
	/**
	 * Series of functions that set the default browser behavior.<br>
	 * <br><br>
	 * The default browser is emplyed when a seleniumQuery object is build using <code>$(".selector");</code>.<br>
	 * A different browser can be used by using <code>$(anotherDriver, ".selector");</code>
	 * 
	 * @author acdcjunior
	 * @since 1.0.0
	 */
	public final SeleniumQueryDefaultDriver browser;
	
	public SeleniumQueryStatic() {
		this.browser = new SeleniumQueryDefaultDriver();
	}
	
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