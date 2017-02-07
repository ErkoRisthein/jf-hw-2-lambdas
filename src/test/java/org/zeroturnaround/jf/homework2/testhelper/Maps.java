package org.zeroturnaround.jf.homework2.testhelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {

	public static <K, V> Map<K, V> of(K k1, V v1) {
		return new LinkedHashMap<K, V>() {{
			put(k1, v1);
		}};
	}

	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
		return new LinkedHashMap<K, V>() {{
			put(k1, v1);
			put(k2, v2);
		}};
	}

	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
		return new LinkedHashMap<K, V>() {{
			put(k1, v1);
			put(k2, v2);
			put(k3, v3);
		}};
	}

	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
		return new LinkedHashMap<K, V>() {{
			put(k1, v1);
			put(k2, v2);
			put(k3, v3);
			put(k4, v4);
		}};
	}

	public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
		return new LinkedHashMap<K, V>() {{
			put(k1, v1);
			put(k2, v2);
			put(k3, v3);
			put(k4, v4);
			put(k5, v5);
		}};
	}
}
