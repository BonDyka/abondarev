package ru.job4j.base;

/**
 * The class shows checking entering a substring in a origin string.
 *
 * @author abondarev.
 * @since 16.07.2017.
 */
public class StringContain {

	/**
	 * The method take two string as parameters and checks first string contains
	 * inside second or not.
	 *
	 * @param origin is origin string.
	 * @param sub is maybe contained subctring.
	 * @return <i>true</i> if and only if origin string contains substring.
	 */
	public boolean contains(String origin, String sub) {
		if (origin.length() == sub.length()) {
			return origin.equals(sub);
		}

		if (origin.length() < sub.length()) {
			return false;
		}

		boolean result = false;
		char[] originArr = origin.toCharArray();
		char[] subArr = sub.toCharArray();
		for (int i = 0; i < originArr.length - subArr.length; i++) {
			if (originArr[i] == subArr[0]) {
				for (int j = 1; j < subArr.length; j++) {
					if (subArr[j] != originArr[i + j]) {
						result = false;
						break;
					} else {
						result = true;
					}
				}
			}
			if (result) {
				break;
			}
		}
		return result;
	}
}