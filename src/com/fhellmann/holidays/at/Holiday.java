/**
 *
 */
package com.fhellmann.holidays.at;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import com.fhellmann.holidays.Calculator;
import com.fhellmann.holidays.IHoliday;
import com.fhellmann.holidays.IState;

/**
 * Every holiday in austria
 *
 * @author Fabio Hellmann
 *
 */
public enum Holiday implements IHoliday {
    // ####################################################
    // Fix holidays
    // ####################################################

    /** Neujahr */
    NEUJAHR(year -> LocalDate.of(year, Month.JANUARY, 1), true, State.values()),
    /** Heilige drei Könige */
    HEILIGE_DREI_KOENIGE(year -> LocalDate.of(year, Month.JANUARY, 6), true, State.values()),
    /** St. Josef */
    ST_JOSEF(year -> LocalDate.of(year, Month.MARCH, 19), false, State.VIENNA, State.STYRIA, State.TYROL,
            State.VORARLBERG),
    /** Staatsfeiertag */
    STAATSFEIERTAG(year -> LocalDate.of(year, Month.MAY, 1), true, State.values()),
    /** St. Florian */
    ST_FLORIAN(year -> LocalDate.of(year, Month.MAY, 4), false, State.UPPER_AUSTRIA),
    /** Mariä Himmelfahrt */
    MARIAE_HIMMELFAHRT(year -> LocalDate.of(year, Month.AUGUST, 15), true, State.values()),
    /** St. Rupert */
    ST_RUPERT(year -> LocalDate.of(year, Month.SEPTEMBER, 24), false, State.SALZBURG),
    /** Tag der Volksabstimmung */
    TAG_DER_VOLKSABSTIMMUNG(year -> LocalDate.of(year, Month.OCTOBER, 10), false, State.SALZBURG),
    /** Nationalfeiertag */
    NATIONALFEIERTAG(year -> LocalDate.of(year, Month.OCTOBER, 26), true, State.values()),
    /** Allerheiligen */
    ALLERHEILIGEN(year -> LocalDate.of(year, Month.NOVEMBER, 1), true, State.values()),
    /** St. Martin */
    ST_MARTIN(year -> LocalDate.of(year, Month.NOVEMBER, 11), false, State.BURGENLAND),
    /** St. Leopold */
    ST_LEOPOLD(year -> LocalDate.of(year, Month.NOVEMBER, 15), false, State.LOWER_AUSTRIA, State.VIENNA),
    /** Mariä Empfängnis */
    MARIAE_EMPFAENGNIS(year -> LocalDate.of(year, Month.DECEMBER, 8), true, State.values()),
    /** Heiliger Abend */
    HEILIGER_ABEND(year -> LocalDate.of(year, Month.DECEMBER, 24), false, State.values()),
    /** Weihnachten */
    WEIHNACHTEN(year -> LocalDate.of(year, Month.DECEMBER, 25), true, State.values()),
    /** Stefanitag */
    STEFANITAG(year -> LocalDate.of(year, Month.DECEMBER, 26), true, State.values()),
    /** Silvester */
    SILVESTER(year -> LocalDate.of(year, Month.DECEMBER, 27), false, State.values()),

    // ####################################################
    // Dynamic holidays
    // ####################################################

    /** Ostermontag */
    OSTERMONTAG(year -> Calculator.EASTER.apply(year).plusDays(1), true, State.values()),
    /** Karfreitag */
    KARFREITAG(year -> Calculator.EASTER.apply(year).minusDays(2), false, State.values()),
    /** Pfingstmontag */
    PFINGSTMONTAG(year -> Calculator.EASTER.apply(year).plusDays(50), true, State.values()),
    /** Christi Himmelfahrt */
    CHRISTI_HIMMELFAHRT(year -> Calculator.EASTER.apply(year).plusDays(39), true, State.values()),
    /** Fronleichnam */
    FRONLEICHNAM(year -> Calculator.EASTER.apply(year).plusDays(60), true, State.values());

    private final Calculator calculator;
    private final boolean national;
    private final IState[] states;

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
