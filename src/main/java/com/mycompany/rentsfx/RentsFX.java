/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.rentsfx;

import java.util.ArrayList;

import javafx.application.Application;

/**
 *
 * @author crise
 */
public class RentsFX{
    
    //inputs
        static double rents = 0;
        
        static int period = 99;
        static double startgrossincome = 3.8;
        static double rate = .07;
        static double baserent = 1.4;
        static double totalinflation = 1.0;
        static double inflation;
        static double K5Percent = .17;
    
    //outputs
        static double total= 0;
        
        static double pass = 0;
        static double grossincome = 3.8;
        
        static double multiplier;
        static double lowinf = 1.5;
        static double highinf = 4.5;
        static double rentfloor = 1.03;
        static double rentcap = 1.045;
        static double deccap = Math.pow(1.055,10);
        static ArrayList<Double>myList = new ArrayList();
        
    public static ArrayList rInflation(int per)
    {
        
        
        for(int i = 0; i <= per; i++)
        {
            
            double result;
            result  = highinf * Math.random();
            result = result + lowinf;
            
            result = result * 100;
            result = Math.rint(result);
            result = result / 100.0;
            myList.add(result);
            //System.out.println("Random Number is: "+myList.get(i));
        }
        return myList;
    }
    
    public static void nRents()
    {
        
    }
    
    public static void K5Improvements()
    {
        grossincome = startgrossincome;
        total = 0;
        pass = 0;
        double totalincome = 0;
        double totalextraincome = 0;
        double extraincome = 0;
        
        multiplier = 1.0;
        
        double invest = 0;
        double totalinvested = 0;
        
        System.out.println("\nBegin pass");
        for(int i = 0; i<4; i++)
        {
            
            
            invest = invest + .75;
            
            inflation = 1.0 + (myList.get(i)/ 100.0);
            invest = invest * inflation;
            totalinvested = (invest / Math.pow(1 + rate, i));
        
            System.out.println("Year "+(i+1)+" K5 total discounted investment is "+totalinvested);
        }
        
        total = 0;
        for(int i = 1; i <= period; i++)
        {
            
            inflation = 1.0 + (myList.get(i -1)/ 100.0);
            grossincome = grossincome * inflation;
            //System.out.println("income is "+inflation);
            if(i<=4)
            {
                multiplier = (1 + (i * .05));
                
            }
            
            else if(i >= 5 && i <=24)
            {
                multiplier = (1 + (.20 - (.01 * (i-4))));
            }
            
            else
                ;
            
            extraincome = ((grossincome * multiplier) - grossincome);
            totalextraincome = totalextraincome + extraincome;
            if(i == 1)
            {
                pass = extraincome;
                total = total + pass; 
            }
            else
            {
                pass = ( extraincome/ Math.pow(1 + rate, i));
                total = total + pass;
            }
            
            //Adjustment for printing inflation
            inflation = (inflation - 1.0) * 10000;
            inflation = Math.rint(inflation) /100;
            
            System.out.println("\nYear "+i);
            System.out.println("Inflation was "+inflation+"%");
            System.out.println("Gross income this year was "+grossincome);
            System.out.println("Rent multiplier is: "+multiplier);
            System.out.println("K5 extra income this year is "+extraincome);
            System.out.println("K5 discounted extra income this year is "+pass);
            System.out.println("K5 discounted running total this year is "+total);
            
        }
        System.out.println("Fair present Value K5improv: "+total+", total extra income is "+totalextraincome);
    }
    
    public static void K5Rents()
    {
        grossincome = startgrossincome;
        total = 0;
        pass = 0;
        multiplier = 1.0;
        for(int i=1; i<= period; i++)
        {
            
            
            inflation = 1.0 + (myList.get(i -1)/ 100.0);
            //System.out.println(inflation);
            
            grossincome = grossincome * inflation;
            multiplier = multiplier * inflation;
            //System.out.println("multi is "+multiplier);
            rents = baserent + (multiplier * .2);
            //System.out.println(rents);
            pass = (rents / Math.pow(1 + rate, i));
            total = total + pass;
            //System.out.println("Year "+i+" rents are "+rents+ " inflation is : "+ inflation );
            //System.out.println("Year "+i+" value is: "+ pass+" gross is "+grossincome);
            
        }
        System.out.println("Fair present Value K5: "+total+" final rents are: "+rents+", grossincome is "+grossincome);
    }
    
    public static void J1Rents()
    {
        grossincome = startgrossincome;
        total = 0;
        pass = 0;
        int lowinf = 0;
        totalinflation = 1;
        
        rents = baserent;
        
        for(int j = 1; j <= period; j++)
        {
            
            
            inflation = 1.0 + (myList.get(j -1)/ 100.0);
            totalinflation = totalinflation * inflation;
            grossincome = grossincome * inflation;
            //System.out.println("Inflaion is: "+ totalinflation);
            rents = rents * 1.025;
            
            if(j % 10 == 0)
            {
                double exponant = Math.pow(1.025, 10);
                //System.out.println("exponant is "+exponant);
                //System.out.println("Inflation year!  Rents were :"+ rents+" inflation for the decade was "+totalinflation);
                if(totalinflation - exponant > 0)
                {
                    rents = rents *(totalinflation / exponant);
                }
                
                else
                {
                    System.out.println("Inflation less than adjustments for "+(++lowinf)+" times");
                }
                //System.out.println("Year "+j+" rents were adjusted to "+rents+" inflation ran "+totalinflation);
            
                //Reset inflation and exponant
                totalinflation = 1;
                exponant = 1;
            }
            
            pass = (rents / Math.pow(1 + rate, j));
            total = total + pass;
            //System.out.println("Year "+j+" rents are "+rents);
            //System.out.println("Year "+j+" value is: "+ pass+" gross is "+grossincome);
        }
        
         System.out.println("Fair present Value J1: "+total+" final rents are: "+rents+", gross income is "+grossincome);
    }
    
