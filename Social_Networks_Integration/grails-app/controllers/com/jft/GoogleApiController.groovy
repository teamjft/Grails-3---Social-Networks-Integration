package com.jft

import com.google.api.client.googleapis.auth.oauth2.*
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.plus.Plus
import com.google.api.services.plus.PlusScopes
import com.google.api.services.plus.model.Person

class GoogleApiController {

    String CLIENT_ID = "284123898988-nr7n0sf00trr36dsj7munem0ujedakj2.apps.googleusercontent.com";
    String SECRET = "GS3TJLAQanmypk3iL3KJ_sjB";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/Integration-samples");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static final SCOPES =
            Arrays.asList(PlusScopes.USERINFO_PROFILE, PlusScopes.USERINFO_EMAIL, PlusScopes.PLUS_LOGIN);
    private static final REDIRECT_URIS =
            Arrays.asList("http://localhost:8080/oauth/register/googleOAuth2Callback");


    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }


    public String exchangeAuthorizationCodeForToken(String authorization) {
        // Load client secrets.
        GoogleClientSecrets googleClientSecrets = buildGoogleClientSecrets();
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = buildGoogleAuthorizationCodeFlow(googleClientSecrets);
        GoogleAuthorizationCodeTokenRequest request = flow.newTokenRequest(authorization);
        request.setRedirectUri("http://localhost:8080/oauth/register/googleOAuth2Callback")
        GoogleTokenResponse response = request.execute();
        return response.getAccessToken();
    }


    public Person getUserInfo(String accessToken) {
        GoogleCredential credential = buildGoogleCredential();
        credential.setAccessToken(accessToken);
        Plus plus = new Plus.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).build();
        Person person = plus.people()
                .get("me")
                .execute();
        return person;
    }


    private GoogleClientSecrets buildGoogleClientSecrets() {
        GoogleClientSecrets googleClientSecrets =  new GoogleClientSecrets();
        googleClientSecrets.setInstalled(new GoogleClientSecrets.Details()
                .setClientId(CLIENT_ID)
                .setClientSecret(SECRET)
                .setRedirectUris(REDIRECT_URIS)
        );
        return googleClientSecrets;
    }

    private GoogleAuthorizationCodeFlow buildGoogleAuthorizationCodeFlow(GoogleClientSecrets googleClientSecrets) {
        // Build flow and trigger user authorization request.
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
    }

    private GoogleCredential buildGoogleCredential () {
        return new GoogleCredential.Builder()
                .setJsonFactory(JSON_FACTORY).setTransport(HTTP_TRANSPORT)
                .setClientSecrets(SECRET, SECRET).build();
    }
}
