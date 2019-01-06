/** ****************************************************************************
 * Multiverse 2 Copyright (c) the Multiverse Team 2011.                       *
 * Multiverse 2 is licensed under the BSD License.                            *
 * For more information please check the README.md file included              *
 * with this project.                                                         *
 ***************************************************************************** */
package com.onarandombox.MultiverseCore.utils;

import com.dumptruckman.minecraft.util.Logging;
import java.util.EnumSet;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;

/**
 * Used to determine block/location-related facts.
 *
 * @deprecated Use instead: {@link com.onarandombox.MultiverseCore.api.BlockSafety} and {@link SimpleBlockSafety}.
 */
@Deprecated
public class BlockSafety {

    //This is definitely missing nonSolidBlocks
    private static final Set<Material> nonSolidBlocks = EnumSet.of(
            Material.ACACIA_BUTTON, Material.ACACIA_SAPLING, Material.ACTIVATOR_RAIL, Material.AIR, 
            Material.ALLIUM, Material.ATTACHED_MELON_STEM, Material.ATTACHED_PUMPKIN_STEM, 
            Material.AZURE_BLUET, Material.BEETROOTS, Material.BIRCH_BUTTON, Material.BIRCH_SAPLING, 
            Material.BLACK_CARPET, Material.BLUE_CARPET, Material.BLUE_ORCHID, Material.BRAIN_CORAL, 
            Material.BRAIN_CORAL_FAN, Material.BRAIN_CORAL_WALL_FAN, Material.BROWN_CARPET, Material.BROWN_MUSHROOM, 
            Material.BUBBLE_COLUMN, Material.BUBBLE_CORAL, Material.BUBBLE_CORAL_FAN, Material.BUBBLE_CORAL_WALL_FAN, 
            Material.CARROTS, Material.CAVE_AIR, Material.CHORUS_FLOWER, Material.CHORUS_PLANT, Material.COBWEB, 
            Material.COCOA, Material.COMPARATOR, Material.CREEPER_HEAD, Material.CREEPER_WALL_HEAD, Material.CYAN_CARPET, 
            Material.DANDELION, Material.DARK_OAK_BUTTON, Material.DARK_OAK_SAPLING, Material.DEAD_BUSH, 
            Material.DETECTOR_RAIL, Material.DRAGON_HEAD, Material.DRAGON_WALL_HEAD, Material.END_GATEWAY, 
            Material.END_PORTAL, Material.END_ROD, Material.FERN, Material.FIRE, Material.FIRE_CORAL, 
            Material.FIRE_CORAL_FAN, Material.FIRE_CORAL_WALL_FAN, Material.FLOWER_POT, Material.GRASS, 
            Material.GRAY_CARPET, Material.GREEN_CARPET, Material.HORN_CORAL, Material.HORN_CORAL_FAN, 
            Material.HORN_CORAL_WALL_FAN, Material.JUNGLE_BUTTON, Material.JUNGLE_SAPLING, Material.KELP, 
            Material.KELP_PLANT, Material.LADDER, Material.LARGE_FERN, Material.LAVA, Material.LEVER, 
            Material.LIGHT_BLUE_CARPET, Material.LIGHT_GRAY_CARPET, Material.LILAC, Material.LILY_PAD, 
            Material.LIME_CARPET, Material.MAGENTA_CARPET, Material.MELON_STEM, Material.NETHER_PORTAL, 
            Material.NETHER_WART, Material.OAK_BUTTON, Material.OAK_SAPLING, Material.ORANGE_CARPET, 
            Material.ORANGE_TULIP, Material.OXEYE_DAISY, Material.PEONY, Material.PINK_CARPET, Material.PINK_TULIP, 
            Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD, Material.POPPY, Material.POTATOES, 
            Material.POTTED_ACACIA_SAPLING, Material.POTTED_ALLIUM, Material.POTTED_AZURE_BLUET, 
            Material.POTTED_BIRCH_SAPLING, Material.POTTED_BLUE_ORCHID, Material.POTTED_BROWN_MUSHROOM, 
            Material.POTTED_CACTUS, Material.POTTED_DANDELION, Material.POTTED_DARK_OAK_SAPLING, 
            Material.POTTED_DEAD_BUSH, Material.POTTED_FERN, Material.POTTED_JUNGLE_SAPLING, Material.POTTED_OAK_SAPLING, 
            Material.POTTED_ORANGE_TULIP, Material.POTTED_OXEYE_DAISY, Material.POTTED_PINK_TULIP, Material.POTTED_POPPY, 
            Material.POTTED_RED_MUSHROOM, Material.POTTED_RED_TULIP, Material.POTTED_SPRUCE_SAPLING, 
            Material.POTTED_WHITE_TULIP, Material.POWERED_RAIL, Material.PUMPKIN_STEM, Material.PURPLE_CARPET, 
            Material.RAIL, Material.REDSTONE_TORCH, Material.REDSTONE_WALL_TORCH, Material.REDSTONE_WIRE, 
            Material.RED_CARPET, Material.RED_MUSHROOM, Material.RED_TULIP, Material.REPEATER, Material.ROSE_BUSH, 
            Material.SEAGRASS, Material.SEA_PICKLE, Material.SKELETON_SKULL, Material.SKELETON_WALL_SKULL, Material.SNOW, 
            Material.SPRUCE_BUTTON, Material.SPRUCE_SAPLING, Material.STONE_BUTTON, Material.STRUCTURE_VOID, 
            Material.SUGAR_CANE, Material.SUNFLOWER, Material.TALL_GRASS, Material.TALL_SEAGRASS, Material.TORCH, 
            Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.TUBE_CORAL, Material.TUBE_CORAL_FAN, 
            Material.TUBE_CORAL_WALL_FAN, Material.VINE, Material.VOID_AIR, Material.WALL_TORCH, 
            Material.WATER, Material.WHEAT, Material.WHITE_CARPET, Material.WHITE_TULIP, Material.WITHER_SKELETON_SKULL, 
            Material.WITHER_SKELETON_WALL_SKULL, Material.YELLOW_CARPET, Material.ZOMBIE_HEAD, Material.ZOMBIE_WALL_HEAD
    );
    
