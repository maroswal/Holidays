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
package com.fhellmann.holidays;

import java.time.LocalDate;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

/**
 * The calculator interface is the best Java 8 solution to calculate the holiday
 * only if necessary.
 *
 * @author Fabio Hellmann
 *
 */
@FunctionalInterface
public interface Calculator {
    /**
     * In many cultures easter is a holiday. This day is mostly used as a
     * relative reference value to calculate other holidays. It can be
     * calculated by Gauss formula.
     */
    IntFunction<LocalDate> EASTER = year -> {
        final int i = year % 19;
        final int j = year / 100;
        final int k = year % 100;

        final int l = (19 * i + j - j / 4 - (j - (j + 8) / 25 + 1) / 3 + 15) % 30;
        final int m = (32 + 2 * (j % 4) + 2 * (k / 4) - l - k % 4) % 7;
        final int n = l + m - 7 * ((i + 11 * l + 22 * m) / 451) + 114;

        final int month = n / 31;
        final int day = n % 31 + 1;

        return LocalDate.of(year, month, day);
    };

    IntUnaryOperator EASTER_DAY_COUNT = year -> {
        final LocalDate cal = EASTER.apply(year);
        return (30 - cal.getDayOfMonth()) % 7;
    };

    /**
     * Calculate the holiday relative by year.
     *
     * @param year
     *            of holiday.
     * @return the date.
     */
    LocalDate calculate(int year);
}
