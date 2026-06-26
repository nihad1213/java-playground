package com.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GitHubClient {

    public String fetchEvents(String username) {
        try {
            URL url = new URL("https://api.github.com/users/" + username + "/events");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "github-activity-cli");

            int status = conn.getResponseCode();

            if (status == 404) {
                System.out.println("Error: User \"" + username + "\" not found.");
                return null;
            }
            if (status != 200) {
                System.out.println("Error: HTTP " + status);
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Error: Unable to connect to GitHub API." + e);
            return null;
        }
    }
}
