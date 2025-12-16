// package: com.example.lab06
package com.example.bai2.quanlydatphong;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bai2.R;
import com.example.bai2.model.DatPhong;
import com.example.bai2.utils.AppDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddBookingActivity extends AppCompatActivity {

    EditText edtMa, edtNguoi, edtNgay, edtSoDem;
    Button btnLuu, btnHuy;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        db = AppDatabase.getAppDatabase(this);

        edtMa = findViewById(R.id.edtMa);
        edtNguoi = findViewById(R.id.edtNguoi);
        edtNgay = findViewById(R.id.edtNgay);
        edtSoDem = findViewById(R.id.edtSoDem);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        edtNgay.setText(today);

        btnLuu.setOnClickListener(v -> {
            String ma = edtMa.getText().toString().trim();
            String nguoi = edtNguoi.getText().toString().trim();
            String ngayStr = edtNgay.getText().toString().trim();
            String soDemStr = edtSoDem.getText().toString().trim();

            if (TextUtils.isEmpty(ma) || TextUtils.isEmpty(nguoi) || TextUtils.isEmpty(ngayStr) || TextUtils.isEmpty(soDemStr)) {
                Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            long millis;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                millis = sdf.parse(ngayStr).getTime();
            } catch (Exception e) {
                Toast.makeText(this, "Ngày không hợp lệ (yyyy-MM-dd)", Toast.LENGTH_SHORT).show();
                return;
            }

            int soDem;
            try {
                soDem = Integer.parseInt(soDemStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số đêm phải là số", Toast.LENGTH_SHORT).show();
                return;
            }

            DatPhong dp = new DatPhong(ma, nguoi, millis, soDem);
            long id = db.datPhongDao().insert(dp);
            if (id > 0) {
                Toast.makeText(this, "Đã thêm", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnHuy.setOnClickListener(v -> finish());
    }
}
