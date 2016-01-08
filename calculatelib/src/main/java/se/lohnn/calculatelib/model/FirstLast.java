package se.lohnn.calculatelib.model;

/**
 * Copyright (C) lohnn on 2015.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class FirstLast {
    private double first, last;

    public FirstLast(double first, double last) {
        this.first = first;
        this.last = last;
    }

    public FirstLast add(FirstLast other) {
        return new FirstLast(this.getFirst() + other.getFirst(), this.getLast() + other.getLast());
    }

    public FirstLast add(double v) {
        return new FirstLast(this.getFirst() + v, this.getLast() + v);
    }

    public FirstLast mult(double v) {
        return new FirstLast(this.getFirst() * v, this.getLast() * v);
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }
}
