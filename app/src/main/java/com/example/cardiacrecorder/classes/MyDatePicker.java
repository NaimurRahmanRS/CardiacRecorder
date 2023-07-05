package com.example.cardiacrecorder.classes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cardiacrecorder.others.StringListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private final StringListener listener;

    /**
     * date picker setter
     * @param listener
     */
    public MyDatePicker(StringListener listener){
        this.listener = listener;
    }

    /**
     * on create dialog creator
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LocalDate localDate = LocalDate.now();

        int year = localDate.getYear();
        int month = localDate.getMonthValue()-1;
        int day = localDate.getDayOfMonth();

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    /**
     * date setter
     * @param datePicker the picker associated with the dialog
     * @param year the selected year
     * @param month the selected month (0-11 for compatibility with
     *              {@link Calendar#MONTH})
     * @param day the selected day of the month (1-31, depending on
     *                   month)
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        LocalDate localDate = LocalDate.of(year,month+1,day);
//        month++;
//        String d = String.valueOf(day);
//        if(d.length() < 2) d = "0"+d;
//
//        String m = String.valueOf(month);
//        if(m.length() < 2) m = "0"+m;
//
//        String date = d+"/"+m+"/"+year;

        String pattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        listener.onStringSelected(formatter.format(localDate));

    }

}
