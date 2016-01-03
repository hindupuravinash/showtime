package in.nash.showtime.network;

/**
 * Created by avinash on 9/12/15.
 */

public class ResponseOptions {

    private final ResponseOption[] options;

    public ResponseOptions(ResponseOption... options) {
        this.options = options;
    }

    @Override
    public String toString() {
        if (options != null && options.length > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < options.length; i++) {
                stringBuffer.append(options[i]);

                if (i < options.length - 1) {
                    stringBuffer.append(',');
                }
            }

            return stringBuffer.toString();
        }

        return null;
    }

    public enum ResponseOption {

        VIDEOS("videos"),
        RELEASES("releases"),
        CREDITS("credits"),
        REVIEWS("reviews"),
        SIMILAR("similar"),
        IMAGES("images"),
        EXTERNAL_IDS("external_ids");

        private final String value;

        ResponseOption(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}