package com.ccf.util;

public class FormatTransformer {
	
	public int[] transformString2IntegerArray(String target, String regex) {
		String[] stringArray = target.split(regex);
		int[] integerArray = new int[stringArray.length];
		for (int i=0; i<integerArray.length; i++) {
			integerArray[i] = Integer.parseInt(stringArray[i]);
		}
		return integerArray;
	}
	
	public String transformIntegerArray2String(int[] target,  String delimiter) {
		String result = "";
		for (int i=0; i<target.length; i++) {
			result = result + String.valueOf(target[i]) + delimiter;
		}
		return result;
	}
	
}
