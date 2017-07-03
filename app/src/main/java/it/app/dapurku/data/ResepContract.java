package it.app.dapurku.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Islam on 10/05/2016.
 */
public class ResepContract {

    public static final String CONTENT_AUTHORITY = "it.app.dapurku";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_RESEP = "RESEP";

    public final static class ResepEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_RESEP).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESEP;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESEP;

        public static final String TABLE_NAME = "dapurku";

        public static final String COL0_RESEP_ID = "resep_id";
        public static final String COL1_RESEP_GAMBAR = "resep_gambar";
        public static final String COL2_RESEP_NAMA = "resep_nama";
        public static final String COL3_RESEP_BAHAN = "resep_bahan";
        public static final String COL4_RESEP_CARA_MASAK = "resep_cara_masak";
        public static final String COL5_RESEP_FAVORITE = "resep_favorite";

        public static Uri buildResepUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }
}
