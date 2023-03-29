/* This file is part of Vault.

    Vault is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Vault is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Vault.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.taco.api.chat;

import com.taco.api.perms.Permissions;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * The main Chat API - allows for Prefix/Suffix nodes along with generic Info nodes if the linked Chat system supports them
 *
 */
public abstract class Chat {

    private final Permissions perms;

    public Chat(Permissions perms) {
        this.perms = perms;
    }
    /**
     * Gets name of permission method
     * @return Name of Permission Method
     */
    abstract public String getName();

    /**
     * Checks if permission method is enabled.
     * @return Success or Failure
     */
    abstract public boolean isEnabled();

    /**
     * Get players prefix
     * @param world World name
     * @param player Player name
     * @return Prefix
     */
    abstract public String getPlayerPrefix(World world, UUID player);

    /**
     * Get a players prefix in the given world
     * Use NULL for world if requesting a global prefix
     *
     * @param world World name
     * @param player OfflinePlayer
     * @return Prefix
     */
    public String getPlayerPrefix(World world, OfflinePlayer player) {
        return getPlayerPrefix(world, player.getUniqueId());
    }

    /**
     * Get players prefix from the world they are currently in.
     * May or may not return the global prefix depending on implementation.
     *
     * @param player Player Object
     * @return Prefix
     */
    public String getPlayerPrefix(Player player) {
        return getPlayerPrefix(player.getWorld(), player);
    }

    /**
     * Set players prefix
     * @param world World name
     * @param player Player name
     * @param prefix Prefix
     */
    abstract public void setPlayerPrefix(World world, UUID player, String prefix);

    /**
     * Sets players prefix in the given world.
     * Use NULL for world for setting in the Global scope.
     *
     * @param world World name
     * @param player OfflinePlayer
     * @param prefix Prefix
     */
    public void setPlayerPrefix(World world, OfflinePlayer player, String prefix) {
        setPlayerPrefix(world, player.getUniqueId(), prefix);
    }

    /**
     * Set players prefix in the world they are currently in.
     *
     * @param player Player Object
     * @param prefix Prefix
     */
    public void setPlayerPrefix(Player player, String prefix) {
        setPlayerPrefix(player.getWorld(), player, prefix);
    }

    /**
     * Get players suffix
     * @param world World name
     * @param player Player name
     * @return Suffix
     */
    abstract public String getPlayerSuffix(World world, UUID player);

    /**
     * Get players suffix in the specified world.
     *
     * @param world World name
     * @param player OfflinePlayer name
     * @return Suffix
     */
    public String getPlayerSuffix(World world, OfflinePlayer player) {
        return getPlayerSuffix(world, player.getUniqueId());
    }

    /**
     * Get players suffix in the world they are currently in.
     *
     * @param player Player Object
     * @return Suffix
     */
    public String getPlayerSuffix(Player player) {
        return getPlayerSuffix(player.getWorld(), player);
    }

    /**
     * Set players suffix
     * @param world World name
     * @param player Player name
     * @param suffix Suffix
     */
    abstract public void setPlayerSuffix(World world, UUID player, String suffix);

    /**
     * Set players suffix for the world specified
     *
     * @param world World name
     * @param player OfflinePlayer
     * @param suffix Suffix
     */
    public void setPlayerSuffix(World world, OfflinePlayer player, String suffix) {
        setPlayerSuffix(world, player.getUniqueId(), suffix);
    }

    /**
     * Set players suffix in the world they currently occupy.
     *
     * @param player Player Object
     * @param suffix Suffix
     */
    public void setPlayerSuffix(Player player, String suffix) {
        setPlayerSuffix(player.getWorld(), player, suffix);
    }

    /**
     * Get group prefix
     * @param world World name
     * @param group Group name
     * @return Prefix
     */
    abstract public String getGroupPrefix(World world, String group);

    /**
     * Get group suffix
     * @param world World name
     * @param group Group name
     * @return Suffix
     */
    abstract public String getGroupSuffix(World world, String group);

