package net.horizoncode.randomizer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.horizoncode.randomizer.Main;

public class FileUtil {

	public static List<File> listFiles(File rootDirectory, File excludedDirectory) {
		List<File> files = new ArrayList<>();
		listFiles(rootDirectory, files, excludedDirectory);

		return files;
	}

	private static void listFiles(File path, List<File> collectedFiles, File excludedDirectory) {
		File[] files = path.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				if (!file.getName().equalsIgnoreCase(excludedDirectory.getName()))
					listFiles(file, collectedFiles, excludedDirectory);
			} else {
				collectedFiles.add(file);
			}
		}
	}

	public static List<String> streamToList(InputStream stream) {
		List<String> objects = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String object = "";
		try {
			while ((object = br.readLine()) != null) {
				objects.add(object);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return objects;

	}

}
