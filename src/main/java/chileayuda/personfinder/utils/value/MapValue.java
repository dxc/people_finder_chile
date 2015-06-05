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


import chileayuda.personfinder.utils.config.PeopleFinderException;
import chileayuda.personfinder.utils.JsonListMap;
import chileayuda.personfinder.utils.ListMap;
import chileayuda.personfinder.utils.utils.MapTypeNode;
import chileayuda.personfinder.utils.utils.ObjectTypeNode;
import chileayuda.personfinder.utils.utils.TypeNode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapValue extends Value {
    public TypeNode type;
    public ListMap<String, Value> value;

    public MapValue() {
        this(ObjectTypeNode.one, null);
    }

    public MapValue(TypeNode type) {
        this(type, null);
    }

    public MapValue(TypeNode type, List<Value> value) {
        this.type = type;
        ListMap<String, Value> newValueMap = new ListMap<String, Value>();
        if (value != null) {
            List<FieldValue> fieldValueList = (List<FieldValue>) (Object) value;
            for (FieldValue fieldValueNode : fieldValueList)
                newValueMap.put(fieldValueNode.fieldName, fieldValueNode.valueNode);
        }
        this.value = newValueMap;
    }

    public TypeNode getType() {
        return MapTypeNode.one;
    }

    public Object getValue() {
        return value;
    }

    public boolean getBooleanValue() {
        return value != null && value.size() > 0;
    }

    public long getLongValue() {
        return value.size();
    }

    public double getDoubleValue() {
        return value.size();
    }

    public String getStringValue() {
        return toString();
    }


    public Value get(String key) throws RuntimeException {
        if (value.containsKey(key))
            return (Value) value.get(key);
        else
            return NullValue.one;
    }


    public Value put(String key, Value newValue) throws RuntimeException {
        value.put(key, newValue);
        return newValue;
    }

    public MapValue clone() {
        List<FieldValue> newList = new ArrayList<FieldValue>();
        for (String key : value.keySet())
            newList.add(new FieldValue(key, value.get(key).clone()));
        return new MapValue(type, (List<Value>) (Object) newList);
    }

    public String toJson() {
        // Return JSON-format comma-separated list of element key/value pairs within braces
        StringBuilder sb = new StringBuilder("{");
        for (String parameterName : value.keySet()) {
            Value valueNode = value.get(parameterName);
            if (sb.length() > 1)
                sb.append(", ");
            sb.append('"');
            // TODO: Should escape this
            sb.append(parameterName);
            sb.append("\": ");
            sb.append(valueNode.toJson());
        }
        sb.append('}');
        return sb.toString();
    }

    public Object toJsonObject() throws PeopleFinderException {
        try {
            JSONObject json = new JsonListMap();
            for (String parameterName : value.keySet())
                json.put(parameterName, value.get(parameterName).toJsonObject());
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new PeopleFinderException("JSON exception formatting map object - " + e.getMessage());
        }
    }

    public String toString() {
        // Return comma-separated list of element key/value pairs within braces
        StringBuilder sb = new StringBuilder("{");
        for (String key : value.keySet()) {
            Value valueNode = value.get(key);
            if (sb.length() > 1)
                sb.append(", ");
            sb.append(key);
            sb.append(": ");
            sb.append(valueNode.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    public String toText() {
        // Return space-delimited list of element values
        StringBuilder sb = new StringBuilder();
        for (String key : value.keySet()) {
            // Get the text of the next element
            Value valueNode = value.get(key);
            String text = valueNode.toText();

            // Ignore empty text
            if (text == null || text.length() == 0)
                continue;

            // Separate text with single space
            if (sb.length() > 1)
                sb.append(" ");

            // Tack on the text for this element
            sb.append(text);
        }

        // Return the accumulated text
        return sb.toString();
    }

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        for (String key : value) {
            // XML formatting depends a little on value type
            Value valueNode = value.get(key);
            if (valueNode instanceof ListValue)
                sb.append(((ListValue) valueNode).toXml(key));
            else {
                // Generate the element start tag
                sb.append("<" + key + ">");

                // Get the XML text of the element
                String xmlText = valueNode.toXml();
                sb.append(xmlText);

                // Generate the element end tag
                sb.append("</" + key + ">");
            }
        }

        // Return the accumulated text
        return sb.toString();
    }

    public String getTypeString() {
        return "map<string, " + type.toString() + ">";
    }

    public boolean equals(Value valueNode) {
        // Other value must also be a map
        if (valueNode instanceof MapValue) {
            // Sizes must agree
            int len1 = value.size();
            MapValue value2 = (MapValue) valueNode;
            int len2 = value2.value.size();
            if (len1 != len2)
                return false;

            // And values for each key must match recursively
            for (String key : value.keySet())
                if (!value2.value.containsKey(key))
                    return false;
                else if (!value.get(key).equals(value2.value.get(key)))
                    return false;

            // Everything matches
            return true;
        } else
            // No match
            return false;
    }

}
