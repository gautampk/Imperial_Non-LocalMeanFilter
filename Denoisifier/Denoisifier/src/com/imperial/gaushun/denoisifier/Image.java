package com.imperial.gaushun.denoisifier;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

//Currently loads an image (line 13) and then copies it as a PNG (line 14).

public class Image {
	public static void main(String[] args) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Gautam\\Desktop\\test.jpg"));
		    ImageIO.write(img, "png", new File("C:\\Users\\Gautam\\Desktop\\test_copy.png"));
		} catch (IOException e) {}
	}
};