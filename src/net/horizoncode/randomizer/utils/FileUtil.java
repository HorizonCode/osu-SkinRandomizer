package net.horizoncode.randomizer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

}
