package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image origImg = new Image(args[0]);
		Image noiseImg = origImg;
		Noisifier noiseGen = new Noisifier(0.25,true);
		LocalMeanFilter lmfGen = new LocalMeanFilter(5);
		
		noiseGen.addNoise(noiseImg);
		noiseImg.saveImg("png", args[0]+".noise.png");
		Image lmfImg = noiseImg;
		Image nlmfImg = noiseImg;
		
		lmfGen.filter(lmfImg);
		lmfImg.saveImg("png", args[0]+".lmf.png");
		
	}
}
