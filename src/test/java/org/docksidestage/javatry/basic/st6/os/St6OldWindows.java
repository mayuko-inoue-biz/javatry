package org.docksidestage.javatry.basic.st6.os;

/**
 * @author mayukorin
 */
public class St6OldWindows extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OldWindows(String loginId) {
        super(loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }
}
