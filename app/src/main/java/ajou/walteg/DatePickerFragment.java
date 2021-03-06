package ajou.walteg;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Ridwan on 7/14/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Activity a = getActivity();
        if (a instanceof PurchaseActivity) {
            PurchaseActivity pa = (PurchaseActivity) a;
            pa.setDate(year, month, day);
        }
        if (a instanceof AddPurchaseActivity) {
            AddPurchaseActivity apa = (AddPurchaseActivity) a;
            apa.setDate(day + "/" + month + "/" + year);
        }
    }
}