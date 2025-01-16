package net.jmp.spring.boot.cleaners.services;

/*
 * (#)ThreadRunnerService.java  0.2.0   01/15/2025
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

import net.jmp.spring.boot.cleaners.classes.ThreadRunner;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Service;

/// The thread service. Since auto-wiring only works on Spring managed
/// objects, and ThreadRunner is not a Spring managed object, we need
/// to pass it in as an argument on the constructor.
///
/// @version    0.2.0
/// @since      0.2.0
@Service
public class ThreadRunnerService implements ServiceRunner {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The application context.
    private final ApplicationContext applicationContext;

    /// The constructor.
    ///
    /// @param  applicationContext    org.springframework.context.ApplicationContext
    public ThreadRunnerService(final ApplicationContext applicationContext) {
        super();

        this.applicationContext = applicationContext;
    }

    /// Runs the service.
    @Override
    public void runService() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        // No explicit or implicit close

        ThreadRunner threadRunner1 = this.applicationContext.getBean(ThreadRunner.class);

        threadRunner1.setup();
        threadRunner1.runThreads();

        threadRunner1 = null;

        System.gc();

        // Use an explicit close

        final ThreadRunner threadRunner2 = this.applicationContext.getBean(ThreadRunner.class);

        threadRunner2.setup();
        threadRunner2.runThreads();
        threadRunner2.close();

        // Use an implicit close

        try (final ThreadRunner threadRunner3 = this.applicationContext.getBean(ThreadRunner.class)) {
            threadRunner3.setup();
            threadRunner3.runThreads();
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
