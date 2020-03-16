package com.mia.loadmusic.main.model;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import com.mia.loadmusic.model.Music;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class MusicModel  {

    public  Single<List<Music>> getAllVideoMedia(Context context) {
        return Single.create(emitter -> {
            List<Music> songInfo = prepareSongData(context);
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onSuccess(songInfo);
        });
    }

    private List<Music> prepareSongData(Context context) {
        List<Music> musicList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == -1) {
            Toast.makeText(context, "Permission read_external_storage disable!", 0).show();
            return musicList;
        } else {
            Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "is_music", "title", "artist", "album", "album_id", "_data", "date_added", "_display_name", "_size", "duration", MediaStore.Audio.AudioColumns.SIZE}, (String)null, (String[])null, "title_key");
            if (cursor == null) {
                return musicList;
            } else {
                while(cursor.moveToNext()) {
                    int isMusic = cursor.getInt(cursor.getColumnIndex("is_music"));
                    if (isMusic != 0) {
                        String id = cursor.getString(cursor.getColumnIndex("_id"));
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String artist = cursor.getString(cursor.getColumnIndex("artist"));
                        String album = cursor.getString(cursor.getColumnIndex("album"));
                        long albumId = cursor.getLong(cursor.getColumnIndex("album_id"));
                        long duration = cursor.getLong(cursor.getColumnIndex("duration"));
                        String path = cursor.getString(cursor.getColumnIndex("_data"));
                        String size = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE));
                        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                        Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);
                        String uri = albumArtUri == null ? "" : albumArtUri.toString();

                        Music music = new Music();
                        music.setId(id);
                        music.setArtist(artist);
                        music.setNameAlbum(album);
                        music.setImage(uri);
                        music.setDuration(duration);
                        music.setFilePath(path);
                        music.setName(title);
                        music.setSize(size);
                        musicList.add(music);
                    }
                }

                cursor.close();
                return musicList;
            }
        }
    }
}
