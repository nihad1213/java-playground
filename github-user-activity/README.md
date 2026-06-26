# GitHub Activity CLI

A simple command-line interface (CLI) built in core Java to fetch and display the recent activity of a GitHub user in the terminal.

## Requirements

- Java 11 or higher

## Usage

```bash
github-activity <username>
```

## Example

```bash
github-activity kamranahmedse
```

```
- Pushed 3 commits to kamranahmedse/developer-roadmap
- Opened a new issue in kamranahmedse/developer-roadmap
- Starred kamranahmedse/developer-roadmap
```

## How It Works

- Accepts a GitHub username as a command-line argument
- Fetches recent activity from `https://api.github.com/users/<username>/events`
- Parses the JSON response using only core Java (no external libraries)
- Displays the activity in the terminal