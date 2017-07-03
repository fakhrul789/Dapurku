package it.app.dapurku.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscoverResponse {

    @SerializedName("resep_id")
    @Expose
    private int resepId;

    @SerializedName("resep_gambar")
    @Expose
    private String resepGambar;
    @SerializedName("resep_nama")
    @Expose
    private String resepNama;
    @SerializedName("resep_bahan")
    @Expose
    private String resepBahan;
    @SerializedName("resep_bahan_halus")
    @Expose
    private Object resepBahanHalus;
    @SerializedName("resep_cara_masak")
    @Expose
    private String resepCaraMasak;
    @SerializedName("resep_favorite")
    @Expose
    private String resepFavorite;

    /**
     *
     * @return
     * The resepId
     */
    public int getResepId() {
        return resepId;
    }

    /**
     *
     * @param resepId
     * The resep_id
     */
    public void setResepId(int resepId) {
        this.resepId = resepId;
    }

    /**
     *
     * @return
     * The resepGambar
     */
    public String getResepGambar() {
        return resepGambar;
    }

    /**
     *
     * @param resepGambar
     * The resep_gambar
     */
    public void setResepGambar(String resepGambar) {
        this.resepGambar = resepGambar;
    }

    /**
     *
     * @return
     * The resepNama
     */
    public String getResepNama() {
        return resepNama;
    }

    /**
     *
     * @param resepNama
     * The resep_nama
     */
    public void setResepNama(String resepNama) {
        this.resepNama = resepNama;
    }

    /**
     *
     * @return
     * The resepBahan
     */
    public String getResepBahan() {
        return resepBahan;
    }

    /**
     *
     * @param resepBahan
     * The resep_bahan
     */
    public void setResepBahan(String resepBahan) {
        this.resepBahan = resepBahan;
    }

    /**
     *
     * @return
     * The resepBahanHalus
     */
    public Object getResepBahanHalus() {
        return resepBahanHalus;
    }

    /**
     *
     * @param resepBahanHalus
     * The resep_bahan_halus
     */
    public void setResepBahanHalus(Object resepBahanHalus) {
        this.resepBahanHalus = resepBahanHalus;
    }

    /**
     *
     * @return
     * The resepCaraMasak
     */
    public String getResepCaraMasak() {
        return resepCaraMasak;
    }

    /**
     *
     * @param resepCaraMasak
     * The resep_cara_masak
     */
    public void setResepCaraMasak(String resepCaraMasak) {
        this.resepCaraMasak = resepCaraMasak;
    }

    /**
     *
     * @return
     * The resepFavorite
     */
    public String getResepFavorite() {
        return resepFavorite;
    }

    /**
     *
     * @param resepFavorite
     * The resep_favorite
     */
    public void setResepFavorite(String resepFavorite) {
        this.resepFavorite = resepFavorite;
    }
}