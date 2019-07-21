package java.rpn.util;

import java.util.ArrayList;
import java.util.HashMap;

public class OperatorUtil {
    final static ArrayList<String> operatorList = new ArrayList<String>(){{
        add("+");
        add("-");
        add("/");
        add("*");
        add("sqrt");
    }};

    final static HashMap<String, String> reverseMapper = new HashMap<String,String>(){{
        put("+", "-");
        put("-", "+");
        put("/", "*");
        put("*", "/");
    }};

    public static boolean isOperator(String operator) {
        return operatorList.contains(operator);
    }

    public static String reverseOperator(String operator) {
        return reverseMapper.get(operator);
    }

    public static boolean isSqrt(String operator) {
        return operator.equals("sqrt");
    }
}
