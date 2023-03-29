package com.taco.api.economy;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * The main interface for a bank account, use this if you want bank support for your plugin.
 *
 * @author FlameyosFlow
 * @since 1.0.0
 */
public interface BankAccount {
    CompletableFuture<Bank> createBank(UUID playerId, String bankName);

    default boolean bankHasEnough(UUID playerId, double amount) {
        final boolean[] isTrue = new boolean[1];
        bankHasAccount(playerId).thenAccept(hasAccount ->
                bankBalance(playerId).thenAccept(balance ->
                        isTrue[0] = hasAccount && (balance >= amount)));
        return isTrue[0];
    }

    default boolean bankHasEnough(OfflinePlayer player, double amount) {
        final boolean[] isTrue = new boolean[1];
        bankHasAccount(player).thenAccept(hasAccount ->
                bankBalance(player).thenAccept(balance ->
                        isTrue[0] = hasAccount && (balance >= amount)));
        return isTrue[0];
    }

    CompletableFuture<Boolean> bankHasAccount(UUID playerId);

    CompletableFuture<Boolean> bankHasAccount(OfflinePlayer playerId);

    CompletableFuture<EconomyResponse> bankWire(UUID playerId, double amount);

    CompletableFuture<EconomyResponse> bankWire(OfflinePlayer playerId, double amount);

    CompletableFuture<EconomyResponse> bankWithdraw(UUID playerId, double amount);

    CompletableFuture<EconomyResponse> bankWithdraw(OfflinePlayer playerId, double amount);

    CompletableFuture<Double> bankBalance(UUID playerId);

    CompletableFuture<Double> bankBalance(OfflinePlayer playerId);

    Set<Bank> getBanks();
}
