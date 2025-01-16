package net.jmp.spring.boot.cleaners.classes;

/*
 * (#)Person.java   0.2.0   01/16/2025
 *
 * @author   Jonathan Parker
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/// The person class.
///
/// @version    0.2.0
/// @since      0.2.0
public class Person {
    /// The name of the person.
    private String name;

    /// The age of the person.
    private int age;

    /// The default constructor.
    public Person() {
        super();
    }

    /// Return the name of the person.
    ///
    /// @return java.lang.String
    public String getName() {
        return this.name;
    }

    /// Set the name of the person.
    ///
    /// @param  name    java.lang.String
    public void setName(final String name) {
        this.name = name;
    }

    /// Return the age of the person.
    ///
    /// @return int
    public int getAge() {
        return this.age;
    }

    /// Set the age of the person.
    ///
    /// @param  age int
    public void setAge(final int age) {
        this.age = age;
    }

    /// Return a string that represents the person.
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
