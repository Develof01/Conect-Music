package com.example.domian.entities

import androidx.room.*
import java.io.Serializable

@Entity(
    tableName = "UserTracks",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("ID"),
        childColumns = arrayOf("ID_USER"),
        onDelete = ForeignKey.CASCADE
    )]
)
class TrackInformation(
    @Ignore
    val artistId: Int,
    @Ignore
    val artistViewUrl: String,
    @Ignore
    val artworkUrl30: String,
    @Ignore
    val artworkUrl60: String,
    @Ignore
    val collectionArtistName: String,
    @Ignore
    val collectionCensoredName: String,
    @Ignore
    val collectionExplicitness: String,
    @Ignore
    val collectionId: Int,
    @Ignore
    val collectionPrice: Double,
    @Ignore
    val collectionViewUrl: String,
    @Ignore
    val country: String,
    @Ignore
    val currency: String,
    @Ignore
    val discCount: Int,
    @Ignore
    val discNumber: Int,
    @Ignore
    val isStreamable: Boolean,
    @Ignore
    val kind: String,
    @Ignore
    val primaryGenreName: String,
    @Ignore
    val releaseDate: String,
    @Ignore
    val trackCensoredName: String,
    @Ignore
    val trackCount: Int,
    @Ignore
    val trackExplicitness: String,
    @Ignore
    val trackNumber: Int,
    @Ignore
    val trackPrice: Double,
    @Ignore
    val trackTimeMillis: Int,
    @Ignore
    val trackViewUrl: String,
    @Ignore
    val wrapperType: String
) : Serializable {

    constructor() : this(0, "", "", "", "", "", "",
        0, 0.0, "",
        "", "", 0,0, false, "","", "",
        "",0, "", 0, 0.0, 0, "","")

    companion object {
        const val ID_ = "ID"
        const val ID_USER = "ID_USER"
        const val ID_TRACK = "ID_TRACK"
        const val ARTIST_NAME = "ARTIST_NAME"
        const val ART_WORK_URL = "ART_WORK_URL"
        const val COLLECTION_NAME = "COLLECTION_NAME"
        const val PREVIEW_URL = "PREVIEW_URL"
        const val TRACK_NAME = "TRACK_NAME"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_)
    var id: Int = 0

    @ColumnInfo(name = ID_USER)
    var userId: Int = 0

    @ColumnInfo(name = ID_TRACK)
    var trackId: Int = 0

    @ColumnInfo(name = ARTIST_NAME)
    var artistName: String = ""

    @ColumnInfo(name = ART_WORK_URL)
    var artworkUrl100: String? = ""

    @ColumnInfo(name = COLLECTION_NAME)
    var collectionName: String? = ""

    @ColumnInfo(name = PREVIEW_URL)
    var previewUrl: String? = ""

    @ColumnInfo(name = TRACK_NAME)
    var trackName: String? = ""



}