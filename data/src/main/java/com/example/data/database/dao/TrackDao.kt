package com.example.data.database.dao

import androidx.room.*
import com.example.domian.entities.TrackInformation

@Dao
interface TrackDao {

    @Query("SELECT * FROM UserTracks WHERE ID_USER = :userId AND ID_TRACK = :trackId  limit 1")
    fun userHaveTrack(userId: Int?, trackId: Int?): TrackInformation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserTrack(track: TrackInformation): Long

    @Query("SELECT * FROM UserTracks WHERE ID_USER = :userId ")
    fun getUserTracks(userId: Int?): List<TrackInformation>?

    @Delete
    fun delete(userTrack: TrackInformation?)


}