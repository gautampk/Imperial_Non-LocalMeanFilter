package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.imageio.*;

//Currently loads an image (line 13) and then copies it as a PNG (line 14).
//Now generates noise and applies it to the image (demonstrate pixel-by-pixel editing).
//Need to supply command line option now:
//**> cd /path/to/download/folder
//**> cd bin
//**> java com.imperial.gaushun.denoisifier.Image "/path/to/image"

//TODO: Denoised image is very dark.

public class Image {
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File(args[0]));
			System.out.println("Loading the image at " + args[0] + " ...");
		    Random rnd = new Random(System.currentTimeMillis());
		    
		    int wImg = img.getWidth();
		    int hImg = img.getHeight();
		    int x,y,RGB,col;
		    
		    System.out.println("Applying noise ...");
		    for(int i=0;i<=(wImg*hImg)/1.2;i++){
			    x = rnd.nextInt(wImg);
			    y = rnd.nextInt(hImg);
			    col = rnd.nextInt(256);
			    RGB = new Color(col,col,col).getRGB();
			    img.setRGB(x,y,RGB);
		    }
		    
		    System.out.println("Saving noised image to " + args[0]+".noise.png" + " ...");
		    ImageIO.write(img, "png", new File(args[0]+".noise.png"));
		    
		    for(int i=1;i<wImg;i+=3){
		    	for(int j=1;j<hImg;j+=3){
		    		if(i>=wImg-1 || j>=hImg-1){
		    			break;
		    		}
		    		int[] pxlRGB = {0,0,0,0,0,0,0,0,0};
		    		pxlRGB[0] = img.getRGB(i-1,j+1);
		    		pxlRGB[0] = img.getRGB(i,j+1);
		    		pxlRGB[0] = img.getRGB(i+1,j+1);
		    		pxlRGB[0] = img.getRGB(i-1,j);
		    		pxlRGB[0] = img.getRGB(i,j);
		    		pxlRGB[0] = img.getRGB(i+1,j);
		    		pxlRGB[0] = img.getRGB(i-1,j-1);
		    		pxlRGB[0] = img.getRGB(i,j-1);
		    		pxlRGB[0] = img.getRGB(i+1,j-1);
		    		
		    		int[] meanRGB = {0,0,0,0};
		    		for(int k=0;k<=8;k++){
		    			meanRGB[0] += new Color(pxlRGB[k]).getRed();
		    			meanRGB[1] += new Color(pxlRGB[k]).getGreen();
		    			meanRGB[2] += new Color(pxlRGB[k]).getBlue();
		    		}
		    		meanRGB[0] /= 9;
		    		meanRGB[1] /= 9;
		    		meanRGB[2] /= 9;
		    		meanRGB[3] = new Color(meanRGB[0], meanRGB[1], meanRGB[2]).getRGB();
		    		
		    		img.setRGB(i-1,j-1,meanRGB[3]);
		    		img.setRGB(i,j-1,meanRGB[3]);
		    		img.setRGB(i+1,j-1,meanRGB[3]);
		    		img.setRGB(i-1,j,meanRGB[3]);
		    		img.setRGB(i,j,meanRGB[3]);
		    		img.setRGB(i+1,j,meanRGB[3]);
		    		img.setRGB(i-1,j+1,meanRGB[3]);
		    		img.setRGB(i,j+1,meanRGB[3]);
		    		img.setRGB(i+1,j+1,meanRGB[3]);
		    	}
		    }
		    
		    System.out.println("Saving denoised image to " + args[0]+".clean.png" + " ...");
		    ImageIO.write(img, "png", new File(args[0]+".clean.png"));
		} catch (IOException e) {
			System.out.println("Input/Output Error. Check filepaths.");
		}
	}
};