package pl.bajowski.githubfetcher.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.bajowski.githubfetcher.model.GitHubRepo;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class JsonParser {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) {
        try {
            return objectMapper.readTree(src);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GitHubRepo mapRepoInformation(JsonNode node, TimeZone timeZone)  {
            String fullName = node.get("full_name").asText();
            String description = node.get("description").asText();
            String cloneUrl = node.get("clone_url").asText();
            int stars = node.get("stargazers_count").asInt();
            String tempDate = node.get("created_at").asText();

            ZonedDateTime zonedDateTime = ZonedDateTime.parse(tempDate).withZoneSameInstant(timeZone.toZoneId());

            return new GitHubRepo(fullName, description, cloneUrl, stars, zonedDateTime);
    }

}
