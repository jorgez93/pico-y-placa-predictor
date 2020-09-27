/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pico.y.placa.predictor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JORGE
 */
public class Validator {
   public static char getLastDigit(String plateNumber) {
        return plateNumber.charAt(plateNumber.length() - 1);
    }
    
    public static Date getDate(String inputDate) throws ParseException {  
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);  
        return date;
    }
    
    public static int getDayOfTheWeek(Date day) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(day);
	return calendar.get(Calendar.DAY_OF_WEEK);		
    }
    
    public static boolean validateInputs(String plateNumber, String date, String time) {
        if (plateNumber.length() == 8 && date.length() == 10 && time.length() == 5) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean validateDay(int day) {
        if (day == 1 || day == 7) {
            return false;
        } else {
            return true;
        }
    }
    
    public Date formatHour(String hour) throws ParseException {
        Date time = new SimpleDateFormat("hh:mm").parse(hour);
        return time;
    }
    
    public boolean validateHours(Date time) throws ParseException {
        String startHourMorning = "07:00";
        Date time1 = new SimpleDateFormat("HH:mm").parse(startHourMorning);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);
        calendar1.add(Calendar.DATE, 1);
        
        String finishHourMorning = "09:30";
        Date time2 = new SimpleDateFormat("HH:mm").parse(finishHourMorning);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        calendar2.add(Calendar.DATE, 1);
        
        String startHourAfternoon = "16:00";
        Date time3 = new SimpleDateFormat("HH:mm").parse(startHourAfternoon);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(time3);
        calendar3.add(Calendar.DATE, 1);
        
        String finishHourAfternoon = "19:30";
        Date time4 = new SimpleDateFormat("HH:mm").parse(finishHourAfternoon);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(time4);
        calendar4.add(Calendar.DATE, 1);
        
        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTime(time);
        calendar5.add(Calendar.DATE, 1);
        
        Date date = calendar5.getTime();
        if(date.after(calendar1.getTime()) && date.before(calendar2.getTime())) {
            return false;
        } else if(date.after(calendar3.getTime()) && date.before(calendar4.getTime())) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean validateLastDigit(char lastDigit, int day) {
        if (((lastDigit == '1' || lastDigit == '2') && day == 2) || ((lastDigit == '3' || lastDigit == '4') && day == 3)
                || ((lastDigit == '5' || lastDigit == '6') && day == 4) || 
                ((lastDigit == '7' || lastDigit == '8') && day == 5) ||
                ((lastDigit == '9' || lastDigit == '0') && day == 6)) {
            System.out.println(day);
            return false;
        } else {
            return true;
        }
    }
public void validate(String plateNumber, String date, String hour){
        
        boolean validate = validateInputs(plateNumber, date, hour);
        if (!validate) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            char lastDigit = getLastDigit(plateNumber);
            try {
                Date parseDate = getDate(date);
                int day = getDayOfTheWeek(parseDate);
                if (validateDay(day)) {
                    if (!validateLastDigit(lastDigit, day)) {
                        Date validHour = formatHour(hour);
                        if (!validateHours(validHour)) {
                            JOptionPane.showMessageDialog(null, "You can't drive your car", "Prohibition", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "You can drive at this hour", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You can drive", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You can drive", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ParseException ex) {
               
            }
}
}
    
}
