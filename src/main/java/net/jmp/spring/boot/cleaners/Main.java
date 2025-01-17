package net.jmp.spring.boot.cleaners;

/*
 * (#)Main.java 0.2.0   01/15/2025
 * (#)Main.java 0.1.0   01/15/2025
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

import net.jmp.spring.boot.cleaners.classes.Person;

import net.jmp.spring.boot.cleaners.services.*;

import static net.jmp.util.logging.LoggerUtils.entry;
import static net.jmp.util.logging.LoggerUtils.exit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.core.env.Environment;

import org.springframework.stereotype.Component;

/// The main application class.
///
/// @version    0.2.0
/// @since      0.1.0
@Component
public class Main implements Runnable {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The application context.
    private final ApplicationContext applicationContext;

    /// The environment.
    private final Environment environment;

    /// The room service.
    private final RoomService roomService;

    /// The thread runner service.
    private final ThreadRunnerService threadRunnerService;

    /// The constructor.
    ///
    /// @param  applicationContext  org.springframework.context.ApplicationContext
    /// @param  environment         org.springframework.core.env.Environment
    /// @param  roomService         net.jmp.spring.boot.cleaners.services.RoomService
    /// @param  threadRunnerService net.jmp.spring.boot.cleaners.services.ThreadRunnerService
    public Main(final ApplicationContext applicationContext,
                final Environment environment,
                final RoomService roomService,
                final ThreadRunnerService threadRunnerService) {
        super();

        this.applicationContext = applicationContext;
        this.environment = environment;
        this.roomService = roomService;
        this.threadRunnerService = threadRunnerService;
    }

    ///
    /// The run method.
    @Override
    public void run() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Hello from: {}:{}",
                    this.environment.getProperty("spring.application.name"),
                    this.environment.getProperty("spring.application.version"));
        }

        // Run the services

        this.roomService.runService();
        this.threadRunnerService.runService();

        // Work with prototype-scoped beans

        final Person john = this.applicationContext.getBean(Person.class);
        final Person jane = this.applicationContext.getBean(Person.class);

        john.setAge(19);
        john.setName("John");
        jane.setAge(22);
        jane.setName("Jane");

        this.logger.info("{} is {} years old", john.getName(), john.getAge());
        this.logger.info("{} is {} years old", jane.getName(), jane.getAge());

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
