/**
 * Copyright 2012 John W. Krupansky d/b/a Base Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chileayuda.personfinder.utils.value;


import chileayuda.personfinder.utils.StringUtils;
import chileayuda.personfinder.utils.utils.StringTypeNode;
import chileayuda.personfinder.utils.utils.TypeNode;
import org.apache.log4j.Logger;
public class StringValue extends Value {
    static final Logger log = Logger.getLogger(StringValue.class);

    public String value;

    static public StringValue empty = new StringValue("");

    public StringValue(String value) {
        this.value = value;
    }

    public TypeNode getType() {
        return StringTypeNode.one;
    }

    public Value getDefaultValue() {
        return StringValue.empty;
    }

    public Object getValue() {
        return value;
    }

    // TODO: Reconsider whether string.boolean is a parse or simply a check for non-null and non-empty
    public boolean getBooleanValue() {
        return value != null && (value.trim().equalsIgnoreCase("true") || value.trim().equalsIgnoreCase("on"));
    }

    public long getLongValue() {
        // Remove commas and fraction
        // TODO: Should round
        return (long) Double.parseDouble(StringUtils.removeCommas(value));
    }

    public double getDoubleValue() {
        // Remove commas
        return Double.parseDouble(StringUtils.removeCommas(value));
    }

    public String getStringValue() {
        return value;
    }


    public int compareValue(Value otherValue) {
        String leftValue = getStringValue();
        String rightValue = otherValue.getStringValue();
        return leftValue.compareTo(rightValue);
    }

    public Value copyOnAssignment() {
        // For most values, no need to make a copy of actual value on assignment, only strings
        return new StringValue(value);
    }

    public Value clone() {
        return new StringValue(value);
    }

    public boolean equals(Value valueNode) {
        if (valueNode instanceof StringValue) {
            String otherValue = valueNode.getStringValue();
            if (value == null)
                return otherValue == null;
            else if (otherValue == null)
                return false;
            else
                return value.equals(otherValue);
        } else
            return false;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder("\"");
        if (value == null) {
            log.error("value of StingValue is null");
            return "";
        }
        int len = value.length();
        for (int i = 0; i < len; i++) {
            char ch = value.charAt(i);
            if (ch == '"' || ch == '\\')
                sb.append('\\');
            sb.append(ch);
        }
        sb.append('"');
        return sb.toString();
    }

    public String toString() {
        return value;
    }

    public String toText() {
        return toString();
    }


    public String getTypeString() {
        return "string";
    }

}