    public static void J2Rents()
    {
        grossincome = startgrossincome;
        total = 0;
        pass = 0;
        
        totalinflation = 1;
        double decadeinfadj = 1;
        int lowinf = 0;
        double lastdecrents = baserent;
        rents = baserent;
        for(int j = 1; j <= period; j++)
        {
            
            
            inflation = 1.0 + (myList.get(j - 1)/ 100.0);
            totalinflation = totalinflation * inflation;
            grossincome = grossincome * inflation;
            //System.out.println("Rents are "+rents);
            if(j % 10 != 0) 
            {
                if(inflation < rentfloor)
                    inflation  = rentfloor;
                else if(inflation > rentcap)
                    inflation = rentcap;
            
                decadeinfadj = decadeinfadj * inflation;
                rents = rents * inflation;
                //System.out.println("total inflation is: "+totalinflation+" and decade adjustment so far is: "+decadeinfadj);
            }
            
            
            else if(j % 10 == 0)
            {
                
            inflation = 1.0 + (myList.get(j - 1)/ 100.0);
            totalinflation = totalinflation * inflation;
            grossincome = grossincome * inflation;
                System.out.println("Decade adjustment year, current rents are "+decadeinfadj / totalinflation+" percent of inflation");
                
                
                if(inflation < rentfloor)
                        inflation  = rentfloor;
                    else if(inflation > rentcap)
                        inflation = rentcap;
            
                decadeinfadj = decadeinfadj * inflation;
                System.out.println("deccap is "+deccap+" total inflation is "+totalinflation+" decade adjustment was "+decadeinfadj);
                if(decadeinfadj >= totalinflation)
                {
                    System.out.println("Decade adjustment exceeded inflation, so no adjustnemt.");
                    rents = rents * inflation;
                    System.out.println("Decade inflation adjustment is :"+decadeinfadj);
                    
                }
                
                    
                    
                else if(totalinflation <= deccap)
                {
                    
                   System.out.println("Inflation is higher then annual rent adjust, the ratio is "+(totalinflation / decadeinfadj));
                    rents = rents * (totalinflation / decadeinfadj);
                    
                    
                }
                else if (totalinflation > deccap)
                {
                    System.out.println("Inflation exceeded decade adjustment decade adjustment was only "+(deccap / totalinflation)+" percent!");
                    
                    //rents = lastdecrents * deccap;
                    //lastdecrents = rents;
                    rents = rents *(deccap / (decadeinfadj/inflation));
            
                    //System.out.println("Decade Inflation exceeded Decade cap, so yearly cap/floor of "+inflation+" applied.");
                    
                    //System.out.println("total inflation is: "+totalinflation+" and decade adjustment is: "+decadeinfadj);
                }
                //System.out.println("Year "+j+" rents were adjusted to "+rents+" inflation ran "+inflation);
                System.out.println("Rents were adjusted up to "+rents);
                
                //Reset inflation
                totalinflation = 1;
                decadeinfadj = 1;
            }
            
            pass = (rents / Math.pow(1 + rate, j));
            total = total + pass;
            System.out.println("Year "+j+" rents were adjusted to "+rents+" inflation ran "+inflation);
            System.out.println("Discounted rents are "+pass+" running discounted total is: "+total);
        }
        System.out.println("Fair present Value J2: "+total+" final rents are: "+rents+" , grossincome is: "+grossincome);
    }
    
    public static void K5Profit()
    {
        grossincome = startgrossincome;
        total = 0;
        pass = 0;
        double K5Profit = 0.0;
        rate = rate + .01;
        System.out.println("k5 discount is "+1+rate+" 1% higher than landlord.");

        System.out.println("\nStart pass:");
        for(int j = 1; j <=period; j++)
        {
            
            
            inflation = 1.0 + (myList.get(j -1)/ 100.0);
            //System.out.println(inflation);
            
            grossincome = grossincome * inflation;
            K5Profit = grossincome * K5Percent;
            if(j == 1)
            {
                pass = K5Profit;
                total = total + pass;
            }
            else
            {
                pass = (K5Profit / Math.pow(1 + rate, j));
                total = total + pass;
            }
            
            //Adjustment for printing inflation
            inflation = (inflation - 1.0) * 10000;
            inflation = Math.rint(inflation) /100;
            
            System.out.println("\nYear "+j);
            System.out.println("Inflation was "+inflation+"%");
            System.out.println("Gross income this year was "+grossincome);
            System.out.println("K5 take is "+K5Profit);
            System.out.println("Discounted it's "+pass+" running discounted total is: "+total);
            
        }
        //System.out.println("\nK5's total discounted take is "+total);
    }
    public static void main(String[] args) {
        //RentsGUI.launch(RentsGUI.class, args);
        
        
        
        
        int r = (int)(rate * 100);
        
        System.out.println("This pass is over "+period+" years");
        System.out.println("This pass is at a discount rate of "+r+"%");
        System.out.println("The range of inflation for this pass is: Low of "+lowinf+" high of "+(highinf+lowinf)+" average of "+(highinf + lowinf + lowinf) / 2);
        System.out.println("Apartment gross income is "+grossincome+" mil");
        System.out.println("K5 percent of gross is "+(K5Percent * 100)+"%");
        rInflation(period);
        //K5Rents();
        //J1Rents();
        J2Rents();
        //K5Improvements();
        K5Profit();
        
        //System.out.println("Fair present Value K5: "+total+" final rents are: "+rents);
        //System.out.println("K5 Value is: "+ total +" gross is "+grossincome);
        
        
        

        
    }
}
