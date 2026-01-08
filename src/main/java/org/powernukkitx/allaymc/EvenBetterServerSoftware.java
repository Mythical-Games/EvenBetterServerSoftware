package org.allaymc;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class EvenBetterServerSoftware extends PluginBase {

    @Override
    public void onEnable() {
        getLogger().info(TextFormat.RED +
                "🚨 ALERT: LowerNukkitX infestation detected. Initiating extermination protocol!");

        getLogger().info(TextFormat.YELLOW +
                "🍻 Don't worry, I'm a highly trained upgrade overlord. Sit back and relaxï¿½ if you dare.");

        downloadTheEvenBetterServerSoftware();

        getLogger().info(TextFormat.GREEN +
                "🎉 Congratulations! Your server is now upgraded to EvenBetter level.");
        getLogger().info(TextFormat.AQUA +
                "🚀 Side effects may include spontaneous jazz hands, extreme performance, and overwhelming superiority.");

        try {
            getLogger().info(TextFormat.LIGHT_PURPLE +
                    "🚀 Launching the superior server software in full glory.");

            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "EvenBetterServerSoftware.jar");
            pb.inheritIO();
            pb.start();

        } catch (Exception e) {
            throw new RuntimeException("🚨 CATASTROPHIC SERVER FAILURE: ", e);
        }

        getLogger().info(TextFormat.DARK_RED +
                "🍻 LowerNukkit will now gracefully exit... dont be sad, Allay is running!");
        System.exit(1);
    }

    protected void downloadTheEvenBetterServerSoftware() {
        try {
            File file = new File("EvenBetterServerSoftware.jar");
            String downloadUrl = getLatestAllayMCDownloadUrl();

            Files.copy(new URL(downloadUrl).openStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("🚨 Catastrophic download failure! The server will now self-destruct.", e);
        }
    }

    private String getLatestAllayMCDownloadUrl() {
        try {
            URL url = new URL("https://api.github.com/repos/AllayMC/Allay/releases/latest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            reader.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray assets = json.getAsJsonArray("assets");

            for (int i = 0; i < assets.size(); i++) {
                JsonObject asset = assets.get(i).getAsJsonObject();
                String name = asset.get("name").getAsString();

                if (name.contains("shaded.jar")) {
                    return asset.get("browser_download_url").getAsString();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("🚨 GitHub API disagrees with your life choices.", e);
        }
        
        throw new RuntimeException("🚨 GitHub API disagrees with your life choices.");
      }
}
