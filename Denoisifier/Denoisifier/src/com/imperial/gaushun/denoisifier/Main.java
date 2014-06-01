package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image noiseImg = new Image(args[0]);
		Image greyImg = new Image(args[0]);
		Noisifier noiseGen = new Noisifier(0.05,false);
		LocalMeanFilter lmfGen = new LocalMeanFilter(5);
		NonLocalMeanFilter nlmfGen = new NonLocalMeanFilter();
		GreyMaker greyGen = new GreyMaker();
		
		noiseGen.addNoise(noiseImg);
		noiseImg.saveImg("png", args[0]+".noise.png");
		
		greyGen.filter(greyImg);
		greyImg.saveImg("png", args[0]+".grey.png");
		
		lmfGen.filter(noiseImg);
		noiseImg.saveImg("png", args[0]+".lmf.png");
		
		nlmfGen.filter(noiseImg);
		noiseImg.saveImg("png", args[0]+".nlmf.png");
	}
}