package service;
 import org.json.JSONObject;
 import software.amazon.awssdk.regions.Region;
 import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class AWSSecret {

    public static JSONObject getSecret() {

        String secretName = "DatabaseConnection";
        Region region = Region.of("eu-west-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);


            return new JSONObject(getSecretValueResponse.secretString());
        } catch (Exception e) {
            throw e;
        }

    }
}
