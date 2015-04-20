package edu.emich.honors.emuhonorscollege.datatypes.enums;

/**
 * This class may not be necessary, but acts as a placeholder until database connections are set up.
 */
public enum HandbookYear {
    YEAR_2010("2010-2011"),
    YEAR_2011("2011-2012"),
    YEAR_2012("2012-2013"),
    YEAR_2013("2013-2014"),
    YEAR_2014("2014-2015"),
    YEAR_2015("2015-2016"),
    YEAR_2016("2016-2017"),
    YEAR_2017("2017-2018"),
    YEAR_2018("2018-2019"),
    YEAR_2019("2019-2020"),
    YEAR_2020("2020-2021"),
    YEAR_2021("2021-2022"),
    YEAR_2022("2022-2023"),
    YEAR_2023("2023-2024"),
    YEAR_2024("2024-2025"),
    YEAR_2025("2025-2026"),
    YEAR_2026("2026-2027"),
    YEAR_2027("2027-2028"),
    YEAR_2028("2028-2029"),
    YEAR_2029("2029-2030");

    private String handbookName;

    private HandbookYear(String s) {
        handbookName = s;
    }

    @Override
    public String toString() {
        return handbookName;
    }
}
