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


import chileayuda.personfinder.utils.utils.IntegerTypeNode;
import chileayuda.personfinder.utils.utils.TypeNode;

public class IntegerValue extends NumberValue {
    public long value;

    static public IntegerValue zero = new IntegerValue(0);

    public IntegerValue(long value) {
        this.value = value;
    }

    public TypeNode getType() {
        return IntegerTypeNode.one;
    }

    public Value clone() {
        return new IntegerValue(value);
    }

    public Value getDefaultValue() {
        return FloatValue.zero;
    }

    public Object getValue() {
        return value;
    }

    public boolean getBooleanValue() {
        return value != 0;
    }

    public long getLongValue() {
        return value;
    }

    public double getDoubleValue() {
        return value;
    }

    public String getStringValue() {
        return Long.toString(value);
    }


    public Value add(Value otherValue) {
        return new IntegerValue(value + otherValue.getLongValue());
    }

    public Value divide(int divisor) {
        return new IntegerValue(value / divisor);
    }

    public int compareValue(Value otherValue) {
        if (otherValue instanceof BooleanValue) {
            long leftValue = getLongValue();
            long rightValue = otherValue.getLongValue();
            if (leftValue == rightValue)
                return 0;
            else if (leftValue < rightValue)
                return -1;
            else
                return 1;
        } else if (otherValue instanceof IntegerValue) {
            long leftValue = getLongValue();
            long rightValue = otherValue.getLongValue();
            if (leftValue == rightValue)
                return 0;
            else if (leftValue < rightValue)
                return -1;
            else
                return 1;
        } else if (otherValue instanceof FloatValue) {
            double leftValue = getDoubleValue();
            double rightValue = otherValue.getDoubleValue();
            if (leftValue == rightValue)
                return 0;
            else if (leftValue < rightValue)
                return -1;
            else
                return 1;
        } else if (otherValue instanceof StringValue) {
            String leftValue = getStringValue();
            String rightValue = otherValue.getStringValue();
            return leftValue.compareTo(rightValue);
        } else {
            // TODO: What should this be?
            long leftValue = getLongValue();
            long rightValue = otherValue.getLongValue();
            if (leftValue == rightValue)
                return 0;
            else if (leftValue < rightValue)
                return -1;
            else
                return 1;
        }
    }

    public String toString() {
        return Long.toString(value);
    }

    public String getTypeString() {
        return "integer";
    }

    public boolean equals(Value valueNode) {
        return valueNode instanceof IntegerValue && (value == valueNode.getLongValue());
    }

    public Value negateValue() {
        return new IntegerValue(-value);
    }

}
