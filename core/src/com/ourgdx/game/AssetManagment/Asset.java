package com.ourgdx.game.AssetManagment;

/***
 * ASSET INTERFACE
 *
 * The Idea is to have an enum that can be implemented in multiple file,
 * each with the assets for a specific thing or type or screen, for organization.
 *
 * Enums are not extendable.
 * Instead, we can use an interface, which will be accepted by the AssetManager.
 *
 * See the AssetExample for an example implementation.
 *
 * @author Traae
 * @version 1
 */
public interface Asset {
    public String location();
    public Class type();
    public String parameter();
}
