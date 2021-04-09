package ua.com.alevel.date;

import ua.com.alevel.exception.DateClassException;

import java.util.Scanner;

public class DateClass implements Comparable<DateClass> {

    private long dateInMilliseconds;
    private int dateFormat = 4;

    private final long MILLISECONDS_IN_SECOND = 1000;
    private final long MILLISECONDS_IN_MINUTE = 60 * MILLISECONDS_IN_SECOND;
    private final long MILLISECONDS_IN_HOUR = 60 * MILLISECONDS_IN_MINUTE;
    private final long MILLISECONDS_IN_DAY = 24 * MILLISECONDS_IN_HOUR;

    private final long SECONDS_IN_MINUTE = 60;
    private final long MINUTES_IN_HOUR = 60;
    private final long HOURS_IN_DAY = 24;
    private final long MONTHS_IN_YEAR = 12;
    private final long YEARS_IN_CENTURY = 100;

    public DateClass() {
        final int GMT = +3;
        DateClass year1970 = new DateClass(0);
        year1970.addYears(1970);
        dateInMilliseconds = differenceInMilliseconds(year1970) +
                System.currentTimeMillis() + GMT * MILLISECONDS_IN_HOUR;
    }

    public DateClass(long milliseconds) {
        dateInMilliseconds = milliseconds;
    }

    public DateClass(String date) throws DateClassException {
        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int months = 0;
        int years = 0;

        try {
            // 01/05/47 00:24:00
            if (date.contains("/")) {
                String[] input = date.split("[/ :]");
                // Day
                // _/05/47
                if (input.length == 0 || input[0].equals("")) {
                    days = 1;
                    // 01/05/47
                } else {
                    days = Integer.parseInt(input[0]);
                }

                // Month
                // 01/_/47
                if (input.length < 2 || input[1].equals("")) {
                    months = 0;
                    // 01/05/47
                } else {
                    months = Integer.parseInt(input[1]);
                }

                // Year
                // 01/05/_
                if (input.length < 3 || input[2].equals("")) {
                    years = 2021;
                    // 01/05/47
                } else if (input[2].length() == 2) {
                    years = 1900 + Integer.parseInt(input[2]);
                    // 01/05/2005
                } else {
                    years = Integer.parseInt(input[2]);
                }
                // 01/05/47 00:15:00
                if (input.length > 3) {
                    // Hour
                    hours = Integer.parseInt(input[3]);
                    minutes = Integer.parseInt(input[4]);
                    seconds = Integer.parseInt(input[5]);
                }
                // 09 March 1256 59:59
            } else {
                String[] input = date.split("[ :]");

                String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December"};
                boolean correctMonth = false;

                // Day
                days = Integer.parseInt(input[0]);

                // Month
                for (String s : input) {
                    for (int j = 0; j < monthsNames.length; j++) {
                        if (s.equals(monthsNames[j])) {
                            months = j + 1;
                            correctMonth = true;
                        }
                    }
                }

                if (!correctMonth) {
                    throw new DateClassException("Wrong data input");
                }

                years = Integer.parseInt(input[2]);
                hours = Integer.parseInt(input[3]);
                minutes = Integer.parseInt(input[4]);
            }

            addYears(years);
            addMonths(months - 1); // We start from January
            addDays(days - 1); // We start from 01 January
            addHours(hours);
            addMinutes(minutes);
            addSeconds(seconds);

        } catch (Exception ex) {
            throw new DateClassException("Wrong data input");
        }
    }

    //-------------------------Get Days Left--------------------------
    private long getDaysLeft(DateClass other) {
        long days = (long) differenceInDays(other);
        boolean daysLeft = true;

        int[] normalYearMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYearMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        while (true) {
            // 3 normal years before leap year
            for (int i = 0; i < 3; i++) {
                // Normal years
                for (int j = 0; j < MONTHS_IN_YEAR; j++) {
                    if (days - normalYearMonths[j] >= 0) {
                        days -= normalYearMonths[j];
                    } else {
                        return days;
                    }
                }
            }

            // Leap year
            for (int i = 0; i < MONTHS_IN_YEAR; i++) {
                if (days - leapYearMonths[i] >= 0) {
                    days -= leapYearMonths[i];
                } else {
                    return days;
                }
            }
        }
    }

