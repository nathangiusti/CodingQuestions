package codingquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/description/
public class ValidIps {

    public static void main(String[] argv) {
        List<String> ret = restoreIpAddresses("25525511135");
        for (String s : ret)
            System.out.println(s);
    }

    public static List<String> restoreIpAddresses(String s) {
        
        if(s.length()< 4)
            return new ArrayList<String>();

        HashSet<String> sets = getValidIps(s.substring(0,1), s.substring(1));

        ArrayList<String> ret = new ArrayList<String>();

        for (String str : sets)
            ret.add(str);

        return ret;
    }

    private static HashSet<String> getValidIps(String head, String tail) {
        HashSet<String> ret = new HashSet<String>();
        String[] strArr = (head + tail).split("\\.");

        // Are these valid?
        for (int i = 0; i < strArr.length - 1; i++)
            if (strArr[i].length() > 3 || Integer.parseInt(strArr[i]) > 255 || 
                    (strArr[i].startsWith("0") && strArr[i].length() > 1))
                return new HashSet<String>();

        if (strArr.length == 4) {
            if (strArr[3].length() > 3 || Integer.parseInt(strArr[3]) > 255 || 
                    (strArr[3].startsWith("0") && strArr[3].length() > 1))
                return new HashSet<String>();
            else
                ret.add(head + tail);
        } else if (tail.length() > 1) {
            if (!head.endsWith("."))
                ret.addAll(getValidIps(head + "." + tail.substring(0, 1), tail.substring(1)));
            ret.addAll(getValidIps(head + tail.substring(0, 1), tail.substring(1)));
        } else if (tail.length() == 1) {
            if (!head.endsWith("."))
                ret.addAll(getValidIps(head + "." + tail.substring(0, 1), ""));
            ret.addAll(getValidIps(head + tail.substring(0, 1), ""));
        }

        return ret;
    }
}