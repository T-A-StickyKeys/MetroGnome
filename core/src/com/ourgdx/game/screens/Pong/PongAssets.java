package com.ourgdx.game.screens.Pong;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ourgdx.game.AssetManagment.Asset;

import java.util.LinkedList;

public enum PongAssets implements Asset {

	hitPaddle("soundFX/hitPaddle.wav", Sound.class, null),
	hitWall("soundFX/hitWall.wav", Sound.class, null),
	score("soundFX/score.wav", Sound.class, null),
	serve("soundFX/serve.wav", Sound.class, null),
	bgMusic("music/anything.mp3", Music.class, null),
	paddle("images/paddlePlain.png", Texture.class, null),
	ball("images/ballPlain.png", Texture.class, null);


	// The Enum's Variable's and Constructor
	private final String fileLocation;
	private final Class fileType;
	private final String paramLocation;
	private PongAssets(String Location, Class Type, String Parameter) {
		this.fileLocation = Location;
		this.fileType = Type;
		this.paramLocation = Parameter;
	}

	// The Interface Methods
	@Override
	public String location() {
		return this.fileLocation;
	}

	@Override
	public Class type() {
		return this.fileType;
	}

	@Override
	public String parameter(){
		return this.paramLocation;
	}
}
