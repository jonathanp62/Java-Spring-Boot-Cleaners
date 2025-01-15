package net.jmp.spring.boot.cleaners.classes;

/*
 * (#)Room.java  0.2.0   01/15/2025
 * (#)Room.java  0.1.0   01/15/2025
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

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// The room class.
///
/// @version    0.2.0
/// @since      0.1.0
public final class Room implements AutoCloseable{
    /// The cleaner. One cleaner can manage multiple items.
    private static final Cleaner cleaner = Cleaner.create();

    /// The resource(s) that require cleanup.
    /// This state class must never refer to Room.
    private static class RoomState implements Runnable {
        /// The logger.
        private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        /// The name of the room.
        private final String name;

        /// The number of junk piles that need to be cleaned up.
        private int numberOfJunkPiles;

        /// The constructor.
        ///
        /// @param  name                java.lang.String
        /// @param  numberOfJunkPiles   int
        private RoomState(final String name, final int numberOfJunkPiles) {
            this.name = name;
            this.numberOfJunkPiles = numberOfJunkPiles;
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

            final int pilesToClean = this.numberOfJunkPiles;

            this.numberOfJunkPiles = 0;     // The cleaning action

            this.logger.info("{}'s room had {} junk piles and now has been cleaned", this.name, pilesToClean);

            if (this.logger.isTraceEnabled()) {
                this.logger.trace(exit());
            }
        }
    }

    /// The state of the room that requires cleanup.
    private final RoomState roomState;

    /// The cleanable. Cleans the room when the room haas become phantom reachable.
    private final Cleaner.Cleanable cleanable;

    /// The constructor.
    ///
    /// @param   name               java.lang.String
    /// @param   numberOfJunkPiles  int
    public Room(final String name, final int numberOfJunkPiles) {
        this.roomState = new RoomState(name, numberOfJunkPiles);
        this.cleanable = cleaner.register(this, this.roomState);
    }

    /// Return the number of junk piles.
    ///
    /// @return int
    public int getNumberOfJunkPiles() {
        return this.roomState.numberOfJunkPiles;
    }

    /// Return the name of the room.
    ///
    /// @return java.lang.String
    public String getName() {
        return this.roomState.name;
    }

    /// The close method.
    @Override
    public void close() {
        this.cleanable.clean();
    }
}
