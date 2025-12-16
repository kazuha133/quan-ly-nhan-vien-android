package com.example.bai2.quanlydatphong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bai2.R;
import com.example.bai2.model.DatPhong;
import com.example.bai2.utils.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    ListView lvDatPhong;
    FloatingActionButton fabAdd;
    ArrayList<DatPhong> ds;
    ArrayAdapter<DatPhong> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        lvDatPhong = findViewById(R.id.lvDatPhong);
        fabAdd = findViewById(R.id.fabAdd);

        ds = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
        lvDatPhong.setAdapter(adapter);

        loadData();

        // long click để xóa
        lvDatPhong.setOnItemLongClickListener((parent, view, position, id) -> {
            DatPhong item = ds.get(position);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có muốn xóa đặt phòng: " + item.getMaDat() + " ?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        int affected = db.datPhongDao().deleteById(item.getId());
                        if (affected > 0) {
                            ds.remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
            return true;
        });

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        ds.clear();
        List<DatPhong> list = db.datPhongDao().getAll();
        ds.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
