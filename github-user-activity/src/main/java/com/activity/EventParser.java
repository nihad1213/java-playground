package com.activity;

public class EventParser {

    public void parse(String json) {
        String[] events = json.split("\\{\"id\":");

        boolean anyPrinted = false;

        for (int i = 1; i < events.length; i++) {
            String event = events[i];

            String type = extractValue(event, "type");
            String repoName = extractRepoName(event);

            if (type == null || repoName == null) continue;

            String message = buildMessage(type, event, repoName);
            if (message != null) {
                System.out.println("- " + message);
                anyPrinted = true;
            }
        }

        if (!anyPrinted) {
            System.out.println("No recent activity found.");
        }
    }

    private String buildMessage(String type, String event, String repo) {
        switch (type) {
            case "PushEvent": {
                int commits = countCommits(event);
                return "Pushed " + commits + " commit(s) to " + repo;
            }
            case "IssuesEvent": {
                String action = extractValue(event, "action");
                return (action != null ? capitalize(action) : "Opened") + " an issue in " + repo;
            }
            case "IssueCommentEvent":
                return "Commented on an issue in " + repo;
            case "WatchEvent":
                return "Starred " + repo;
            case "ForkEvent":
                return "Forked " + repo;
            case "CreateEvent": {
                String refType = extractValue(event, "ref_type");
                return "Created a new " + (refType != null ? refType : "ref") + " in " + repo;
            }
            case "DeleteEvent": {
                String refType = extractValue(event, "ref_type");
                return "Deleted a " + (refType != null ? refType : "ref") + " in " + repo;
            }
            case "PullRequestEvent": {
                String action = extractValue(event, "action");
                return (action != null ? capitalize(action) : "Opened") + " a pull request in " + repo;
            }
            case "ReleaseEvent":
                return "Published a release in " + repo;
            default:
                return type.replace("Event", "") + " in " + repo;
        }
    }

    private String extractValue(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return null;
        return json.substring(start, end);
    }

    private String extractRepoName(String event) {
        String search = "\"repo\":{\"id\":";
        int repoStart = event.indexOf(search);
        if (repoStart == -1) return null;
        return extractValue(event.substring(repoStart), "name");
    }

    private int countCommits(String event) {
        String search = "\"commits\":[";
        int idx = event.indexOf(search);
        if (idx == -1) return 0;
        int count = 0;
        int pos = idx;
        while ((pos = event.indexOf("\"sha\":", pos + 1)) != -1) count++;
        return count;
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}