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

package chileayuda.personfinder.utils.utils;


import chileayuda.personfinder.utils.value.FalseValue;
import chileayuda.personfinder.utils.value.Value;

public class BooleanTypeNode extends TypeNode {
    public static BooleanTypeNode one = new BooleanTypeNode();

    public Value getDefaultValue() {
        return FalseValue.one;
    }

    public String toString() {
        return "boolean";
    }

    public boolean isCompatibleType(TypeNode other) {
        return other instanceof BooleanTypeNode;
    }
}
