package graph.core.cli.comparator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CycStringComparator extends StringComparator {
	public static final Pattern MARKUP_PARSER = Pattern
			.compile("\\[\\[[^\\]]+?\\|(.+?)\\]\\]");

	@Override
	protected int compareInternal(Object o1, Object o2) {
		String o1Str = processString(o1);
		String o2Str = processString(o2);
		return super.compareInternal(o1Str, o2Str);
	}

	public static String processString(Object o) {
		// Parsing functions
		String oStr = o.toString();
		while (oStr.startsWith("(") && oStr.length() > 2)
			oStr = oStr.substring(1);

		// Parsing markup
		Matcher m = MARKUP_PARSER.matcher(oStr);
		oStr = m.replaceAll("$1");
		return oStr;
	}
}
