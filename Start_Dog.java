

/**
 * Created by marklewchenko on 22/06/2017.
 */
package com.company;

// GUB V0.1 June 22 2017
// This is a simulator of a Dog. The intention is to use the simulator to model a diabetic dog throughout a given day (mimicking my real dog Gub)
// Mark Lewchenko

class Dog {

    String DName;
    int Age;
    String Colour;
    Boolean Diabetic;
    int number_of_readings;
    float max_insulin;


    /* expected blood sugar values for every half hour - low value & high value range*/
    float[][] exp_blood_sugar = new float[2][48];




    /* Actual value - updated when he is measured*/
    float blood_sugar_now;


    /* below are 2 arrays to capture the blood sugar readings through a given day */
    float[] blood_sugar_hist = new float[20];
    int[] blood_sugar_time = new int[20];


    int Needs_Wee;     // value between 1 [does not] and 10 [does]
    float Dog_Time;    // an event based measure 0 to 24 of the state the Dog is at on a given day.

    float last_insulin_given; // last insulin value given.
    int last_insulin_given_time; // time of last injection.



    public void Initialise_BG_Curve() {

        last_insulin_given=0; // will be reset when insulin is given
        max_insulin = 10;



        /* 0, = Low Value 1,=High Value */

        /* Time 00:00 */
        exp_blood_sugar[0][0]=12;
        exp_blood_sugar[1][0]=16;
        /* Time 01:00 */
        exp_blood_sugar[0][1]=12;
        exp_blood_sugar[1][1]=16;
        /* Time 02:00 */
        exp_blood_sugar[0][2]=12;
        exp_blood_sugar[1][2]=16;
        /* Time 03:00 */
        exp_blood_sugar[0][3]=12;
        exp_blood_sugar[1][3]=16;
        /* Time 04:00 */
        exp_blood_sugar[0][4]=13;
        exp_blood_sugar[1][4]=20;
        /* Time 05:00 */
        exp_blood_sugar[0][5]=13;
        exp_blood_sugar[1][5]=21;
        /* Time 06:00 */
        exp_blood_sugar[0][6]=10;
        exp_blood_sugar[1][6]=22;
        /* Time 07:00 */
        exp_blood_sugar[0][7]=13;
        exp_blood_sugar[1][7]=23;
        /* Time 08:00 */
        exp_blood_sugar[0][8]=18;
        exp_blood_sugar[1][8]=26;
        /* Time 09:00  ----------------------- assumes Fed by 8am */
        exp_blood_sugar[0][9]=20;
        exp_blood_sugar[1][9]=28;
        /* Time 10:00 */
        exp_blood_sugar[0][10]=15;
        exp_blood_sugar[1][10]=23;
        /* Time 11:00 */
        exp_blood_sugar[0][11]=10;
        exp_blood_sugar[1][11]=18;
        /* Time 12:00 -------------------------assumes no morning snack */
        exp_blood_sugar[0][12]=7;
        exp_blood_sugar[1][12]=14;
        /* Time 13:00 */
        exp_blood_sugar[0][13]=6;
        exp_blood_sugar[1][13]=13;
        /* Time 14:00 */
        exp_blood_sugar[0][14]=5;
        exp_blood_sugar[1][14]=12;
        /* Time 15:00 */
        exp_blood_sugar[0][15]=10;
        exp_blood_sugar[1][15]=15;
        /* Time 16:00 */
        exp_blood_sugar[0][16]=15;
        exp_blood_sugar[1][16]=20;
        /* Time 17:00 */
        exp_blood_sugar[0][17]=16;
        exp_blood_sugar[1][17]=22;
        /* Time 18:00 -------------------------assumes fed by 6.30 */
        exp_blood_sugar[0][18]=16;
        exp_blood_sugar[1][18]=25;
        /* Time 19:00 */
        exp_blood_sugar[0][19]=18;
        exp_blood_sugar[1][19]=29;
        /* Time 20:00 */
        exp_blood_sugar[0][20]=16;
        exp_blood_sugar[1][20]=24;
        /* Time 21:00 */
        exp_blood_sugar[0][21]=10;
        exp_blood_sugar[1][21]=17;
        /* Time 22:00 ------------------------assumes fed biscuits by 9.30 */
        exp_blood_sugar[0][22]=7;
        exp_blood_sugar[1][22]=13;
        /* Time 23:00 */
        exp_blood_sugar[0][23]=10;
        exp_blood_sugar[1][23]=15;



    }





    public void Inject_Insulin(float ui, int hr) {

        last_insulin_given=ui;
        last_insulin_given_time=hr;

        System.out.println(DName + " had an insulin injection of " + ui + "ui at " + hr);

        if (last_insulin_given>max_insulin) {


            System.out.println("*** Warning - Insulin dose of " + ui + "ui exceeds max dose of " + max_insulin + "ui ***");
            System.out.println("*** Take RECOVERY ACTION NOW ***");

        }


    }

