package net.tuxun.customer.module.admin.util;

public class UserUtil {
  public static String trimAreaCode(String areaCode) {
    String shortAreaCode = "";
    if (areaCode == null)
      return "";
    if (areaCode.endsWith("00000000")) {
      shortAreaCode = areaCode.substring(0, 2);
    } else if (areaCode.endsWith("000000")) {
      shortAreaCode = areaCode.substring(0, 4);
    } else if (areaCode.endsWith("0000")) {
      shortAreaCode = areaCode.substring(0, 6);
    } else if (areaCode.endsWith("00")) {
      shortAreaCode = areaCode.substring(0, 8);
    } else {
      shortAreaCode = areaCode;
    }
    return shortAreaCode;
  }
}
