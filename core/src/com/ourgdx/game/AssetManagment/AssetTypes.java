package com.ourgdx.game.AssetManagment;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public enum AssetTypes {
    texture(Texture.class),
    sound(Sound.class),
    music(Music.class),
    skin(Skin.class),
    font(BitmapFont.class);


    private final Class fileType;

    private AssetTypes(Class Type) {
        this.fileType = Type;
    }
    public static Class getTypeFromInt(int code){
        return AssetTypes.values()[code].getFileType();
    }

    public Class getFileType() {
        return fileType;
    }


}
