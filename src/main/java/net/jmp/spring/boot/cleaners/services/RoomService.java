package net.jmp.spring.boot.cleaners.services;

/*
 * (#)RoomService.java  0.1.0   01/15/2025
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

import net.jmp.spring.boot.cleaners.classes.Room;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Service;

/// The room service. Since auto-wiring only works on Spring managed
/// objects, and Room is not a Spring managed object, we need to pass
/// it in as an argument on the constructor.
///
/// @version    0.1.0
/// @since      0.1.0
@Service
public class RoomService implements ServiceRunner {
    /// The message text when logging a room.
    private static final String ROOM_MESSAGE_TEXT = "{}'s room has {} junk piles in it";

    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The application context.
    private final ApplicationContext applicationContext;

    /// The constructor.
    ///
    /// @param  applicationContext  org.springframework.context.ApplicationContext
    public RoomService(final ApplicationContext applicationContext) {
        super();

        this.applicationContext = applicationContext;
    }

    /// Runs the service.
    @Override
    public void runService() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        // The try-with-resources invokes the close method on Room

        try (final Room myRoom = this.applicationContext.getBean(Room.class)) {
            myRoom.setup("Jonathan", 2);

            if (this.logger.isInfoEnabled()) {
                this.logger.info(ROOM_MESSAGE_TEXT, myRoom.getName(), myRoom.getNumberOfJunkPiles());
            }
        }

        // Using an explicit close

        final Room garage = this.applicationContext.getBean(Room.class);

        garage.setup("Garage", 12);

        if (this.logger.isInfoEnabled()) {
            this.logger.info(ROOM_MESSAGE_TEXT, garage.getName(), garage.getNumberOfJunkPiles());
        }

        garage.close();

        // Not using try-with-resources or an explicit close

        Room herRoom = this.applicationContext.getBean(Room.class);

        herRoom.setup("Dena", 5);

        if (this.logger.isInfoEnabled()) {
            this.logger.info(ROOM_MESSAGE_TEXT, herRoom.getName(), herRoom.getNumberOfJunkPiles());
        }

        herRoom = null; // Abandon the reference in anticipation of garbage collection

        // Garbage collection will cause the reference to herRoom to become phantom reachable
        // Then the close method on Room will be invoked by the cleanable

        System.gc();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