    /**
     * Set group suffix
     * @param world World name
     * @param group Group name
     * @param suffix Suffix
     */
    abstract public void setGroupSuffix(World world, String group, String suffix);

    /**
     * Get a players informational node (Integer) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(World world, OfflinePlayer player, String node, int defaultValue) {
        return getPlayerInfoInteger(world, player.getUniqueId(), node, defaultValue);
    }

    /**
     * Get a players informational node (Integer) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public int getPlayerInfoInteger(World world, UUID player, String node, int defaultValue);

    /**
     * Get a players informational node (Integer) value
     * @param player Player Object
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(Player player, String node, int defaultValue) {
        return getPlayerInfoInteger(player.getWorld(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Integer) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoInteger(World world, OfflinePlayer player, String node, int value) {
        setPlayerInfoInteger(world, player.getUniqueId(), node, value);
    }

    /**
     * Set a players informational node (Integer) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setPlayerInfoInteger(World world, UUID player, String node, int value);

    /**
     * Set a players informational node (Integer) value
     * @param player Player Object
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoInteger(Player player, String node, int value) {
        setPlayerInfoInteger(player.getWorld(), player, node, value);
    }

    /**
     * Get a groups informational node (Integer) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public int getGroupInfoInteger(World world, String group, String node, int defaultValue);

    /**
     * Set a groups informational node (Integer) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoInteger(World world, String group, String node, int value);

    /**
     * Get a players informational node (Double) value
     * @param world World name
     * @param playerId Player UUID
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public double getPlayerInfoDouble(World world, UUID playerId, String node, double defaultValue);

    /**
     * Get a players informational node (Double) value
     * @param world World Object
     * @param player Player name
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(World world, OfflinePlayer player, String node, double defaultValue) {
        return getPlayerInfoDouble(world, player.getUniqueId(), node, defaultValue);
    }

    /**
     * Get a players informational node (Double) value
     * @param player Player Object
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(Player player, String node, double defaultValue) {
        return getPlayerInfoDouble(player.getWorld(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Double) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setPlayerInfoDouble(World world, UUID player, String node, double value);

    /**
     * Set a players informational node (Double) value
     * @param world World Object
     * @param player Player name
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoDouble(World world, OfflinePlayer player, String node, double value) {
        setPlayerInfoDouble(world, player.getUniqueId(), node, value);
    }

    /**
     * Set a players informational node (Double) value
     * @param player Player Object
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoDouble(Player player, String node, double value) {
        setPlayerInfoDouble(player.getWorld(), player, node, value);
    }

    /**
     * Get a groups informational node (Double) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public double getGroupInfoDouble(World world, String group, String node, double defaultValue);

    /**
     * Set a groups informational node (Double) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoDouble(World world, String group, String node, double value);

    /**
     * Get a players informational node (Boolean) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(World world, OfflinePlayer player, String node, boolean defaultValue) {
        return getPlayerInfoBoolean(world, player.getUniqueId(), node, defaultValue);
    }

    /**
     * Get a players informational node (Boolean) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public boolean getPlayerInfoBoolean(World world, UUID player, String node, boolean defaultValue);

    /**
     * Get a players informational node (Boolean) value
     * @param player Player Object
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(Player player, String node, boolean defaultValue) {
        return getPlayerInfoBoolean(player.getWorld(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Boolean) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(World world, OfflinePlayer player, String node, boolean value) {
        setPlayerInfoBoolean(world, player.getUniqueId(), node, value);
    }

    /**
     * Set a players informational node (Boolean) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setPlayerInfoBoolean(World world, UUID player, String node, boolean value);

    /**
     * Set a players informational node (Boolean) value
     * @param player Player Object
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoBoolean(Player player, String node, boolean value) {
        setPlayerInfoBoolean(player.getWorld(), player, node, value);
    }

    /**
     * Get a groups informational node (Boolean) value
     * @param world Name of World
     * @param group Name of Group
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public boolean getGroupInfoBoolean(World world, String group, String node, boolean defaultValue);

    /**
     * Set a groups informational node (Boolean) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoBoolean(World world, String group, String node, boolean value);

    /**
     * Get a players informational node (String) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(World world, OfflinePlayer player, String node, String defaultValue) {
        return getPlayerInfoString(world, player.getUniqueId(), node, defaultValue);
    }

    /**
     * Get a players informational node (String) value
     * @param world World name
     * @param player Player uuid
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public String getPlayerInfoString(World world, UUID player, String node, String defaultValue);

    /**
     * Get a players informational node (String) value
     * @param player Player Object
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(Player player, String node, String defaultValue) {
        return getPlayerInfoString(player.getWorld(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (String) value
     * @param world World name
     * @param player OfflinePlayer
     * @param node Permission node
     * @param value Value to set
     */
    public void setPlayerInfoString(World world, OfflinePlayer player, String node, String value) {
        setPlayerInfoString(world, player.getUniqueId(), node, value);
    }

