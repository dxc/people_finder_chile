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


import chileayuda.personfinder.utils.utils.ListTypeNode;
import chileayuda.personfinder.utils.utils.ObjectTypeNode;
import chileayuda.personfinder.utils.utils.TypeNode;

import java.util.ArrayList;
import java.util.List;

public class ListValue extends Value {
    public TypeNode type;
    public List<Value> value;

    public ListValue() {
        this.type = ObjectTypeNode.one;
        this.value = new ArrayList<Value>();
    }

    public ListValue(TypeNode type) {
        this.type = type;
        this.value = new ArrayList<Value>();
    }

    public ListValue(TypeNode type, List<Value> value) {
        this.type = type;
        this.value = value;
    }

    public TypeNode getType()
    {
        return ListTypeNode.one;
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

    public void appendValue(Value newValue) {
        // Append the new value
        value.add(newValue);
    }

    public Value clone() {
        List<Value> newList = new ArrayList<Value>();
        for (Value element : value)
            newList.add(element.clone());
        return new ListValue(type, newList);
    }

    public String toJson() {
        // Return JSON-format comma-separated list of element values within square brackets
        StringBuilder sb = new StringBuilder("[");
        for (Value valueNode : value) {
            if (sb.length() > 1)
                sb.append(", ");
            sb.append(valueNode.toJson());
        }
        sb.append(']');
        return sb.toString();
    }

    public String toString() {
        // Return comma-separated list of element values within square brackets
        StringBuilder sb = new StringBuilder("[");
        for (Value valueNode : value) {
            if (sb.length() > 1)
                sb.append(", ");
            sb.append(valueNode.toString());
        }
        sb.append(']');
        return sb.toString();
    }

    public String toText() {
        // Return space-delimited list of element values
        StringBuilder sb = new StringBuilder();
        for (Value valueNode : value) {
            // Get the text of the next element
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

        // Return the acculated text
        return sb.toString();
    }

    public String toXml() {
        return toXml(null);
    }

    public String toXml(String elementName) {
        StringBuilder sb = new StringBuilder();
        for (Value valueNode : value) {
            if (elementName != null)
                sb.append("<" + elementName + ">");
            else if (sb.length() > 0)
                sb.append(' ');

            // Get the XML text of the next element
            String xmlText = valueNode.toXml();
            sb.append(xmlText);

            if (elementName != null)
                sb.append("</" + elementName + ">");
        }

        // Return the accumulated text
        return sb.toString();
    }

    public String getTypeString() {
        return "list<" + type.toString() + ">";
    }

    public boolean equals(Value valueNode) {
        // Other value must also be a list
        if (valueNode instanceof ListValue) {
            // Sizes must agree
            int len1 = value.size();
            ListValue value2 = (ListValue) valueNode;
            int len2 = value2.value.size();
            if (len1 != len2)
                return false;

            // And each list element must match recursively
            for (int i = 0; i < len1; i++)
                if (!value.get(i).equals(value2.value.get(i)))
                    return false;

            // Everything matches
            return true;
        } else
            // No match
            return false;
    }

    public List<String> getStrings(int startIndex, int endIndex) {
        List<String> strings = new ArrayList<String>();
        int numElements = value.size();
        if (startIndex < 0)
            startIndex = 0;
        if (endIndex >= numElements)
            endIndex = numElements - 1;
        for (int i = startIndex; i <= endIndex; i++)
            strings.add(value.get(i).toString());

        return strings;
    }
}