    public void Feed() {






    }

    public void Give_Water() {

    }

    public void Take_for_Wee(int hr) {

        System.out.println(DName + " is going for a wee at " + hr + "am");
        Needs_Wee=0;
        Dog_Time=hr;

    }


    public void Take_for_Walk() {


    }

    public void Dog_Sleeps () {

    }

    public void Wake_Up (float hr) {

        System.out.println(DName + " is waking up! Time is " + hr + "am");

        Needs_Wee=10;
        Dog_Time=hr;

        System.out.println(DName + " needs a wee, feeding, measuring, then insulin.");


    }

public void Status (String codes) {


        /* Display a list of key attributes of the dog and alert any issues */

        if (codes!="Alert Only") {
            System.out.println(DName);
            System.out.println("");
            System.out.println("Diabetic=" + Diabetic);
            System.out.println("Age=" + Age);
            System.out.println("Colour=" + Colour);
            System.out.println("");
            System.out.println("Current Blood Sugar Level=" + blood_sugar_now);
            System.out.println("");
                   }


        /* Do ALERTS - Check if Blood sugar is in range

        if (blood_sugar_now>=blood_sugar_low & blood_sugar_now<=blood_sugar_high) {
            System.out.println("");
            System.out.println("Blood Sugar is in expected range.");
        } else {
            if (blood_sugar_now<blood_sugar_low){

                    System.out.println("");
                    System.out.println("*** ALERT -> Blood Sugar is below expected range ***.");

                }
            if (blood_sugar_now>blood_sugar_high){

                        System.out.println("");
                        System.out.println("*** ALERT -> Blood Sugar is above expected range ***.");

                    }
        }
        */

    }


public void Check_Alerts() {

        int alert=0;

        /* Check current blood sugar reading against the curve */

            if (blood_sugar_now<exp_blood_sugar[0][blood_sugar_time[number_of_readings]]){
                System.out.println("");
                System.out.println("*** ALERT -> Blood Sugar Reading : " + blood_sugar_now + " taken at " + blood_sugar_time[number_of_readings] + " is BELOW expected range point of " + exp_blood_sugar[0][blood_sugar_time[number_of_readings]] +  " to " + exp_blood_sugar[1][blood_sugar_time[number_of_readings]] + " ***.");
                alert=1;
            } else {

                if (blood_sugar_now > exp_blood_sugar[1][blood_sugar_time[number_of_readings]]) {
                    System.out.println("");
                    System.out.println("*** ALERT -> Blood Sugar Reading : " + blood_sugar_now + " taken at " + blood_sugar_time[number_of_readings] + " is HIGHER than expected range point of " + exp_blood_sugar[0][blood_sugar_time[number_of_readings]] + " to " + exp_blood_sugar[1][blood_sugar_time[number_of_readings]] + " ***.");
                    alert=1;
                }

            }
        if (alert==0) {
            System.out.println("");
            System.out.println("Last Blood Sugar Reading : " + blood_sugar_now + " taken at " + blood_sugar_time[number_of_readings] + " is in the range of " + exp_blood_sugar[0][blood_sugar_time[number_of_readings]] + " to " + exp_blood_sugar[1][blood_sugar_time[number_of_readings]]);
        }

        if (Needs_Wee>8) {
            System.out.println("*** " + DName + " definitely needs to go for a wee ***");
        } else {


            if (Needs_Wee<8 & Needs_Wee>3) {
                System.out.println("*** " + DName + " probably needs to go for a wee ***");
            }


        }



        alert=0;

}




public void Measure(float reading, int hr) {

        /*take a blood sugar reading*/
        number_of_readings=number_of_readings+1;

        blood_sugar_hist[number_of_readings]=reading;
        blood_sugar_time[number_of_readings]=hr;
        System.out.println("");
        System.out.println("Blood Sugar Reading of "+ reading + " taken at "+hr);
        blood_sugar_now=reading;
        Dog_Time=hr;

}

} /* DOG Class */



public class Start_Dog {
    public static void main(String[] args) {

        System.out.println("About to Initialise Dog");

        Dog Gub = new Dog(); /* create a new Dog Object Instance using Blueprint DOG Class*/


        /* Describe Dog */

        Gub.DName="Gub";
        Gub.Age=13;
        Gub.Diabetic = true;
        Gub.Colour="Brown";
        Gub.number_of_readings=0;
        Gub.Initialise_BG_Curve();




        Gub.Wake_Up(7);
        /** take for a wee, feed, measure, insulin, sleep
         *
         */
        Gub.Take_for_Wee(7);

        Gub.Measure(29,7);

        Gub.Check_Alerts();

        Gub.Inject_Insulin(12,7);




        Gub.Status("Alert Only");
        /*Gub.Status("Full");*/







    }

}
