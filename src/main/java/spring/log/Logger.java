package spring.log;

import Controller.Main;

import java.io.IOException;
import java.util.logging.*;

/**
 * A general class for logging operations based on Java's
 * default logging package. It contains no method since no
 * need to use any method. We can use <tt>log()</tt> to
 * write a log info into app.log file.
 *
 * <p>
 *     Class has three fields:
 *     <ul>
 *         <li> instance: A final object for Singleton pattern
 *         <li> LOGGER: A final java.util.logging.Logger object to invoke log method
 *         <li> DEFAULT_LEVEL: A Level final object to indicate default Level value
 *     </ul>
 * </p>
 *
 * @author Muhammed Bera KOÇ
 * @since 1.3
 */
public class Logger {

    public static final Logger instance = new Logger();
    public final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Main.class.getName());
    public static final Level DEFAULT_LEVEL = Level.INFO;

    /**
     * Constructor first sets the level, then disables root handler(Terminal)
     * then sets the current handler as a file handler and sets the append true
     * and adds it to the handlers ArrayList
     */
    private Logger() {
        LOGGER.setLevel(DEFAULT_LEVEL);
        LOGGER.setUseParentHandlers(false);
        try {
            Handler fileHandler = new FileHandler("./app.log", true);
            fileHandler.setFormatter(MessageFormatter.instance);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
