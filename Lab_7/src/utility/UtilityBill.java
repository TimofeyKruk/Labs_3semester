package utility;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class UtilityBill implements Serializable  {
    //Fields
    private int homeNumber;
    private int flatNumber;
    private String address;
    private String name;
    private Date paymentDate;
    private double paymentAmount;
    private double finePercent;
    private int expirationDays;


    //  Methods
    public UtilityBill() {
    }

    public UtilityBill(int homeNumber, int flatNumber, String address, String name, Date paymentDate, double paymentAmount, double finePercent, int expirationDays) {
        this.homeNumber = homeNumber;
        this.flatNumber = flatNumber;
        this.address = address;
        this.name = name;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.finePercent = finePercent;
        this.expirationDays = expirationDays;
    }
    public UtilityBill(String s) throws ParseException
    {
        StringTokenizer str=new StringTokenizer(s," ,");
        if(str.hasMoreTokens())
        {
            this.homeNumber=Integer.parseInt(str.nextToken());
            this.flatNumber=Integer.parseInt(str.nextToken());
            this.address=str.nextToken();
            this.name=str.nextToken();
            SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            this.paymentDate=sdf.parse(str.nextToken());
            this.paymentAmount=Double.parseDouble(str.nextToken());
            this.finePercent=Double.parseDouble(str.nextToken());
            this.expirationDays=Integer.parseInt(str.nextToken());
        }
    }
    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getFinePercent() {
        return finePercent;
    }

    public void setFinePercent(double finePercent) {
        this.finePercent = finePercent;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
    }

    @Override
    public String toString() {
        return "UtilityBill{" +
                "homeNumber=" + homeNumber +
                ", flatNumber=" + flatNumber +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", finePercent=" + finePercent +
                ", expirationDays=" + expirationDays +
                '}';
    }
}

