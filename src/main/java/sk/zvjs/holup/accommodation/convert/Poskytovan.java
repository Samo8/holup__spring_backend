package sk.zvjs.holup.accommodation.convert;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Poskytovan {
    NIE, NO;

    @JsonValue
    public String toValue() {
        switch (this) {
            case NIE: return "Nie";
            case NO: return "\u00c1no";
        }
        return null;
    }

    @JsonCreator
    public static Poskytovan forValue(String value) throws IOException {
        if (value.equals("Nie")) return NIE;
        if (value.equals("\u00c1no")) return NO;
        throw new IOException("Cannot deserialize Poskytovan");
    }
}
