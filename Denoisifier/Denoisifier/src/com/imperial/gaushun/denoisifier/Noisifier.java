package com.imperial.gaushun.denoisifier;

import java.util.Random;

public class Noisifier {
	private Random rg;
	
	public Noisifier(){
		rg = new Random();
	}
	
	public void addNoise(Image img, double range, boolean bw){
		int r = 0;
		int g = 0;
		int b = 0;
		double o = 0;
		
		for(int x=0;x<img.width;x++){
			for(int y=0;y<img.height;y++){
			
				if(bw == false){
					o = rg.nextGaussian()*range;
					r = (int) (o + img.getRed(x,y));
					g = (int) (o + img.getGreen(x,y));
					b = (int) (o + img.getBlue(x,y));
					
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
					r = (img.getRed(x,y) + img.getBlue(x,y) + img.getGreen(x,y))/3;
					r = (int) (rg.nextGaussian()*range + r);
					
					if(r > 255){
						r = 255;
					}else if(r < 0){
						r = 0;
					}
					
					img.setRed(x,y,r);
					img.setGreen(x,y,r);
					img.setBlue(x,y,r);
				}
			}
		}
	}
}