package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image origImg = new Image(args[0]);
		Image noiseImg = origImg;
		Noisifier noiseGen = new Noisifier(0.1,true);
		
		noiseGen.addNoise(noiseImg);
		noiseImg.saveImg("png", args[0]+".noise.png");
		Image lmf = noiseImg;
		Image nlmf = noiseImg;
	}
}
