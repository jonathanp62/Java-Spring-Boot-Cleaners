package net.jmp.spring.boot.cleaners.components;

/*
 * (#)ResourceCleaner.java  0.2.0   01/16/2025
 *
 * @author    Jonathan Parker
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

import org.springframework.stereotype.Component;

import java.lang.ref.Cleaner;

/// The resource cleaner.
///
/// @version 0.2.0
/// @since   0.2.0
@Component
public class ResourceCleaner {
    /// The cleaner.
    private final Cleaner cleaner;

    /// The default constructor.
    public ResourceCleaner() {
        super();

        this.cleaner = Cleaner.create();
    }

    /// Retrieves the cleaner object.
    ///
    /// @return java.lang.ref.Cleaner
    public Cleaner getCleaner() {
        return this.cleaner;
    }
}
