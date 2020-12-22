package com.me.driver;

import com.me.test.*;
import com.relevantcodes.extentreports.ExtentReports;

public class Driver {



    public static void main(String[] args) {
        TestCase_1_Negative test_1 = new TestCase_1_Negative();

        //Test case 1 (Negative): Create test scenarios for registering 1 user
        try{
            test_1.runTest(); 
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_1_Positive test_1_pos = new TestCase_1_Positive();

        //Test case 1 (Postive): Create test scenarios for registering 1 user
        try{
            test_1_pos.runTest();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_2 testCase_2 = new TestCase_2();
        try{
            testCase_2.runTest();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_3 testCase_3 = new TestCase_3();

                try{
            testCase_3.runTest();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
