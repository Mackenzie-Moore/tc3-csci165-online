public class Tickets{

    public static void main(String[] args){

        /* 
            The following code steps you through the String processing
            and math neccessary to determine if a single ticket number is valid.
            Your job with this code is to:

            ~   Closely study the code. Reverse engineer it. Use the API for guidance
                to understand the classes and methods that are being used.
            ~   Add comments for each statement, describing in detail what the line is doing
                I want to know DETAILS, not generalizations. RESEARCH!
            ~   When you have completed that, add code to complete the following

                1) Using a Scanner, open the file: tickets.txt
                2) Using a while(hasNext) loop, read each ticket number
                3) Run the ticket number through the provided math and String processing
                4) If the ticket number is valid write it to a new file called: valid_tix.txt
                5) Ignore the invalid ticket numbers

            ~   Additional code must also be commented. But I am more interested in why you are doing
                something, not the details of how.

            Check your work: There are 101 valid ticket numbers among the 1000 provided ticket numbers
                             These people make mistakes!!!

            Submit only Java source code files. Also submit valid_tix.txt   

        */

        String  ticket  = "123454";                            
        String  last = ticket.substring(ticket.length() - 1);                                                   
        int     last_digit = Integer.valueOf(last);
        String  reduced_ticket = ticket.substring(0, ticket.length() - 1);
        int     ticket_number = Integer.valueOf(reduced_ticket);
        int     remainder = ticket_number % 7; 
        boolean validity = remainder == last_digit; 
        String  format = "Original Ticket #: %s\nLast Digit: %d\nReduced Ticket #: %d\nRemainder: %d\nValidity: %b\n";
        System.out.printf(format, ticket, last_digit, ticket_number, remainder, validity);  
    }
}