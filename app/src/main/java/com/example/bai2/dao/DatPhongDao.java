package com.example.bai2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bai2.model.DatPhong;
import java.util.List;

@Dao
public interface DatPhongDao {
    @Query("SELECT * FROM DatPhong_TBL ORDER BY ngayDatMillis DESC")
    List<DatPhong> getAll();

    @Insert
    long insert(DatPhong datPhong);

    @Update
    int update(DatPhong datPhong);

    @Delete
    int delete(DatPhong datPhong);

    @Query("DELETE FROM DatPhong_TBL WHERE id = :id")
    int deleteById(int id);
}
