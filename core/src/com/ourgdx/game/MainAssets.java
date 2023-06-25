package com.ourgdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ourgdx.game.AssetManagment.Asset;

public enum MainAssets implements Asset {



	text("skin/commodore-64.fnt", BitmapFont.class,"skin/uiskin.atlas"),
	skin("skin/uiskin.json", Skin.class, "skin/uiskin.atlas");

	// The Enum's Variable's and Constructor
	private final String fileLocation;
	private final Class fileType;
	private final String paramLocation;
	private MainAssets(String Location, Class Type, String Parameter) {
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
