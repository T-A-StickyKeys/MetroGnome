package com.ourgdx.game.AssetManagment;

import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;


public class GameAssetManager {

	public final com.badlogic.gdx.assets.AssetManager manager;
	private static GameAssetManager INSTANCE = null;

	
	private GameAssetManager(){
		manager = new com.badlogic.gdx.assets.AssetManager();
	}
	public static GameAssetManager instance() {
		if (INSTANCE == null){
			INSTANCE = new GameAssetManager();
		}
		return INSTANCE;
	}


	public void loadAsset(Asset a){
		if (a.parameter() == null){
			manager.load(a.location(), a.type());
		}
		else if (a.type() == Skin.class){
			SkinParameter param = new SkinParameter(a.parameter());
			manager.load(a.location(), a.type(), param);
		}
		else if (a.type() == BitmapFont.class){
			BitmapFontLoader.BitmapFontParameter param = new BitmapFontLoader.BitmapFontParameter();
			param.atlasName = a.parameter();
			manager.load(a.location(), a.type(), param);
		}
	}
	public void unloadAsset(Asset toUnload){
		manager.unload(toUnload.location());
	}


	public void loadAll(Asset[] assets){
		for (Asset a : assets){
			loadAsset(a);
		}
	}
	public void unloadAll(Asset[] assets){
		for (Asset a : assets){
			unloadAsset(a);
		}
	}

	public void finish(){
		manager.finishLoading();
	}
	public <T> T get(Asset a){
		return manager.get(a.location());
	}






}