    /**
     * Set a players informational node (String) value
     * @param world World name
     * @param player Player name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setPlayerInfoString(World world, UUID player, String node, String value);

    /**
     * Set a players informational node (String) value
     * @param player Player Object
     * @param node Permission node
     * @param value Value ot set
     */
    public void setPlayerInfoString(Player player, String node, String value) {
        setPlayerInfoString(player.getWorld(), player, node, value);
    }

    /**
     * Get a groups informational node (String) value
     * @param world Name of World
     * @param group Name of Group
     * @param node Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public String getGroupInfoString(World world, String group, String node, String defaultValue);

    /**
     * Set a groups informational node (String) value
     * @param world World name
     * @param group Group name
     * @param node Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoString(World world, String group, String node, String value);

    /**
     * Check if player is member of a group.
     * @param world World name
     * @param player Player name
     * @param group Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(World world, OfflinePlayer player, String group) {
        return perms.playerInGroup(world, player.getUniqueId(), group);
    }

    /**
     * Check if player is member of a group.
     * @param world World Object
     * @param player Player name
     * @param group Group name
     * @return Success or Failure
     */
    public abstract boolean playerInGroup(World world, UUID player, String group);

    /**
     * Check if player is member of a group.
     * @param player Player Object
     * @param group Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(Player player, String group) {
        return playerInGroup(player.getWorld(), player, group);
    }

    /**
     * Gets the list of groups that this player has
     * @param world World name
     * @param player OfflinePlayer
     * @return Array of groups
     */
    public String[] getPlayerGroups(World world, OfflinePlayer player) {
        return perms.getPlayerGroups(world, player);
    }

    /**
     * Gets the list of groups that this player has
     * @param world World name
     * @param player Player name
     * @return Array of groups
     */
    public String[] getPlayerGroups(World world, UUID player) {
        return perms.getPlayerGroups(world, player);
    }

    /**
     * Gets the list of groups that this player has
     * @param world World Object
     * @param player Player name
     * @return Array of groups
     */
    public String[] getPlayerGroups(World world, Player player) {
        return getPlayerGroups(world, player.getUniqueId());
    }

    /**
     * Gets the list of groups that this player has
     * @param player Player Object
     * @return Array of groups
     */
    public String[] getPlayerGroups(Player player) {
        return getPlayerGroups(player.getWorld(), player);
    }

    public String getPrimaryGroup(World world, OfflinePlayer player) {
        return perms.getPrimaryGroup(world, player);
    }

    /**
     * Gets players primary group
     * @param world World name
     * @param player Player name
     * @return Players primary group
     */
    public String getPrimaryGroup(World world, UUID player) {
        return perms.getPrimaryGroup(world, Bukkit.getOfflinePlayer(player));
    }

    /**
     * Returns a list of all known groups
     * @return an Array of String of all groups
     */
    public String[] getGroups() {
        return perms.getGroups();
    }
}
