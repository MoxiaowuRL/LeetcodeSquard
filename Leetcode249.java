// We can shift a string by shifting each of its letters to its successive letter.

// For example, "abc" can be shifted to be "bcd".
// We can keep shifting the string to form a sequence.

// For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
// Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
// Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
// Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

class LeetCode249 {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strings) {
			String hashKey = hash(s);
			if (!map.containsKey(hashKey)) {
				map.put(hashKey, new ArrayList<>());
			}
			map.get(hashKey).add(s);
		}
		for (String s : map.keySet()) {
			result.add(map.get(s));
		}
		return result;
	}

	private String hash(String s) {

		StringBuilder hashKey = new StringBuilder();

		for (int i = 1; i < s.length(); i++) {
			hashKey.append((char) ((s.charAt(i) - s.charAt(i - 1) + 26) % 26));
		}
		System.out.println(s + ", " + hashKey.toString());
		return hashKey.toString();
	}
}