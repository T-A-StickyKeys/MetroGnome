package com.ourgdx.game.screens.Pong;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.ourgdx.game.AssetManagment.Asset;

public enum PongAssets implements Asset {

    // The Enumerations
    ballPlain("pongAssets/images/ballPlain.png", Texture.class, null),
    ballRound("pongAssets/images/ballRound.png", Texture.class, null),
    paddle("pongAssets/images/PaddlePlain.png", Texture.class, null),
    music("pongAssets/music/anything.mp3", Music.class, null),
    hitPaddle("pongAssets/soundFX/hitPaddle.wav", Sound.class, null),
    hitWall("pongAssets/soundFX/hitWall.wav", Sound.class, null),
    score("pongAssets/soundFX/score.wav", Sound.class, null),
    serve("pongAssets/soundFX/score.wav", Sound.class, null);

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

