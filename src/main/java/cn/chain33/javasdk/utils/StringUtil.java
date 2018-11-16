package cn.chain33.javasdk.utils;

public class StringUtil {
	
	public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
}
