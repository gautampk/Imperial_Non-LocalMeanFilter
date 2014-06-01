package com.imperial.gaushun.denoisifier;

public class GreyMaker {
	
	public GreyMaker(){
		
	}
	
	public void filter(Image img){
		int greyLevel;
		
		for(int i=0;i<img.width;i++){
			for(int j=0;j<img.height;j++){
				greyLevel = (img.getRed(i,j)+img.getGreen(i,j)+img.getBlue(i,j))/3;
				img.setRed(i,j,greyLevel);
				img.setGreen(i,j,greyLevel);
				img.setBlue(i,j,greyLevel);
			}
		}
	}
}