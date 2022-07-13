package pl.bajowski.githubfetcher.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.bajowski.githubfetcher.utility.JsonParser;
import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;

@RestController
@RequestMapping("/repositories")
public class GitHubFetcherAPI {

    private final RestTemplate restTemplate;

    @Value("${api-key}")
    private String api_key;

    @Autowired
    public GitHubFetcherAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping(value = "/{owner}/{repositoryname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getRepoInformation(@PathVariable("owner") String owner, @PathVariable("repositoryname") String repositoryname, HttpServletRequest request)  {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",api_key);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        request.getRemoteAddr();

        //using TimeZone in arguments kinda worked but gave the server's timezone so didn't really work on heroku
        //thats why im using different api that will give me the timezone based off of requester's IP address

        try {
            String response = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repositoryname, String.class);
            ResponseEntity<String> responseTimeZone;
            String timezone;

            try {
                responseTimeZone = restTemplate.exchange("https://ipxapi.com/api/ip?ip=" + request.getRemoteAddr(), HttpMethod.GET,
                        requestEntity, String.class);
                timezone = JsonParser.parse(responseTimeZone.getBody()).get("timezone").asText();
            }
            catch (Exception e){
                timezone = "Europe/Warsaw"; //default value, since on localhost the ip is 0:0:0:0:0:0:0:1 and we cant get it from the ipxapi
            }

            return JsonParser.mapRepoInformation(JsonParser.parse(response), TimeZone.getTimeZone(timezone));

        }
        catch (Exception e){
            return "Repo not found";
        }

    }

}
