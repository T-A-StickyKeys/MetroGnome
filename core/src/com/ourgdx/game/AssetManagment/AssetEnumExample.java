package com.ourgdx.game.AssetManagment;

import com.badlogic.gdx.graphics.Texture;

/***
 * ASSET EXAMPLE
 *
 * This file is an example of an Asset Enum, using the AssetInterface.
 *
 * See the AssetInterface for more information.
 *
 * @author Traae
 * @version 1
 */
public enum AssetEnumExample implements Asset {

    // The Enumerations
    example("/badlogic.jpg", Texture.class, null);


    // The Enum's Variable's and Constructor
    private final String fileLocation;
    private final Class fileType;
    private final String paramLocation;
    private AssetEnumExample(String Location, Class Type, String Parameter) {
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
