package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Image {
	//Fields
	public int height;
	public int width;
	public BufferedImage img;
	
	//Constructor
	public Image(String filePath) {
		try {
			img = ImageIO.read(new File(filePath));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Input/Output Error. Check filepaths.");
		}
	}
	
	//Methods: Get Colours & RGB
	public int getRed(int x, int y){
		return new Color(img.getRGB(x,y)).getRed();
	}
	public int getGreen(int x, int y){
		return new Color(img.getRGB(x,y)).getGreen();
	}
	public int getBlue(int x, int y){
		return new Color(img.getRGB(x,y)).getBlue();
	}
	public int getRGB(int x, int y){
		return img.getRGB(x,y);
	}
	
	//Methods: Set Colours & RGB
	public void setRed(int x, int y, int r){
		int rgb = new Color(r,getGreen(x,y),getBlue(x,y)).getRGB();
		img.setRGB(x, y, rgb);
	}
	public void setGreen(int x, int y, int g){
		int rgb = new Color(getRed(x,y),g,getBlue(x,y)).getRGB();
		img.setRGB(x, y, rgb);
	}
	public void setBlue(int x, int y, int b){
		int rgb = new Color(getRed(x,y),getGreen(x,y),b).getRGB();
		img.setRGB(x, y, rgb);
	}
	public void setRGB(int x, int y, int rgb){
		img.setRGB(x, y, rgb);
	}
	
	//Method: Save Image
	public void saveImg(String fileType, String filePath){
		try {
		    ImageIO.write(img, fileType, new File(filePath));
		} catch (IOException e) {
			System.out.println("Input/Output Error. Check filepaths.");
		}
	}
};