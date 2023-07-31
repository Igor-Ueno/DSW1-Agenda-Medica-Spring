package br.ufscar.dc.dsw.conversor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTimeConverter implements Converter<String, Time> {

    // private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    // private final SimpleDateFormat format = new SimpleDateFormat("hh:mm");
    private final DateFormat formatter = new SimpleDateFormat("hh:mm");

    @Override
    public Time convert(String source) {
        try {
            // String s = String.valueOf(source);
            // long ms = format.parse(s).getTime();
            // return Time.parse(source);
            // return Time.valueOf(source+":00");
            // DateFormat formatter = new SimpleDateFormat("hh:mm");
            // Date fajr_begins = (Date)formatter.parse(source);

            // Time timeValue = new Time(formatter.parse(source).getTime());
            // return timeValue;
            return new Time(formatter.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
