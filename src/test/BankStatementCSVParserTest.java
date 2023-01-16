package test;
import org.junit.Test;

import main.BankStatementCSVParser;
import main.BankStatementParser;
import main.BankTransaction;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser=new BankStatementCSVParser();
    @Test
    public void shouldParseOneCorrectLine() throws Exception{
        final String line="30-01-2022,-50,Tesco";
        final BankTransaction result=statementParser.parseFrom(line);
        final BankTransaction expected=new BankTransaction(LocalDate.of(2022, Month.JANUARY, 30),-50,"Tesco");

        final double tolerance =0.0d;
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(),result.getAmount(),tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }
    @Test
    public void shouldParseLines()throws Exception{
        final List<String>lines=new ArrayList<String>();
        String[] parts;
        for(int i=0;i<32;i++){
        
        lines.add("30-01-2022,-50,Tesco");
        
        parts=lines.get(i).split(",");
        String dateString=parts[0];
        int transaction=(int)Double.parseDouble(parts[1]);
        String company=parts[2];
        LocalDate date=LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            BankTransaction result=statementParser.parseFrom(lines.get(i));
            BankTransaction expected=new BankTransaction(LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth()),transaction,company);
            final double tolerance =0.0d;
            Assert.assertEquals(expected.getDate(), result.getDate());
            Assert.assertEquals(expected.getAmount(),result.getAmount(),tolerance);
            Assert.assertEquals(expected.getDescription(), result.getDescription());
        }
       
    
}
// @Test 
// public void failDescription() throws Exception{
        
//         final List<String>lines=new ArrayList<String>();
//         String[] parts;
//         for(int i=0;i<32;i++){
        
//         lines.add("30-01-2022,-50,iVzq5K9n2hRdJZW8mEHYwNr0jfvLp6tXu1xa7MgUybkQN3gscb8ljKwGz5oNxT6T1d2Q9a7H4u8cLp6vhhthhthzturthhrhthrth");
        
//         parts=lines.get(i).split(",");
//         String dateString=parts[0];
//         int transaction=(int)Double.parseDouble(parts[1]);
//         String company=parts[2];
//         LocalDate date=LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//             BankTransaction result=statementParser.parseFrom(lines.get(i));
//             BankTransaction expected=new BankTransaction(LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth()),transaction,company);
//             final double tolerance =0.0d;
//             Assert.assertEquals(expected.getDate(), result.getDate());
//             Assert.assertEquals(expected.getAmount(),result.getAmount(),tolerance);
//             Assert.assertEquals(expected.getDescription(), result.getDescription());
//         }
       
    
// }
    

}

