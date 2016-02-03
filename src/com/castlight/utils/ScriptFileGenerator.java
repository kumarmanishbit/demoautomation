package com.castlight.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ScriptFileGenerator {

	public boolean writeToFile(String query) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Applications/XAMPP/htdocs/demoautomation/MENT-0001.sql"), "utf-8"));
			writer.write(query);
			System.out.println("Done writing file...");
			
		} catch (IOException ex) {
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
		return true;
	}
}
