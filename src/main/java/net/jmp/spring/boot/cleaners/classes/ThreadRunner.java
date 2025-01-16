package net.jmp.spring.boot.cleaners.classes;

/*
 * (#)ThreadRunner.java 0.2.0   01/15/2025
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

import java.lang.ref.Cleaner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.jmp.spring.boot.cleaners.components.ResourceCleaner;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// The thread runner class.
///
/// @version    0.2.0
/// @since      0.2.0
public final class ThreadRunner implements AutoCloseable {
    /// The default number of threads.
    private static final int DEFAULT_NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The resource(s) that require cleanup.
    /// This state class must never refer to ThreadRunner.
    private static class State implements Runnable {
        /// The logger.
        private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        /// The executor service which must be closed.
        private final ExecutorService executorService;

        /// The constructor.
        ///
        /// @param  executorService java.util.concurrent.ExecutorService
        private State(final ExecutorService executorService) {
            this.executorService = executorService;
        }

        /// The run method.  This method
        /// will be invoked at most one
        /// time by either the close method
        /// or the cleaner.
        @Override
        public void run() {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(entry());
            }

            if (!this.executorService.isShutdown()) {
                this.executorService.shutdown();

                this.logger.info("The executor service has been shut down");

                if (this.executorService.isTerminated()) {
                    this.logger.info("The executor service has been terminated");
                }
            } else {
                this.logger.info("The executor service has already been shut down");
            }

            if (this.logger.isTraceEnabled()) {
                this.logger.trace(exit());
            }
        }
    }

    /// The state.
    private final State state;

    /// The cleanable. Cleans up when the thread runner has become phantom reachable.
    private final Cleaner.Cleanable cleanable;

    /// The constructor.
    ///
    /// @param  resourceCleaner net.jmp.spring.boot.cleaners.components.ResourceCleaner
    public ThreadRunner(final ResourceCleaner resourceCleaner) {
        this.state = new State(Executors.newFixedThreadPool(DEFAULT_NUMBER_OF_THREADS));
        this.cleanable = resourceCleaner.getCleaner().register(this, this.state);
    }

    /// Runs threads.
    public void runThreads() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Return the executor service.
    ///
    /// @return java.util.concurrent.ExecutorService
    public ExecutorService getExecutorService() {
        return this.state.executorService;
    }

    /// The close method.
    @Override
    public void close() {
        this.cleanable.clean();
    }
}
