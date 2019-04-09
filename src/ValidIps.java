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

        if (s.length() < 4)
            return new ArrayList<String>();

        ArrayList<String> ret = new ArrayList<String>(getValidIps(s.substring(0, 1), s.substring(1)));

        return ret;
    }

    private static HashSet<String> getValidIps(String head, String tail) {
        HashSet<String> ret = new HashSet<String>();
        String curr = head + tail;

        // We have 4 parts to our IP
        if (curr.split("\\.").length == 4) {
            if (isValidIp(curr))
                ret.add(head + tail);
            return ret;
        }

        if (tail.length() > 0) {
            String[] withDot = shift(head + ".", tail);
            String[] withoutDot = shift(head, tail);

            if (isValidIp(head)) {
                ret.addAll(getValidIps(withDot[0], withDot[1]));
                ret.addAll(getValidIps(withoutDot[0], withoutDot[1]));
            }
        }
        return ret;
    }

    private static String[] shift(String head, String tail) {
        if (tail.length() <= 1)
            return new String[] {head + tail, ""};
        else
            return new String[] {head + tail.substring(0, 1), tail.substring(1)};
    }

    private static boolean isValidIp(String input) {
        String[] strArr = input.split("\\.");
        for (String str : strArr)
            if (str.length() > 3 || Integer.parseInt(str) > 255 || (str.startsWith("0") && str.length() > 1))
                return false;
        return true;
    }
}
