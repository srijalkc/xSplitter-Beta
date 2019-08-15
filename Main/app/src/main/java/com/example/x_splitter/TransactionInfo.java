package com.example.x_splitter;

public class TransactionInfo {
    String group;
    String event;
    String paidBy;
    double amountToPay;
    double amountToGet;
    double amountInvested;


    String amountInvestedTemp;
//List<String> groupMembers;
    //int equallySplittedAmount;


    //String ItemPaidBy;
    //int membersize;





//    public TransactionInfo( int equallySplittedAmount, String amountToPay, String amountToGet, String amountInvested) {
//        this.amountInvested = amountInvested;
//        amountInvestedTemp = amountInvested;
//        this.amountToGet = Integer.toString(equallySplittedAmount);

//        int difference = ((Integer.parseInt(amountInvested)) - equallySplittedAmount);
//        if(difference>=0)
//            {
//                this.amountToGet =Integer.toString(difference);

//                if(Integer.parseInt(amountToPay) != 0){
//                    if (Integer.parseInt(amountToGet)<Integer.parseInt(amountToPay)){
//                        this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-(Integer.parseInt(amountToGet)));
//                        this.amountToGet=Integer.toString(0);
//                    }
//                    else {
//                        this.amountToGet=Integer.toString((Integer.parseInt(amountToGet))-(Integer.parseInt(amountToPay)));
//                        this.amountToPay=Integer.toString(0);
//                    }
//                }
//            }else {
//            this.amountToPay=Integer.toString((Integer.parseInt(amountToPay))-difference);
//            this.amountToGet=Integer.toString(0);

//        }
        //    }

//    public TransactionInfo( int equallySplittedAmount, String itemPaidBy, String amountToPay, String amountToGet, String amountInvested) {
//        this.amountToPay = Integer.toString(equallySplittedAmount);
//    }

    public TransactionInfo(double amountToPay, double amountToGet, double amountInvested) {
        this.amountToPay = amountToPay;
        this.amountToGet = amountToGet;
        this.amountInvested = amountInvested;
    }
//

//
//    public TransactionInfo(String paidBy, String amount){
//        this.paidBy = paidBy;
//        this.amount = amount;
//    }

    public TransactionInfo() {
    }

//    public TransactionInfo( String amountInvested){
//        this.amountInvested = amountInvested + amountInvestedTemp;
//
//
//    }


}

