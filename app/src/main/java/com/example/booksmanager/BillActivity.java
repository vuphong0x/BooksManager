package com.example.booksmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.HoaDonDAO;
import com.example.booksmanager.Model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edtNgayMua, edtMaHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        setTitle("Thêm Hóa Đơn");
        edtNgayMua = findViewById(R.id.edtNgayMua);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDon);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        setDate(calendar);
    }

    private void setDate(final Calendar calendar) {
        edtNgayMua.setText(sdf.format(calendar.getTime()));
    }
    
    public static class DatePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }

    }
    
    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    public void addBill(View view) {
        hoaDonDAO = new HoaDonDAO(this);
        try {
            if (validation() < 0) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new HoaDon(edtMaHoaDon.getText().toString(), sdf.parse(edtNgayMua.getText().toString()));
                if (hoaDonDAO.insertBill(hoaDon) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HoaDonChiTietActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MAHOADON", edtMaHoaDon.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int validation() {
        if (edtMaHoaDon.getText().toString().isEmpty() || edtNgayMua.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}