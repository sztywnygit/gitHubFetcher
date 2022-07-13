package pl.bajowski.githubfetcher;


import io.restassured.RestAssured;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import pl.bajowski.githubfetcher.model.GitHubRepo;
import pl.bajowski.githubfetcher.utility.JsonParser;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;


@SpringBootTest
class GithubFetcherApplicationTests {



    private String jsonResponse = "{\"id\":507180519,\"node_id\":\"R_kgDOHjr15w\",\"name\":\"javaBinarySearchTree\",\"full_name\":\"sztywnygit/javaBinarySearchTree\",\"private\":false,\"owner\":{\"login\":\"sztywnygit\",\"id\":94997324,\"node_id\":\"U_kgDOBamLTA\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/94997324?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/sztywnygit\",\"html_url\":\"https://github.com/sztywnygit\",\"followers_url\":\"https://api.github.com/users/sztywnygit/followers\",\"following_url\":\"https://api.github.com/users/sztywnygit/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/sztywnygit/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/sztywnygit/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/sztywnygit/subscriptions\",\"organizations_url\":\"https://api.github.com/users/sztywnygit/orgs\",\"repos_url\":\"https://api.github.com/users/sztywnygit/repos\",\"events_url\":\"https://api.github.com/users/sztywnygit/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/sztywnygit/received_events\",\"type\":\"User\",\"site_admin\":false},\"html_url\":\"https://github.com/sztywnygit/javaBinarySearchTree\",\"description\":\"my implementation of binary search tree in Java featuring methods like adding, deleting, printing path and isPresent\",\"fork\":false,\"url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree\",\"forks_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/forks\",\"keys_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/teams\",\"hooks_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/hooks\",\"issue_events_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/events\",\"assignees_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/tags\",\"blobs_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/languages\",\"stargazers_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/stargazers\",\"contributors_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/contributors\",\"subscribers_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/subscribers\",\"subscription_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/subscription\",\"commits_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/merges\",\"archive_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/downloads\",\"issues_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/sztywnygit/javaBinarySearchTree/deployments\",\"created_at\":\"2022-06-25T00:54:23Z\",\"updated_at\":\"2022-06-25T08:38:10Z\",\"pushed_at\":\"2022-06-25T08:38:07Z\",\"git_url\":\"git://github.com/sztywnygit/javaBinarySearchTree.git\",\"ssh_url\":\"git@github.com:sztywnygit/javaBinarySearchTree.git\",\"clone_url\":\"https://github.com/sztywnygit/javaBinarySearchTree.git\",\"svn_url\":\"https://github.com/sztywnygit/javaBinarySearchTree\",\"homepage\":null,\"size\":8,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"Java\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":0,\"license\":null,\"allow_forking\":true,\"is_template\":false,\"web_commit_signoff_required\":false,\"topics\":[],\"visibility\":\"public\",\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"master\",\"temp_clone_token\":null,\"network_count\":0,\"subscribers_count\":1}";


    @Test
    void shouldReturnJsonWithGitHubRepoInfo() throws Exception {


        ResponseBodyExtractionOptions body = RestAssured
                .when()
                .get("http://localhost:8080/repositories/sztywnygit/javaBinarySearchTree")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract().body();

        String fullName = body.path("fullName");
        String description = body.path("description");
        String cloneUrl = body.path("cloneUrl");
        int stars = body.path("stars");
        ZonedDateTime createdAt = ZonedDateTime.parse(body.path("createdAt")).withZoneSameInstant(ZoneId.of("Europe/Warsaw"));;

        GitHubRepo returnedRepo = new GitHubRepo(fullName,description,cloneUrl,stars,createdAt);
        GitHubRepo parsedRepo = JsonParser.mapRepoInformation(JsonParser.parse(jsonResponse), TimeZone.getTimeZone("Europe/Warsaw"));

        Assertions.assertEquals(returnedRepo,parsedRepo);


    }

}
