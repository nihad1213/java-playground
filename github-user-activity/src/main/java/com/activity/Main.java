package com.activity;

public class Main {
    static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: github-activity <username>");
        }


        String username = args[0];
        GitHubClient client = new GitHubClient();
        String response = client.fetchEvents(username);

        if (response == null) {
            System.out.println("Failed to fetch activity for: " + username);
            return;
        }

        EventParser parser = new EventParser();
        parser.parse(response);

    }
}
