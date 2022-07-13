package pl.bajowski.githubfetcher.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class GitHubRepo {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final int stars;
    private final ZonedDateTime createdAt;


    public GitHubRepo(String fullName, String description, String cloneUrl, int stars, ZonedDateTime createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public int getStars() {
        return stars;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitHubRepo)) return false;
        GitHubRepo that = (GitHubRepo) o;
        return stars == that.stars && fullName.equals(that.fullName) && description.equals(that.description) && cloneUrl.equals(that.cloneUrl) && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, description, cloneUrl, stars, createdAt);
    }
}
