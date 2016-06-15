/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Xavier
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({tests.PaysheetTest.class, tests.PaymentTest.class, tests.billsTest.class, tests.EmployeeTest.class, tests.articlesTest.class})
public class AllCanBeFixed_TestSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
