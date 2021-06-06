package ru.kim.addressbook.tests;

import org.testng.annotations.Test;

public class ContractDeletionTests extends TestBase {

    @Test
    public void testContractDeletion() {
        app.getHomeHelper().selectContract();
        app.getHomeHelper().deleteSelectedContact();
        app.getHomeHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
