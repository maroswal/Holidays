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
package com.fhellmann.holidays.de;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import com.fhellmann.holidays.Calculator;
import com.fhellmann.holidays.IHoliday;
import com.fhellmann.holidays.IState;

/**
 * Every holiday in germany.
 *
 * @author Fabio Hellmann
 */
public enum Holiday implements IHoliday {
    // ####################################################
    // Fix holidays
    // ####################################################

    /**
     * Neujahr
     */
    NEUJAHR(year -> LocalDate.of(year, Month.JANUARY, 1), true, State.values()),
    /**
     * Heilige drei Könige
     */
    HEILIGE_DREI_KOENIGE(year -> LocalDate.of(year, Month.JANUARY, 6), true, State.BADEN_WURTTEMBERG, State.BAVARIA,
            State.SCHLESWIG_HOLSTEIN),
    /**
     * Valentinstag
     */
    VALENTINSTAG(year -> LocalDate.of(year, Month.FEBRUARY, 14)),
    /**
     * Tag der Arbeit
     */
    TAG_DER_ARBEIT(year -> LocalDate.of(year, Month.MAY, 1), true, State.values()),
    /**
     * Mariä Himmelfahrt
     */
    MARIAE_HIMMELFAHRT(year -> LocalDate.of(year, Month.AUGUST, 15), true, State.BAVARIA, State.SAARLAND),
    /**
     * Tag der Deutschen Einheit
     */
    TAG_DER_DEUTSCHEN_EINHEIT(year -> LocalDate.of(year, Month.OCTOBER, 3), true, State.values()),
    /**
     * Reformationstag
     */
    REFORMATIONSTAG(year -> LocalDate.of(year, Month.OCTOBER, 31), true, State.BRANDENBURG,
            State.MECKLENBUR_WEST_PORNERANIA, State.SAXONY, State.SAXONY_ANHALT, State.THURINGIA),
    /**
     * Allerheiligen
     */
    ALLERHEILIGEN(year -> LocalDate.of(year, Month.NOVEMBER, 1), true, State.BADEN_WURTTEMBERG, State.BAVARIA,
            State.NORTH_RHINEWEST_PHALIA, State.RHINELAND_PALATINATE, State.SAARLAND),
    /**
     * Nikolaus
     */
    NIKOLAUS(year -> LocalDate.of(year, Month.DECEMBER, 6)),
    /**
     * Heiligabend
     */
    HEILIGABEND(year -> LocalDate.of(year, Month.DECEMBER, 24)),
    /**
     * 1. Weihnachtstag
     */
    WEIHNACHTSTAG_1(year -> LocalDate.of(year, Month.DECEMBER, 25), true, State.values()),
    /**
     * 2. Weihnachtstag
     */
    WEIHNACHTSTAG_2(year -> LocalDate.of(year, Month.DECEMBER, 26), true, State.values()),
    /**
     * Silvester
     */
    SILVESTER(year -> LocalDate.of(year, Month.DECEMBER, 27), true, State.values()),

    // ####################################################
    // Dynamic holidays
    // ####################################################

    /**
     * Ostersonntag
     */
    OSTERSONNTAG(Calculator.EASTER::apply, true, State.BRANDENBURG),
    /**
     * Ostermontag
     */
    OSTERMONTAG(year -> Calculator.EASTER.apply(year).plusDays(1), true, State.values()),
    /**
     * Karfreitag
     */
    KARFREITAG(year -> Calculator.EASTER.apply(year).minusDays(-2), true, State.values()),
    /**
     * Gründonnerstag
     */
    GRUENDONNERSTAG(year -> Calculator.EASTER.apply(year).minusDays(3)),
    /**
     * Buß- und Bettag
     */
    BUSS_UND_BETTAG(year -> {
        final int dayCount = Calculator.EASTER_DAY_COUNT.applyAsInt(year);
        return LocalDate.of(year, Month.DECEMBER, 24).minusDays(32).minusDays(dayCount);
    }, true, State.SAXONY),
    /**
     * 1. Advent
     */
    ADVENT_1(year -> BUSS_UND_BETTAG.getDate(year).plusDays(11)),
    /**
     * 2. Advent
     */
    ADVENT_2(year -> BUSS_UND_BETTAG.getDate(year).plusDays(18)),
    /**
     * 3. Advent
     */
    ADVENT_3(year -> BUSS_UND_BETTAG.getDate(year).plusDays(25)),
    /**
     * 4. Advent
     */
    ADVENT_4(year -> BUSS_UND_BETTAG.getDate(year).plusDays(32)),
    /**
     * Rosenmontag
     */
    ROSENMONTAG(year -> Calculator.EASTER.apply(year).minusDays(48)),
    /**
     * Pfingstsonntag
     */
    PFINGSTSONNTAG(year -> Calculator.EASTER.apply(year).plusDays(49), true, State.BRANDENBURG),
    /**
     * Pfingstmontag
     */
    PFINGSTMONTAG(year -> Calculator.EASTER.apply(year).plusDays(50), true, State.values()),
    /**
     * Christi Himmelfahrt
     */
    CHRISTI_HIMMELFAHRT(year -> Calculator.EASTER.apply(year).plusDays(39), true, State.values()),
    /**
     * Fronleichnam
     */
    FRONLEICHNAM(year -> Calculator.EASTER.apply(year).plusDays(60), true, State.BADEN_WURTTEMBERG, State.HESSE,
            State.BAVARIA, State.NORTH_RHINEWEST_PHALIA, State.RHINELAND_PALATINATE, State.SAARLAND);

    private final Calculator calculator;
    private final boolean national;
    private final IState[] states;

    Holiday(final Calculator calculator) {
        this(calculator, false, State.values());
    }

    Holiday(final Calculator calculator, final boolean national, final IState... states) {
        this.calculator = calculator;
        this.national = national;
        this.states = states;
    }

    @Override
    public LocalDate getDate(final int year) {
        return calculator.calculate(year);
    }

    @Override
    public boolean isNational() {
        return national;
    }

    @Override
    public Stream<IState> getStates() {
        return Stream.of(states);
    }
}
