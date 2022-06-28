package com.easypost;

import com.easypost.easyvcr.AdvancedSettings;
import com.easypost.easyvcr.Cassette;
import com.easypost.easyvcr.CensorElement;
import com.easypost.easyvcr.Censors;
import com.easypost.easyvcr.MatchRules;
import com.easypost.easyvcr.Mode;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class TestUtils {
    public enum ApiKey {
        TEST,
        PRODUCTION
    }

    private static final String API_KEY_FAILED_TO_PULL = "couldnotpullapikey";
    private static final String CASSETTES_PATH = "src/test/cassettes";
    private static final java.util.List<String> HEADER_CENSORS = new ArrayList<String>() {{
        add("Authorization");
        add("User-Agent");
        add("X-Client-User-Agent");
    }};
    private static final List<String> QUERY_CENSORS = new ArrayList<String>() {{
        add("card[cvc]");
        add("card[number]");
    }};
    private static final List<String> BODY_CENSORS = new ArrayList<String>() {{
        add("api_keys");
        add("client_ip");
        add("credentials");
        add("key");
        add("keys");
        add("phone_number");
        add("phone");
        add("test_credentials");
    }};
    private static final List<CensorElement> BODY_ELEMENTS_TO_IGNORE_ON_MATCH = new ArrayList<CensorElement>() {{
        // Timezone difference between machines causing failure on replay
        add(new CensorElement("createdAt", false));
        add(new CensorElement("updatedAt", false));
    }};

    /**
     * Get the directory where the program is currently executing.
     *
     * @return The directory where the program is currently executing
     */
    private static String getSourceFileDirectory() {
        try {
            return Paths.get("").toAbsolutePath().toString();
        } catch (Exception e) {
            return "";
        }
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
            default:
                break;
        }

        String value = System.getenv(keyName);
        return value != null ? value :
                API_KEY_FAILED_TO_PULL; // if can't pull from environment, will use a fake key. Won't matter on replay.
    }

    public static final class VCR {
        private final com.easypost.easyvcr.VCR vcr;

        private final String apiKey;

        private String testCassettesFolder;

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
         * @param testCassettesFolder The folder where the cassettes will be stored.
         */
        public VCR(String testCassettesFolder) {
            this(testCassettesFolder, ApiKey.TEST);
        }

        /**
         * Constructor.
         *
         * @param testCassettesFolder The folder where the cassettes will be stored.
         * @param apiKey              The API key to use.
         */
        public VCR(String testCassettesFolder, ApiKey apiKey) {
            this(testCassettesFolder, getApiKey(apiKey));
        }

        /**
         * Constructor.
         *
         * @param testCassettesFolder The folder where the cassettes will be stored.
         * @param apiKey              The API key to use.
         */
        public VCR(String testCassettesFolder, String apiKey) {
            AdvancedSettings advancedSettings = new AdvancedSettings();
            advancedSettings.matchRules =
                    new MatchRules().byMethod().byFullUrl().byBody(BODY_ELEMENTS_TO_IGNORE_ON_MATCH);
            advancedSettings.censors = new Censors("REDACTED").censorHeadersByKeys(HEADER_CENSORS)
                    .censorQueryParametersByKeys(QUERY_CENSORS).censorBodyElementsByKeys(BODY_CENSORS);

            vcr = new com.easypost.easyvcr.VCR(advancedSettings);

            this.apiKey = apiKey;

            this.testCassettesFolder = Paths.get(getSourceFileDirectory(), CASSETTES_PATH)
                    .toString(); // create the "cassettes" folder

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
         * Constructor.
         *
         * @param apiKey The API key to use.
         */
        public VCR(ApiKey apiKey) {
            this(null, apiKey);
        }

        /**
         * Constructor.
         */
        public VCR() {
            this(null, ApiKey.TEST);
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName The name of the cassette to use.
         */
        public void setUpTest(String cassetteName) {
            setUpTest(cassetteName, null);
        }

        /**
         * Set up the VCR for a unit test.
         *
         * @param cassetteName   The name of the cassette to use.
         * @param overrideApiKey The API key to use.
         */
        public void setUpTest(String cassetteName, String overrideApiKey) {
            // override api key if needed
            EasyPost.apiKey = overrideApiKey != null ? overrideApiKey : apiKey;

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

            // set VCR to be used during requests
            EasyPost._vcr = vcr;
        }
    }
}
