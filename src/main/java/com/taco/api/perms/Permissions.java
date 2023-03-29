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
package com.taco.api.perms;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

/**
 * The main Permission API - allows for group and player based permission tests
 *
 */
public abstract class Permissions {
    protected Plugin plugin;

    public Permissions(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets name of permission method
     *
     * @return Name of Permission Method
     */
    abstract public String getName();

    /**
     * Checks if permission method is enabled.
     *
     * @return Success or Failure
     */
    abstract public boolean isEnabled();

    /**
     * Returns if the permission system is or attempts to be compatible with super-perms.
     *
     * @return True if this permission implementation works with super-perms
     */
    abstract public boolean hasSuperPermsCompat();

    /**
     * Checks if a CommandSender has a permission node.
     * This will return the result of bukkits, generic .hasPermission() method and is identical in all cases.
     * This method will explicitly fail if the registered permission system does not register permissions in bukkit.
     * <p>
     * For easy checking of a commandsender
     *
     * @param sender     to check permissions on
     * @param permission to check for
     * @return true if the sender has the permission
     */
    public boolean has(CommandSender sender, String permission) {
        return sender.hasPermission(permission);
    }

    /**
     * Checks if player has a permission node. (Short for playerHas(...)
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean has(Player player, String permission) {
        return player.hasPermission(permission);
    }

    public abstract boolean playerAdd(World world, UUID player, String permission);

    /**
     * Add permission to a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      String world name
     * @param player     to add to
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAdd(World world, OfflinePlayer player, String permission) {
        if (world == null) {
            return playerAdd((World) null, player.getUniqueId(), permission);
        }
        return playerAdd(world, player.getUniqueId(), permission);
    }

    /**
     * Add permission to a player ONLY for the world the player is currently on.
     * This is a world-specific operation, if you want to add global permission you must explicitly use NULL for the world.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAdd(Player player, String permission) {
        return playerAdd(player.getWorld(), player, permission);
    }

    /**
     * Add transient permission to a player.
     * This implementation can be used by any subclass which implements a "pure" superperms plugin, i.e.
     * one that only needs the built-in Bukkit API to add transient permissions to a player.
     *
     * @param player     to add to
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAddTransient(OfflinePlayer player, String permission) throws UnsupportedOperationException {
        if (player.isOnline()) {
            return playerAddTransient((Player) player, permission);
        }
        throw new UnsupportedOperationException(getName() + " does not support offline player transient permissions!");
    }

    /**
     * Add transient permission to a player.
     * This operation adds a permission onto the player object in bukkit via Bukkit's permission interface.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAddTransient(Player player, String permission) {
        for (PermissionAttachmentInfo paInfo : player.getEffectivePermissions()) {
            if (paInfo.getAttachment() != null && paInfo.getAttachment().getPlugin().equals(plugin)) {
                paInfo.getAttachment().setPermission(permission, true);
                return true;
            }
        }

        PermissionAttachment attach = player.addAttachment(plugin);
        attach.setPermission(permission, true);

        return true;
    }

    abstract public boolean playerRemove(World world, UUID player, String permission);

    /**
     * Remove permission from a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param player     Player name
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemove(World world, OfflinePlayer player, String permission) {
        if (world == null) {
            return playerRemove(null, player, permission);
        }
        return playerRemove(world, player.getUniqueId(), permission);
    }

    /**
     * Remove permission from a player.
     * Will attempt to remove permission from the player on the player's current world.  This is NOT a global operation.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemove(Player player, String permission) {
        return playerRemove(player.getWorld(), player, permission);
    }


    /**
     * Remove transient permission from a player.
     * This implementation can be used by any subclass which implements a "pure" superperms plugin, i.e.
     * one that only needs the built-in Bukkit API to remove transient permissions from a player.  Any subclass
     * implementing a plugin which provides its own API for these needs to override this method.
     *
     * @param player     OfflinePlayer
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(OfflinePlayer player, String permission) {
        if (player.isOnline()) {
            return playerRemoveTransient((Player) player, permission);
        } else {
            return false;
        }
    }

    /**
     * Remove transient permission from a player.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(Player player, String permission) {
        for (PermissionAttachmentInfo paInfo : player.getEffectivePermissions()) {
            if (paInfo.getAttachment() != null && paInfo.getAttachment().getPlugin().equals(plugin)) {
                paInfo.getAttachment().unsetPermission(permission);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if group has a permission node.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World Object
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    public abstract boolean groupHas(World world, String group, String permission);

    /**
     * Add permission to a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    abstract public boolean groupAdd(World world, String group, String permission);

    /**
     * Remove permission from a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    abstract public boolean groupRemove(World world, String group, String permission);

    abstract public boolean playerInGroup(World world, UUID player, String group);

    /**
     * Check if player is member of a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  World Object
     * @param player to check
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(World world, OfflinePlayer player, String group) {
        if (world == null) {
            return playerInGroup(null, player.getUniqueId(), group);
        }
        return playerInGroup(world, player.getUniqueId(), group);
    }

    /**
     * Check if player is member of a group.
     * This method will ONLY check groups for which the player is in that are defined for the current world.
     * This may result in odd return behaviour depending on what permission system has been registered.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(Player player, String group) {
        return playerInGroup(player.getWorld(), player, group);
    }

    abstract public boolean playerAddGroup(World world, UUID player, String group);

    /**
     * Add player to a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player to add
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerAddGroup(World world, OfflinePlayer player, String group) {
        if (world == null) {
            return playerAddGroup(null, player.getUniqueId(), group);
        }
        return playerAddGroup(world, player.getUniqueId(), group);
    }

    /**
     * Add player to a group.
     * This will add a player to the group on the current World.  This may return odd results if the permission system
     * being used on the server does not support world-specific groups, or if the group being added to is a global group.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerAddGroup(Player player, String group) {
        return playerAddGroup(player.getWorld(), player, group);
    }

    abstract public boolean playerRemoveGroup(World world, UUID player, String group);


    public boolean playerRemoveGroup(World world, OfflinePlayer player, String group) {
        if (world == null) {
            return playerRemoveGroup(null, player.getUniqueId(), group);
        }
        return playerRemoveGroup(world, player.getUniqueId(), group);
    }

    /**
     * Remove player from a group.
     * This will add a player to the group on the current World.  This may return odd results if the permission system
     * being used on the server does not support world-specific groups, or if the group being added to is a global group.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerRemoveGroup(Player player, String group) {
        return playerRemoveGroup(player.getWorld(), player, group);
    }

    public abstract String[] getPlayerGroups(World world, UUID player);

    /**
     * Gets the list of groups that this player has
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player OfflinePlayer
     * @return Array of groups
     */
    public String[] getPlayerGroups(World world, OfflinePlayer player) {
        return getPlayerGroups(world, player.getUniqueId());
    }

    /**
     * Returns a list of world-specific groups that this player is currently in. May return unexpected results if
     * you are looking for global groups, or if the registered permission system does not support world-specific groups.
     *
     * @param player Player Object
     * @return Array of groups
     */
    public String[] getPlayerGroups(Player player) {
        return getPlayerGroups(player.getWorld(), player);
    }

    /**
     * Gets players primary group
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player to get from
     * @return Players primary group
     */
    public String getPrimaryGroup(World world, OfflinePlayer player) {
        return getPrimaryGroup(world, player.getUniqueId());
    }

    public abstract String getPrimaryGroup(World world, UUID uniqueId);

    /**
     * Get players primary group.
     * Defaults to the players current world, so may return only world-specific groups.
     *
     * @param player Player Object
     * @return Players primary group
     */
    public String getPrimaryGroup(Player player) {
        return getPrimaryGroup(player.getWorld(), player);
    }

    /**
     * Returns a list of all known groups
     *
     * @return an Array of String of all groups
     */
    abstract public String[] getGroups();

    /**
     * Returns true if the given implementation supports groups.
     *
     * @return true if the implementation supports groups
     */
    abstract public boolean hasGroupSupport();
}