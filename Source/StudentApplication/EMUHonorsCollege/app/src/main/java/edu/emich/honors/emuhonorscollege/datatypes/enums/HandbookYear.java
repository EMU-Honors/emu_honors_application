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
    YEAR_2015("2015-2016");
    
    private String handbookName;

    private HandbookYear(String s)
    {
        handbookName = s;
    }
    @Override
    public String toString() {
        return handbookName;
    }
}
