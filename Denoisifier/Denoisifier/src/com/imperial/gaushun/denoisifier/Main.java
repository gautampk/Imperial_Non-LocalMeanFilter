package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image nlmfImg = new Image(args[0]);
		Image greyImg = new Image(args[0]);
	
		Noisifier noiseGen = new Noisifier(0.3,50,true);
		LocalMeanFilter lmfGen = new LocalMeanFilter(3);
		NonLocalMeanFilter nlmfGen = new NonLocalMeanFilter(1,1);
		GreyMaker greyGen = new GreyMaker();
		
		greyGen.filter(greyImg);
		greyGen.filter(nlmfImg);
		greyImg.saveImg("png", args[0]+".grey.png");
		
		noiseGen.addNoise(greyImg);
		greyGen.filter(nlmfImg);
		greyImg.saveImg("png", args[0]+".noise.png");
		
		nlmfGen.filter(greyImg,nlmfImg);
		nlmfImg.saveImg("png", args[0]+".nlmf.png");
	}
}