package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.util.Random;

public class Noisifier {
	public double frac;
	private Random rg;
	public boolean bw;
	public int diff;
	
	public Noisifier(double f, int diff, boolean bw){
		frac = f;
		rg = new Random();
		this.bw = bw;
		this.diff = diff;
	}
	
	public void addNoise(Image img){
		int rgb = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		int x = 0;
		int y = 0;
		int col = 0;
		
		for(int i=0;i<=(img.height*img.width)*frac;i++){
			if(bw == false){
				x = rg.nextInt(img.width);
				y = rg.nextInt(img.height);
				
				r = img.getRed(x,y) + ((rg.nextInt(2*diff))-diff);
				g = img.getGreen(x,y) + ((rg.nextInt(2*diff))-diff);
				b = img.getBlue(x,y) + ((rg.nextInt(2*diff))-diff);
				
				if(r > 255){
					r = 255;
				}else if(r < 0){
					r = 0;
				}
				if(g > 255){
					g = 255;
				}else if(g < 0){
					g = 0;
				}
				if(b > 255){
					b = 255;
				}else if(b < 0){
					b = 0;
				}
				
				img.setRed(x,y,r);
				img.setGreen(x,y,g);
				img.setBlue(x,y,b);
			}else if(bw == true){
				col = rg.nextInt(256);
				img.setRGB(rg.nextInt(img.width),rg.nextInt(img.height),rgb);
			}
		}
	}
}