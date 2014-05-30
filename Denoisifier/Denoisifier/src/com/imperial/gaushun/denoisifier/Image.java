package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.imageio.*;

//Currently loads an image (line 13) and then copies it as a PNG (line 14).
//Now generates noise and applies it to the image (demonstrate pixel-by-pixel editing).

public class Image {
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File(args[0]));
			System.out.println("Loading the image at " + args[0] + "\\...");
		    Random rnd = new Random(System.currentTimeMillis());
		    
		    int wImg = img.getWidth();
		    int hImg = img.getHeight();
		    int x,y,RGB,col;
		    
		    System.out.println("Applying noise...");
		    for(int i=0;i<=(wImg*hImg)/4;i++){
			    x = rnd.nextInt(wImg);
			    y = rnd.nextInt(hImg);
			    col = rnd.nextInt(256);
			    RGB = new Color(col,col,col).getRGB();
			    img.setRGB(x,y,RGB);
		    }
		    
		    System.out.println("Saving modified image to " + args[0]+".copy.png" + "\\...");
		    ImageIO.write(img, "png", new File(args[0]+".copy.png"));
		} catch (IOException e) {
			System.out.println("Input/Output Error. Check filepaths.");
		}
	}
};