    //-------------------------Is Leap Lear--------------------------
    private boolean isLeapYear(long year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    //-------------------------Difference--------------------------
    public void difference(DateClass other) {
        long milliseconds = differenceInMilliseconds(other) % MILLISECONDS_IN_SECOND;
        long seconds = (long) (differenceInSeconds(other) % SECONDS_IN_MINUTE);
        long minutes = (long) (differenceInMinutes(other) % MINUTES_IN_HOUR);
        long hours = (long) (differenceInHours(other) % HOURS_IN_DAY);
        long days = getDaysLeft(other);
        long months = (long) (differenceInMonths(other) % MONTHS_IN_YEAR);
        long years = (long) (differenceInYears(other) % YEARS_IN_CENTURY);
        long centuries = (long) (differenceInCenturies(other));

        System.out.printf("Difference between dates is %d centuries, " +
                        "%d years, %d months, %d days, %d hours, %d minutes, " +
                        "%d seconds, %d milliseconds\n",
                centuries, years, months, days, hours, minutes, seconds, milliseconds);
    }

    public long differenceInMilliseconds(DateClass other) {
        return Math.abs(dateInMilliseconds - other.dateInMilliseconds);
    }

    public double differenceInSeconds(DateClass other) {
        return Math.abs(dateInMilliseconds - other.dateInMilliseconds)
                / (double) MILLISECONDS_IN_SECOND;
    }

    public double differenceInMinutes(DateClass other) {
        return Math.abs(dateInMilliseconds - other.dateInMilliseconds)
                / (double) MILLISECONDS_IN_MINUTE;
    }

    public double differenceInHours(DateClass other) {
        return Math.abs(dateInMilliseconds - other.dateInMilliseconds)
                / (double) MILLISECONDS_IN_HOUR;
    }

    public double differenceInDays(DateClass other) {
        return Math.abs(dateInMilliseconds - other.dateInMilliseconds)
                / (double) MILLISECONDS_IN_DAY;
    }

    public double differenceInMonths(DateClass other) {
        long days = (long) differenceInDays(other);
        long months = 0;
        double remainder = 0.0;
        boolean daysLeft = true;

        int[] normalYearMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYearMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        while (daysLeft) {
            // 3 normal years before leap year
            for (int i = 0; i < 3 && daysLeft; i++) {
                // Normal years
                for (int j = 0; j < MONTHS_IN_YEAR && daysLeft; j++) {
                    if (days - normalYearMonths[j] >= 0) {
                        days -= normalYearMonths[j];
                        months++;
                    } else {
                        remainder = days / (double) normalYearMonths[j];
                        daysLeft = false;
                    }
                }
            }

            // Leap year
            for (int i = 0; i < MONTHS_IN_YEAR && daysLeft; i++) {
                if (days - leapYearMonths[i] >= 0) {
                    days -= leapYearMonths[i];
                    months++;
                } else {
                    remainder = days / (double) leapYearMonths[i];
                    daysLeft = false;
                }
            }
        }
        return months + remainder;
    }

    public double differenceInYears(DateClass other) {
        return differenceInMonths(other) / MONTHS_IN_YEAR;
    }

    public double differenceInCenturies(DateClass other) {
        return differenceInYears(other) / YEARS_IN_CENTURY;
    }

    //-------------------------Add--------------------------
    public void addMilliseconds(long milliseconds) {
        dateInMilliseconds += milliseconds;
    }

    public void addSeconds(long seconds) {
        dateInMilliseconds += seconds * MILLISECONDS_IN_SECOND;
    }

    public void addMinutes(long minutes) {
        dateInMilliseconds += minutes * MILLISECONDS_IN_MINUTE;
    }

    public void addHours(long hours) {
        dateInMilliseconds += hours * MILLISECONDS_IN_HOUR;
    }

    public void addDays(long days) {
        addHours(days * HOURS_IN_DAY);
    }

    public void addMonths(long months) {
        long days = 0;
        boolean monthsLeft = true;

        int[] normalYearMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYearMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        while (monthsLeft) {
            // 3 normal years before leap year
            for (int i = 0; i < 3 && monthsLeft; i++) {
                // Normal years
                for (int j = 0; j < MONTHS_IN_YEAR && monthsLeft; j++) {
                    if (months - 1 >= 0) {
                        days += normalYearMonths[j];
                        months--;
                    } else {
                        monthsLeft = false;
                    }
                }
            }

            // Leap year
            for (int i = 0; i < MONTHS_IN_YEAR && monthsLeft; i++) {
                if (months - 1 >= 0) {
                    days += leapYearMonths[i];
                    months--;
                } else {
                    monthsLeft = false;
                }
            }
        }
        addDays(days);
    }

    public void addYears(long years) {
        // Leap yer occurs every 4 years
        long leapYears = years / 4;
        long normalYears = years - leapYears;

        long leapYearsMilliseconds = leapYears * 366 * MILLISECONDS_IN_DAY;
        long normalYearsMilliseconds = normalYears * 365 * MILLISECONDS_IN_DAY;

        dateInMilliseconds += leapYearsMilliseconds + normalYearsMilliseconds;
    }

    public void addCenturies(long centuries) {
        addYears(centuries * YEARS_IN_CENTURY);
    }

    //-------------------------Subtract--------------------------
    public void subtractMilliseconds(long milliseconds) throws DateClassException {
        if (dateInMilliseconds - milliseconds >= 0) {
            dateInMilliseconds -= milliseconds;
        } else {
            throw new DateClassException("Can not contain negative year");
        }
    }

    public void subtractSeconds(long seconds) throws DateClassException {
        if (dateInMilliseconds - seconds * MILLISECONDS_IN_SECOND >= 0) {
            dateInMilliseconds -= seconds * MILLISECONDS_IN_SECOND;
        } else {
            throw new DateClassException("Can not contain negative year");
        }
    }

    public void subtractMinutes(long minutes) throws DateClassException {
        if (dateInMilliseconds - minutes * MILLISECONDS_IN_MINUTE >= 0) {
            dateInMilliseconds -= minutes * MILLISECONDS_IN_MINUTE;
        } else {
            throw new DateClassException("Can not contain negative year");
        }
    }

    public void subtractHours(long hours) throws DateClassException {
        if (dateInMilliseconds - hours * MILLISECONDS_IN_HOUR >= 0) {
            dateInMilliseconds -= hours * MILLISECONDS_IN_HOUR;
        } else {
            throw new DateClassException("Can not contain negative year");
        }
    }

    public void subtractDays(long days) throws DateClassException {
        subtractHours(days * HOURS_IN_DAY);
    }

    public void subtractYears(long years) throws DateClassException {
        // Leap yer occurs every 4 years
        long leapYears = years / 4;
        long normalYears = years - leapYears;

        long leapYearsMilliseconds = leapYears * 366 * MILLISECONDS_IN_DAY;
        long normalYearsMilliseconds = normalYears * 365 * MILLISECONDS_IN_DAY;

        if (dateInMilliseconds - (leapYearsMilliseconds + normalYearsMilliseconds) >= 0) {
            dateInMilliseconds -= leapYearsMilliseconds + normalYearsMilliseconds;
        } else {
            throw new DateClassException("Can not contain negative year");
        }

    }

    public void subtractCenturies(long centuries) throws DateClassException {
        subtractYears(centuries * YEARS_IN_CENTURY);
    }

    //-------------------------Compare--------------------------
    public boolean larger(DateClass other) {
        return dateInMilliseconds > other.dateInMilliseconds;
    }

    @Override
    public int compareTo(DateClass other) {
        return dateInMilliseconds - other.dateInMilliseconds > 0 ? 1 :
                dateInMilliseconds - other.dateInMilliseconds < 0 ? -1 : 0;
    }

    //-------------------------Date Format--------------------------
    public void setDateFormat() {
        System.out.println("------------------------------------------------");
        System.out.println(
                "1) dd/mm/yy - 01/12/21\n" +
                        "2) m/d/yyyy - 3/4/2021\n" +
                        "3) mmm-d-yy - March 4 21\n" +
                        "4) dd-mmm-yyyy 00:00 - 09 April 789 45:23\n");
        System.out.print("Choose what data format you would like to use: ");

        Scanner input = new Scanner(System.in);
        String options = "1234";
        String choice = input.nextLine();

        while (!options.contains(choice) || choice.length() != 1) {
            System.out.print("Please enter valid number: ");
            choice = input.nextLine();
        }

        switch (choice) {
            case "1" -> dateFormat = 1;
            case "2" -> dateFormat = 2;
            case "3" -> dateFormat = 3;
            case "4" -> dateFormat = 4;
        }

        System.out.println("Date format set successfully");
        System.out.println("------------------------------------------------");
    }

    //-------------------------Output--------------------------
    public void print() throws DateClassException {
        try {
            String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};
            DateClass year0 = new DateClass(0);

            long milliseconds = differenceInMilliseconds(year0) % MILLISECONDS_IN_SECOND;
            long seconds = (long) differenceInSeconds(year0) % SECONDS_IN_MINUTE;
            long minutes = (long) differenceInMinutes(year0) % MINUTES_IN_HOUR;
            long hours = (long) differenceInHours(year0) % HOURS_IN_DAY;
            long days = getDaysLeft(year0) + 1; // (00 January must be 01 January)
            long months = (long) differenceInMonths(year0) % MONTHS_IN_YEAR + 1; // To output correctly
            long years = (long) differenceInYears(year0);

            switch (dateFormat) {
                // dd/mm/yy
                case 1 -> {
                    if (days < 10) {
                        System.out.print("0");
                    }
                    System.out.print(days + "/");
                    if (months < 10) {
                        System.out.print("0");
                    }
                    System.out.print(months + "/");
                    if ((years % YEARS_IN_CENTURY) < 10) {
                        System.out.print("0");
                    }
                    System.out.print(years % YEARS_IN_CENTURY);
                    System.out.println();
                }
                // m/d/yyyy
                case 2 -> System.out.println(months + "/" + days + "/" + years);
                // mmm-d-yy
                case 3 -> System.out.println(monthsNames[(int) months - 1] + " " + days +
                        " " + years % YEARS_IN_CENTURY);
                // dd-mmm-yyy 00:00
                case 4 -> {
                    if (days < 10) {
                        System.out.print("0");
                    }
                    System.out.print(days + " ");
                    System.out.print(monthsNames[(int) months - 1] + " " + years + " ");
                    if (hours < 10) {
                        System.out.print("0");
                    }
                    System.out.print(hours + ":");
                    if (minutes < 10) {
                        System.out.print("0");
                    }
                    System.out.println(minutes);
                }
            }
        } catch (Exception ex) {
            throw new DateClassException("Wrong output");
        }
    }
}
