package spring.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * MessageFormatter class is a singleton class created to format
 * the log output. It extends Formatter class. And implements
 * <tt>format</tt> method
 *
 * @author Muhammed Bera KOÇ
 * @since 1.3
 */
public class MessageFormatter extends Formatter {

    public static final MessageFormatter instance = new MessageFormatter();

    private MessageFormatter() {}

    /**
     * A method to format raw logging info.
     *
     * @param logRecord A LogRecord object which contains useful info about logging
     * @return A string of formatted output for log file
     */
    @Override
    public String format(LogRecord logRecord) {
        return logRecord.getSourceClassName() + "::" + logRecord.getSourceMethodName() + "::"
                + new Date(logRecord.getMillis()) + "::" + logRecord.getMessage() + "\n";
    }
}
