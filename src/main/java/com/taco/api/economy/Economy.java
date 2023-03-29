package com.taco.api.economy;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Main taco Economy class
 *
 * @author FlameyosFlow
 * @since 1.0.0
 */
public interface Economy {
    String format(double amount);

    CompletableFuture<Double> getBalance(UUID playerId);

    CompletableFuture<Double> getBalance(OfflinePlayer player);

    CompletableFuture<EconomyResponse> send(OfflinePlayer player, double amount);

    CompletableFuture<EconomyResponse> send(UUID playerId, double amount);

    CompletableFuture<EconomyResponse> take(OfflinePlayer player, double amount);

    CompletableFuture<EconomyResponse> take(UUID playerId, double amount);

    default boolean hasEnough(UUID playerId, double amount) {
        final boolean[] isTrue = new boolean[1];
        hasAccount(playerId).thenAccept(hasAccount ->
                getBalance(playerId).thenAccept(balance ->
                        isTrue[0] = hasAccount && (balance >= amount)));
        return isTrue[0];
    }

    default boolean hasEnough(OfflinePlayer player, double amount) {
        final boolean[] isTrue = new boolean[1];
        hasAccount(player).thenAccept(hasAccount ->
                getBalance(player).thenAccept(balance ->
                        isTrue[0] = hasAccount && (balance >= amount)));
        return isTrue[0];
    }

    CompletableFuture<Boolean> hasAccount(UUID playerId);

    CompletableFuture<Boolean> hasAccount(OfflinePlayer player);

    CompletableFuture<Boolean> createAccount(UUID playerId);

    CompletableFuture<Boolean> createAccount(OfflinePlayer playerId);
}