    /**
     * This function checks whether the block at the given coordinates are above air or not.
     *
     * @param l The {@link Location} of the block.
     * @return True if the block at that {@link Location} is above air.
     */
    public boolean isBlockAboveAir(Location l) {
        Location downOne = l.clone();
        downOne.setY(downOne.getY() - 1);
        return (downOne.getBlock().getType() == Material.AIR);
    }

    // TODO maybe remove this?
    private boolean blockIsNotSafe(World world, double x, double y, double z) {
        return !playerCanSpawnHereSafely(world, x, y, z);
    }

    /**
     * Checks if a player can spawn safely at the given coordinates.
     *
     * @param world The {@link World}.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param z The z-coordinate.
     * @return True if a player can spawn safely at the given coordinates.
     */
    public boolean playerCanSpawnHereSafely(World world, double x, double y, double z) {
        Location l = new Location(world, x, y, z);
        return playerCanSpawnHereSafely(l);
    }

    /**
     * This function checks whether the block at the coordinates given is safe or not by checking for Lava/Fire/Air etc. This also ensures there is enough space for a player to spawn!
     *
     * @param l The {@link Location}
     * @return Whether the player can spawn safely at the given {@link Location}
     */
    public boolean playerCanSpawnHereSafely(Location l) {
        if (l == null) {
            // Can't safely spawn at a null location!
            return false;
        }

        World world = l.getWorld();
        Location actual = l.clone();
        Location upOne = l.clone();
        Location downOne = l.clone();
        upOne.setY(upOne.getY() + 1);
        downOne.setY(downOne.getY() - 1);

        if (this.isSolidBlock(world.getBlockAt(actual).getType())
                || this.isSolidBlock(upOne.getBlock().getType())) {
            Logging.finest("Error Here (Actual)? (%s)[%s]", actual.getBlock().getType(),
                    this.isSolidBlock(actual.getBlock().getType()));
            Logging.finest("Error Here (upOne)? (%s)[%s]", upOne.getBlock().getType(),
                    this.isSolidBlock(upOne.getBlock().getType()));
            return false;
        }

        if (downOne.getBlock().getType() == Material.LAVA) {
            Logging.finest("Error Here (downOne)? (%s)[%s]", downOne.getBlock().getType(),
                    this.isSolidBlock(downOne.getBlock().getType()));
            return false;
        }

        if (downOne.getBlock().getType() == Material.FIRE) {
            Logging.finest("There's fire below! (%s)[%s]", actual.getBlock().getType(),
                    this.isSolidBlock(actual.getBlock().getType()));
            return false;
        }

        if (isBlockAboveAir(actual)) {
            Logging.finest("Is block above air [%s]", isBlockAboveAir(actual));
            Logging.finest("Has 2 blocks of water below [%s]", this.hasTwoBlocksofWaterBelow(actual));
            return this.hasTwoBlocksofWaterBelow(actual);
        }
        return true;
    }

