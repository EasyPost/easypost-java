package com.easypost;

import com.easypost.easyvcr.AdvancedSettings;
import com.easypost.easyvcr.Cassette;
import com.easypost.easyvcr.CensorElement;
import com.easypost.easyvcr.Censors;
import com.easypost.easyvcr.ExpirationActions;
import com.easypost.easyvcr.MatchRules;
import com.easypost.easyvcr.Mode;
import com.easypost.easyvcr.TimeFrame;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.mocking.MockClient;
import com.easypost.mocking.MockRequest;
import com.easypost.service.EasyPostClient;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class TestUtils {
    public enum ApiKey {
        TEST,
        PRODUCTION,
        REFERRAL,
        PARTNER
    }

    private static final String API_KEY_FAILED_TO_PULL = "couldNotPullApiKey";
    private static final String CASSETTES_PATH = "src/test/cassettes";
    private static final List<String> HEADER_CENSORS = ImmutableList.of("Authorization", "User-Agent");
    private static final List<String> QUERY_CENSORS = ImmutableList.of("card[cvc]", "card[number]");
    private static final List<String> BODY_CENSORS =
            ImmutableList.of("api_keys", "client_ip", "credentials", "key", "keys", "phone_number", "phone",
                    "test_credentials");
    private static final List<CensorElement> BODY_ELEMENTS_TO_IGNORE_ON_MATCH =
            ImmutableList.of(new CensorElement("createdAt", false), new CensorElement("updatedAt", false));

    /**
     * Get the directory where the program is currently executing.
     *
     * @return The directory where the program is currently executing
     */
    public static String getSourceFileDirectory() {
        try {
            return Paths.get("").toAbsolutePath().toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Read the contents of a file.
     *
     * @param path The path to the file
     * @return The contents of the file
     */
    public static String readFile(Path path) {
        List<String> data;
        try {
            data = java.nio.file.Files.readAllLines(path);
        } catch (IOException ignored) {
            return null;
        }
        if (data.isEmpty()) {
            return null;
        }
        StringBuilder contents = new StringBuilder();
        for (String line : data) {
            contents.append(line);
        }
        return contents.toString();
    }

    /**
     * Get an API key from the environment.
     *
     * @param apiKey Type of API key to get
     * @return the API key from the environment
     */
    public static String getApiKey(ApiKey apiKey) {
        String keyName = "";
        switch (apiKey) {
            case TEST:
                keyName = "EASYPOST_TEST_API_KEY";
                break;
            case PRODUCTION:
                keyName = "EASYPOST_PROD_API_KEY";
                break;
            case REFERRAL:
                keyName = "REFERRAL_CUSTOMER_PROD_API_KEY";
                break;
            case PARTNER:
                keyName = "PARTNER_USER_PROD_API_KEY";
                break;
            default:
                break;
        }

        String value = System.getenv(keyName);
        value = (value != null && !value.isEmpty()) ? value : API_KEY_FAILED_TO_PULL; // if can't pull from environment,
        // will use a fake key. Won't
        // matter on replay.
        return value;
    }

    public static final class VCR {
        private final com.easypost.easyvcr.VCR vcr;
        private String testCassettesFolder;
        private String apiKey;
        EasyPostClient client;

        private List<MockRequest> mockRequests = new ArrayList<>();

        /**
         * Get whether the VCR is recording.
         *
         * @return true if recording, false otherwise.
         */
        public boolean isRecording() {
            return vcr.getMode() == Mode.Record;
        }

        /**
         * Constructor.
         *
         * @throws MissingParameterError if a required parameter is missing.
         */
        public VCR() throws MissingParameterError {
            this(null, ApiKey.TEST);
        }

        /**
         * Constructor.
         *
         * @param testCassettesFolder The folder where the cassettes will be stored.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public VCR(String testCassettesFolder) throws MissingParameterError {
            this(testCassettesFolder, ApiKey.TEST);
        }

        /**
         * Constructor.
         *
         * @param testCassettesFolder The folder where the cassettes will be stored.
         * @param apiKey              The API key to use.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public VCR(String testCassettesFolder, ApiKey apiKey) throws MissingParameterError {
            this(testCassettesFolder, getApiKey(apiKey));
        }

        /**
         * Constructor.
         *
         * @param apiKey The API key to use.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public VCR(ApiKey apiKey) throws MissingParameterError {
            this(null, apiKey);
        }

        /**
         * Constructor.
         *
         * @param testCassettesFolder The folder where the cassettes will be stored.
         * @param apiKey              The API key to use.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public VCR(String testCassettesFolder, String apiKey) throws MissingParameterError {
            AdvancedSettings advancedSettings = new AdvancedSettings();
            advancedSettings.matchRules =
                    new MatchRules().byMethod().byFullUrl().byBody(BODY_ELEMENTS_TO_IGNORE_ON_MATCH);
            advancedSettings.censors = new Censors("REDACTED").censorHeadersByKeys(HEADER_CENSORS)
                    .censorQueryParametersByKeys(QUERY_CENSORS).censorBodyElementsByKeys(BODY_CENSORS);

            advancedSettings.timeFrame = TimeFrame.months6();
            advancedSettings.whenExpired = ExpirationActions.Warn;

            vcr = new com.easypost.easyvcr.VCR(advancedSettings);

            this.apiKey = apiKey;
            this.client = new EasyPostClient(apiKey);
            this.testCassettesFolder =
                    Paths.get(getSourceFileDirectory(), CASSETTES_PATH).toString(); // create the "cassettes" folder

            if (testCassettesFolder != null) {
                this.testCassettesFolder = Paths.get(this.testCassettesFolder, testCassettesFolder)
                        .toString(); // create test group folder in cassettes folder
            }

            // if folder doesn't exist, create it
            File directory = new File(this.testCassettesFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName The name of the cassette to use.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public void setUpTest(String cassetteName) throws MissingParameterError {
            setUpTest(cassetteName, null, null);
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName The name of the cassette to use.
         * @param mockRequests The mock requests to use. Will attempt to mock requests first, then fall back to VCR.
         *                     These cannot be altered outside the setUpTest method.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public void setUpTest(String cassetteName, List<MockRequest> mockRequests) throws MissingParameterError {
            setUpTest(cassetteName, null, mockRequests);
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName   The name of the cassette to use.
         * @param overrideApiKey The API key to use.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public void setUpTest(String cassetteName, String overrideApiKey) throws MissingParameterError {
            setUpTest(cassetteName, overrideApiKey, null);
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName   The name of the cassette to use.
         * @param overrideApiKey The API key to use.
         * @param mockRequests   The mock requests to use. Will attempt to mock requests first, then fall back to VCR.
         *                       These cannot be altered outside the setUpTest method.
         * @throws MissingParameterError if a required parameter is missing.
         */
        public void setUpTest(String cassetteName, @Nullable String overrideApiKey,
                              @Nullable List<MockRequest> mockRequests) throws MissingParameterError {
            // override api key if needed
            client = new EasyPostClient(overrideApiKey != null ? overrideApiKey : apiKey);

            // if mock requests are enabled, wrap the VCR client in a mock client with passthrough enabled
            // requests that match a mock will be handled by the mock, otherwise they will be handled by the VCR
            if (mockRequests != null) {
                client = new MockClient(client, mockRequests, true);
            }

            // set up cassette
            Cassette cassette = new Cassette(testCassettesFolder, cassetteName);

            String filePath = Paths.get(testCassettesFolder, cassetteName + ".json").toString();
            File cassetteFile = new File(filePath);

            // add cassette to vcr
            vcr.insert(cassette);

            if (!cassetteFile.exists()) {
                // if cassette doesn't exist, switch to record mode
                vcr.record();
            } else {
                // if cassette exists, switch to replay mode
                vcr.replay();
            }

            Function<String, HttpsURLConnection> vcrUrlFunction = (url) -> {
                try {
                    return vcr.getHttpUrlConnection(url).openConnectionSecure();
                } catch (Exception vcrException) {
                    throw new RuntimeException(vcrException);
                }
            };

            // set VCR to be used during requests
            EasyPost._vcrUrlFunction = vcrUrlFunction;
        }
    }
}
