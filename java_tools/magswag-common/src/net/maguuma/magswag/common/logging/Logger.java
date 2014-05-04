package net.maguuma.magswag.common.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Logger {
  private static final String ERROR = "Error";
  private static final String DEBUG = "Debug";
  private static final String WARN = "Warning";
  private static final String TRACE = "Trace";
  private static final String INFO = "Info";

  private static final Map<String, Boolean> ENABLED = new HashMap<String, Boolean>();

  static {
    ENABLED.put(ERROR, true);
    ENABLED.put(DEBUG, false);
    ENABLED.put(WARN, true);
    ENABLED.put(TRACE, true);
    ENABLED.put(INFO, true);
  }

  public static void log(String type, String info) {
    Boolean okay = ENABLED.get(type);
    if (okay != null && okay == false) {
      return;
    }
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(new Date());
    sb.append("] ");
    sb.append(type);
    sb.append(": ");
    sb.append(info);
    String logMessage = sb.toString();
    System.out.println(logMessage);
  }

  public static void log(String info) {
    debug(info);
  }

  public static void debug(String info) {
    log(DEBUG, info);
  }

  public static void info(String info) {
    log(INFO, info);
  }

  public static void error(String info, Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stack = sw.toString();

    StringBuilder sb = new StringBuilder();
    sb.append(info);
    sb.append("\n");
    sb.append(stack);
    info = sb.toString();
    log(ERROR, info);
  }

  public static void error(String info) {
    log(ERROR, info);
  }

  public static void warn(String info) {
    log(WARN, info);
  }

  public static void trace(String info) {
    log(TRACE, info);
  }
}
