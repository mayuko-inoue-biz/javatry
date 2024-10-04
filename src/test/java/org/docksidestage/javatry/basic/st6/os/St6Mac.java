package org.docksidestage.javatry.basic.st6.os;

/**
 * @author mayukorin
 */
public class St6Mac extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6Mac(String loginId) {
        super(loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
