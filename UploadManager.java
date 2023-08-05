package me.bluenitrox.manager;

import me.bluenitrox.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadManager {

    private static String webhookURL = "https://discord.com/api/webhooks/936360784391450695/BYl1EWjQ7TyFxaaUaPz3LoH5wpr4aPQ-QHb_1iYAXKg8dUXCSpO86VB0rrL44XcLcAli";

    public void uploadToDatabase(String message, String sender){
        try (PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO message (Nachricht, Sender) VALUES (?, ?)")) {
            ps.setString(1, message);
            ps.setString(2, sender);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setHook(String message){
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(message));
        try {
            webhook.execute();
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
    }

}
