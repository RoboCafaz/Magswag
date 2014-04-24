package net.maguuma.magswag.common.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class Logger {
  private static final String ERROR = "Error";
  private static final String DEBUG = "Debug";
  private static final String WARN = "Warning";
  private static final String TRACE = "Trace";

  public static void log(String type, String info) {
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
