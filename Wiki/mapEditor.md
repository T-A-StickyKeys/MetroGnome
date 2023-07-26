# Map Editor

A built-in program for designing and building levels with a visual tool.

## Requirements (Currently)

- We're using 64x64 tiles.
- Sprites can be given in a sprite sheet, or multiple files.
- The program must breakdown the sheets into tiles.
  - I should make it so I can adjust scale, squish and stretch pictures into the right size
- Our levels will have a set of varying properties across the tiles,
  - Walls
  - Ramps
  - holes
  - damage zones: spikes, lava, etcetera
  - trigger zones: buttons, traps, keyholes
  - Alternate Physics: ice, sticky tar
  - special interaction zones (a little indent for sliding block puzzles)
  - Doors to other levels
- Our levels have a depth dimension
  - We need pokemon and zelda esc ledges and ramps that take place on the same screen,
  - We also need holes / Bridges / ladders that move a complete floor down or up
- We need te be able to place enemies, objects, blocks doors, npcs, and event triggers
- The file needs to know
  - The layout
  - All the corresponding tiles
  - Needs to list/track the properties of all the different tiles
  - All the corresponding THINGS to put on top of them.

## Thoughts
- we only really need several types of tiles, like the pac-an map, but being able to assign different variations of sprites would be cool.
- Should tiles have properties attached to them, or should their be a complete seperation of the sprite and the tile?
  - It would be really annoying to want to draw a wall, but then you have to put both every time.
  - Why not just have objects make up the walls?
- Some objects, like enemies and devices will need to have thier own files,
  - They don't need to be showin in the editor, I could just show a colored shape, but if I do that for all the objects like walls, it's going to get out of control
  - What's my balance between granualiry and reasonability?

## Solutions
- Properties that are assigned to the ground/world will directly correlate to the map. Walls is walls.
- Other things like ice will be an object that is placed on top.
- There are no ramps, just tiles that do not distinguish their z axis. All ramp/ledge z axis is an ilusion created by the properties of those tiles. 
  - But what about walking on top of blocks? their should be a descrete height property

## Requirements Engineered

A level is a screen or board, it can have doors to other levels, A Map / The Map is the manifest of all the levels and their connections

- the level will have certain types of tile with specific properties
  - Wall
  - Floor
  - TODO decide on the ledge ramp problem
  - indent/special
- The level will mark tile as type, z value, and variation
  - floor: various sprites for variety
  - Wall: Left wall, right wall, right-bottom corner ecetera + variety. 
  - Ledge: left, right, up, down,
- The level will list 1 floor
  - You can build depth inside the floor with z value
  - holes/ladders can lead to another map like a door
- Ice, Tar, Lava are obstacle objects that can be placed over a tile. They are responsible for changing the properties.
- Enemies and objects can be assigned to a starting position
- The level can must be at least 2x1 or 1x2, other wise it can be as big as you want. The screen will show 16x16, but can scroll to area beyond that.
  - Come up with an abirtray limit, but the map editor must let you edit the size.
- The Editor needs to let you save individual levels and the overall map
- A level will need a multiple dictionaries to mark all of its internal parts with it's external assets
  - tiles - sprites
  - Objects - Object files
  - Door - map markers
  

## Related features but part of a different modules
- A save file should be applied to the map, to allow persistent changes
- 