    /**
     * Gets the location of the top block at the specified {@link Location}.
     *
     * @param l Any {@link Location}.
     * @return The {@link Location} of the top-block.
     */
    public Location getTopBlock(Location l) {
        Location check = l.clone();
        check.setY(127); // SUPPRESS CHECKSTYLE: MagicNumberCheck
        while (check.getY() > 0) {
            if (this.playerCanSpawnHereSafely(check)) {
                return check;
            }
            check.setY(check.getY() - 1);
        }
        return null;
    }

    /**
     * Gets the location of the top block at the specified {@link Location}.
     *
     * @param l Any {@link Location}.
     * @return The {@link Location} of the top-block.
     */
    public Location getBottomBlock(Location l) {
        Location check = l.clone();
        check.setY(0);
        while (check.getY() < 127) { // SUPPRESS CHECKSTYLE: MagicNumberCheck
            if (this.playerCanSpawnHereSafely(check)) {
                return check;
            }
            check.setY(check.getY() + 1);
        }
        return null;
    }

    /*
     * If someone has a better way of this... Please either tell us, or submit a pull request!
     */
    private static boolean isSolidBlock(Material type) {
        return !nonSolidBlocks.contains(type);

    }

    /**
     * Checks if an entity would be on track at the specified {@link Location}.
     *
     * @param l The {@link Location}.
     * @return True if an entity would be on tracks at the specified {@link Location}.
     */
    public boolean isEntitiyOnTrack(Location l) {
        Material currentBlock = l.getBlock().getType();
        return (currentBlock == Material.POWERED_RAIL || currentBlock == Material.DETECTOR_RAIL || currentBlock == Material.RAIL);
    }

    // TODO maybe remove this?
    private void showDangers(Location l) {
        Location actual = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
        Location upOne = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
        Location downOne = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
        upOne.setY(upOne.getY() + 1);
        downOne.setY(downOne.getY() - 1);

        System.out.print("Location Up:   " + upOne.getBlock().getType());
        System.out.print("               " + upOne);
        System.out.print("Location:      " + actual.getBlock().getType());
        System.out.print("               " + actual);
        System.out.print("Location Down: " + downOne.getBlock().getType());
        System.out.print("               " + downOne);
    }

    /**
     * Checks recursively below a {@link Location} for 2 blocks of water.
     *
     * @param l The {@link Location}
     * @return Whether there are 2 blocks of water
     */
    private boolean hasTwoBlocksofWaterBelow(Location l) {
        if (l.getBlockY() < 0) {
            return false;
        }
        Location oneBelow = l.clone();
        oneBelow.subtract(0, 1, 0);
        if (oneBelow.getBlock().getType() == Material.WATER) {
            Location twoBelow = oneBelow.clone();
            twoBelow.subtract(0, 1, 0);
            return (oneBelow.getBlock().getType() == Material.WATER);
        }
        if (oneBelow.getBlock().getType() != Material.AIR) {
            return false;
        }
        return hasTwoBlocksofWaterBelow(oneBelow);
    }

    /**
     * Checks if the specified {@link Minecart} can spawn safely.
     *
     * @param cart The {@link Minecart}.
     * @return True if the minecart can spawn safely.
     */
    public boolean canSpawnCartSafely(Minecart cart) {
        if (this.isBlockAboveAir(cart.getLocation())) {
            return true;
        }
        if (this.isEntitiyOnTrack(LocationManipulation.getNextBlock(cart))) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified {@link Vehicle} can spawn safely.
     *
     * @param vehicle The {@link Vehicle}.
     * @return True if the vehicle can spawn safely.
     */
    public boolean canSpawnVehicleSafely(Vehicle vehicle) {
        if (this.isBlockAboveAir(vehicle.getLocation())) {
            return true;
        }
        return false;
    }

}
