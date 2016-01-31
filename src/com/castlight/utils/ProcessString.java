package com.castlight.utils;

public class ProcessString {

	public String getModifiedString(String inputs) {

		inputs = "NULL".equalsIgnoreCase(inputs) ? inputs : "'" + inputs + "'";
		return inputs.replaceAll("\n", "");
	}

}
