package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image nlmfImg = new Image(args[0]);
		Image greyImg = new Image(args[0]);
	
		Noisifier noiseGen = new Noisifier(0.1,50,false);
		LocalMeanFilter lmfGen = new LocalMeanFilter(3);
		NonLocalMeanFilter nlmfGen = new NonLocalMeanFilter(1);
		GreyMaker greyGen = new GreyMaker();
		
		greyGen.filter(nlmfImg);
		greyGen.filter(greyImg);
		greyImg.saveImg("png", args[0]+".grey.png");
		
		noiseGen.addNoise(nlmfImg);
		nlmfImg.saveImg("png", args[0]+".noise.png");
		
		nlmfGen.filter(nlmfImg);
		nlmfImg.saveImg("png", args[0]+".nlmf.png");
	}
}