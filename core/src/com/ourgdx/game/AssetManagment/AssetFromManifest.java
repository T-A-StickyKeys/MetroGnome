package com.ourgdx.game.AssetManagment;

import java.io.Serializable;

public class AssetFromManifest implements Asset {
    private String fileLocation;
    private Class fileType;
    private String parameter;

    public AssetFromManifest(String fileLocation, Class fileType, String parameter){
        this.fileLocation = fileLocation;
        this.fileType = fileType;
        this.parameter = parameter;
    }
    public AssetFromManifest(String fileLocation, AssetTypes fileType, String parameter){
        this.fileLocation = fileLocation;
        this.fileType = fileType.getFileType();
        this.parameter = parameter;
    }
    public AssetFromManifest(String fileLocation, int fileType, String parameter){
        this.fileLocation = fileLocation;
        this.fileType = AssetTypes.getTypeFromInt(fileType);
        this.parameter = parameter;
    }

    @Override
    public String location() {
        return fileLocation;
    }

    @Override
    public Class type() {
        return fileType;
    }

    @Override
    public String parameter() {
        return parameter;
    }
}
