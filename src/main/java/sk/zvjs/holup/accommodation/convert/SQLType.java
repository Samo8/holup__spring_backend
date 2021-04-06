package sk.zvjs.holup.accommodation.convert;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum SQLType {
    SQL_TYPE_FLOAT, SQL_TYPE_INTEGER, SQL_TYPE_N_VARCHAR, SQL_TYPE_OTHER;

    @JsonValue
    public String toValue() {
        switch (this) {
            case SQL_TYPE_FLOAT: return "sqlTypeFloat";
            case SQL_TYPE_INTEGER: return "sqlTypeInteger";
            case SQL_TYPE_N_VARCHAR: return "sqlTypeNVarchar";
            case SQL_TYPE_OTHER: return "sqlTypeOther";
        }
        return null;
    }

    @JsonCreator
    public static SQLType forValue(String value) throws IOException {
        if (value.equals("sqlTypeFloat")) return SQL_TYPE_FLOAT;
        if (value.equals("sqlTypeInteger")) return SQL_TYPE_INTEGER;
        if (value.equals("sqlTypeNVarchar")) return SQL_TYPE_N_VARCHAR;
        if (value.equals("sqlTypeOther")) return SQL_TYPE_OTHER;
        throw new IOException("Cannot deserialize SQLType");
    }
}
