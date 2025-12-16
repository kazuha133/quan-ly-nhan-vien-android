package com.example.bai2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DatPhong_TBL")
public class DatPhong {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "maDat")
    private String maDat;

    @ColumnInfo(name = "nguoiDat")
    private String nguoiDat;

    @ColumnInfo(name = "ngayDatMillis")
    private long ngayDatMillis;

    @ColumnInfo(name = "soDem")
    private int soDem;

    public DatPhong() {}

    public DatPhong(String maDat, String nguoiDat, long ngayDatMillis, int soDem) {
        this.maDat = maDat;
        this.nguoiDat = nguoiDat;
        this.ngayDatMillis = ngayDatMillis;
        this.soDem = soDem;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaDat() { return maDat; }
    public void setMaDat(String maDat) { this.maDat = maDat; }

    public String getNguoiDat() { return nguoiDat; }
    public void setNguoiDat(String nguoiDat) { this.nguoiDat = nguoiDat; }

    public long getNgayDatMillis() { return ngayDatMillis; }
    public void setNgayDatMillis(long ngayDatMillis) { this.ngayDatMillis = ngayDatMillis; }

    public int getSoDem() { return soDem; }
    public void setSoDem(int soDem) { this.soDem = soDem; }

    @Override
    public String toString() {
        String ngay = android.text.format.DateFormat.format("dd/MM/yyyy", ngayDatMillis).toString();
        return "Mã đặt: " + maDat + "\n"
                + "Người đặt: " + nguoiDat + "\n"
                + "Ngày đặt: " + ngay + "\n"
                + "Số đêm: " + soDem;
    }
}
