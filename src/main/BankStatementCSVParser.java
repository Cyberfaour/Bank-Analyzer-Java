package main;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{
    String[] columns;
    LocalDate date;
    double amount;
    String description;
    private static final DateTimeFormatter DATE_PATTERN=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public BankTransaction parseFrom(final String line){
        columns=line.split(",");
        date=LocalDate.parse(columns[0],DATE_PATTERN);
        amount=Double.parseDouble(columns[1]);
        description=columns[2];
        validate(date, amount, description);
        return new BankTransaction(date,amount,description);
    }

    public List<BankTransaction>parseLinesFrom(final List<String>lines){
        final List<BankTransaction> bankTransactions=new ArrayList<>();
        for(final String line:lines){
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }

    public Notification validate(LocalDate date,double amount ,String desc ){
        final String description=desc;
        final double parsedAmount;
        final LocalDate parsedDate;
        final Notification notification =new Notification();
        if(description.length()>100){
            notification.addError("The description is too long");
        }
         
        try{
            parsedDate=date;
            if(parsedDate.isAfter(LocalDate.now())){
                notification.addError("Date cannot be in the future");
            }
        }catch(DateTimeParseException e){
                notification.addError("Invalid format for date");
        }
        
        try{
            parsedAmount=amount;
            notification.noError();
        }catch(NumberFormatException e){
            notification.addError("Invalid format for amount");
        }
        return notification;
    }
    
   

   
    }

