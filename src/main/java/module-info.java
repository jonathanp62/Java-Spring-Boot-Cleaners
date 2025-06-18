/*
 * (#)module-info.java  0.2.0   01/16/2025
 * (#)module-info.java  0.1.0   01/15/2025
 *
 * @version 0.2.0
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

/// The application module. No packages are provided.
///
/// @since 0.1.0
module Spring.Boot.Cleaners.main {
    requires logging.utilities;
    requires org.slf4j;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    opens net.jmp.spring.boot.cleaners to spring.core;
    opens net.jmp.spring.boot.cleaners.classes to spring.core;
    opens net.jmp.spring.boot.cleaners.components to spring.core;

    exports net.jmp.spring.boot.cleaners to spring.beans, spring.context;
    exports net.jmp.spring.boot.cleaners.components to spring.beans;
    exports net.jmp.spring.boot.cleaners.services to spring.beans, spring.core;
}