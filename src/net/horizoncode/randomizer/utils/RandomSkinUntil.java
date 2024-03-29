package net.horizoncode.randomizer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import javafx.application.Platform;
import net.horizoncode.randomizer.Main;

public class RandomSkinUntil {

	private List<File> files;
	private List<String> filter;
	private List<File> outputFiles;
	private File outputDirectory;
	private Random rnd;

	public RandomSkinUntil(List<File> files, List<String> filter, File output) {
		this.files = files;
		this.filter = filter;
		this.outputDirectory = output;
		outputFiles = new ArrayList<File>();
		this.rnd = new Random();
		if (output.exists()) {
			FileUtil.listFiles(output, null).stream().forEach(File::delete);
		} else {
			output.mkdir();
		}
		pickFiles();
		prepareOutput();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				RenderUtils.renderDialog(Main.mainController.stackPane, "Success", "Done!", "Okay");
			}
		});
	}

	private void prepareOutput() {
		for (File out : outputFiles) {
			try {
				FileUtils.copyFileToDirectory(out, outputDirectory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void pickFiles() {
		for (String fileName : filter) {
			List<File> filteredFiles = files.stream().filter(file -> file.getName().toLowerCase().contains(fileName.toLowerCase())).collect(Collectors.toList());
			if (filteredFiles.size() <= 0)
				continue;
			File random = filteredFiles.get(rnd.nextInt(filteredFiles.size()));
			if (random == null)
				continue;
			outputFiles.add(random);
		}
	}
}
