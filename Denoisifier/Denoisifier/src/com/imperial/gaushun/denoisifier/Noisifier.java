package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.util.Random;

public class Noisifier {
	public double frac;
	private Random rg;
	public boolean bw;
	
	public Noisifier(double f, boolean bw){
		frac = f;
		rg = new Random();
		this.bw = bw;
	}
	
	public void addNoise(Image img){
		int rgb = 0;
		int col = 0;
		
		for(int i=0;i<=(img.height*img.width)*frac;i++){
			if(bw == false){
				rgb = new Color(rg.nextInt(256),rg.nextInt(256),rg.nextInt(256)).getRGB();
			}else if(bw == true){
				col = rg.nextInt(256);
				rgb = new Color(col,col,col).getRGB();
			}
			
			img.setRGB(rg.nextInt(img.width),rg.nextInt(img.height),rgb);
		}
	}
}
