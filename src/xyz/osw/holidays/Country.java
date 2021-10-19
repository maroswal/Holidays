/**
 * Copyright 2014 Fabio Hellmann
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.osw.holidays;

import java.util.stream.Stream;

import xyz.osw.holidays.at.State;
import xyz.osw.holidays.de.Holiday;

/**
 * The countries with states and holidays.
 *
 * @author Fabio Hellmann
 *
 */
public enum Country {
    /** Germany */
    GERMANY(xyz.osw.holidays.de.State.values(), Holiday.values()),
    /** Austria */
    AUSTRIA(State.values(), xyz.osw.holidays.at.Holiday.values());

    private final IState[] states;
    private final IHoliday[] holidays;

    private Country(final IState[] states, final IHoliday[] holidays) {
        this.states = states;
        this.holidays = holidays;
    }

    /**
     * Get the holidays of the country.
     *
     * @return all holidays.
     */
    public Stream<IHoliday> getHolidays() {
        return Stream.of(holidays);
    }

    /**
     * Get the states of the country.
     *
     * @return all states.
     */
    public Stream<IState> getStates() {
        return Stream.of(states);
    }
}
