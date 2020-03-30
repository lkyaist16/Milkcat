package designpatten.chainofresponsibility;

public class Demo {

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);

        errorLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(debugLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }

}
