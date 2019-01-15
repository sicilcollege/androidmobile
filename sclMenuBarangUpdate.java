package e.com.scluasmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class sclMenuBarangUpdate extends AppCompatActivity implements View.OnClickListener {
    private EditText sclIDBarangUpdate2, sclNamaBarangUpdate2, sclSatuanBarangUpdate2, sclStokBarangUpdate2, sclHargaBeliBarangUpdate2, sclHargaJualBarangUpdate2;
    private Button sclUpdate, sclHapus;
    private static final String sclURLread = "http://192.168.43.19/scluasmobile/readbarangberdasarkankode.php?id_barang=";
    private static final String sclURLupdate = "http://192.168.43.19/scluasmobile/updatebarang.php";
    private static final String sclURLhapus = "http://192.168.43.19/scluasmobile/deletebarang.php?id_barang=";

    //declare string untuk nampung ID barang yang akan ditaruk di tampilan selanjutnya
    private String sclIDBarangTampung;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenubarangupdate);

        Intent intent = getIntent();
        //                                              samain sama variable sclMenuBarang.java paling bawah
        sclIDBarangTampung = intent.getStringExtra("id_barang");

        sclIDBarangUpdate2 = (EditText)findViewById(R.id.sclIDBarangUpdate);
        sclIDBarangUpdate2.setEnabled(false);
        sclNamaBarangUpdate2 = (EditText)findViewById(R.id.sclNamaBarangUpdate);
        sclSatuanBarangUpdate2 = (EditText)findViewById(R.id.sclSatuanBarangUpdate);
        sclStokBarangUpdate2 = (EditText)findViewById(R.id.sclStokBarangUpdate);
        sclHargaBeliBarangUpdate2 = (EditText)findViewById(R.id.sclHargaBeliBarangUpdate);
        sclHargaJualBarangUpdate2 = (EditText)findViewById(R.id.sclHargaJualBarangUpdate);

        sclUpdate = (Button)findViewById(R.id.update);
        sclHapus = (Button)findViewById(R.id.hapus);

        sclUpdate.setOnClickListener(this);
        sclHapus.setOnClickListener(this);

        sclIDBarangUpdate2.setText(sclIDBarangTampung);

        getBarang();
    }

    private void getBarang(){
        class GetBarang extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected  void onPostExecute(String s){
                super.onPostExecute(s);
                showBarang(s);
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLread,sclIDBarangTampung);
                return s;
            }
        }
        GetBarang getBarang = new GetBarang();
        getBarang.execute();
    }

    //                      parameter yang isinya String. kalo isinya int ya tambah int
    private void showBarang(String json){
        try{
            //json object untuk nangkap data dari array nya php
            JSONObject jsonObject = new JSONObject(json);
            //habis ditangkap di json object, ditaruk di jsonobject (kayak cursor)//      sesuaiin sama php
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            //ditaruk di json object (kayak cursor) tadi
            JSONObject c = jsonArray.getJSONObject(0);
            String sclIDBarangUpdate3 = c.getString("id_barang");
            String sclNamaBarangUpdate3 = c.getString("nama_barang");
            String sclSatuanBarangUpdate3 = c.getString("satuan_barang");
            String sclStokBarangUpdate3 = c.getString("stok_barang");
            String sclHargaBeliBarangUpdate3 = c.getString("harga_beli_barang");
            String sclHargaJualBarangUpdate3 = c.getString("harga_jual_barang");

                //yang tadi di declare di atas, diisi sama yang barusan cursor di atas ini
            sclIDBarangUpdate2.setText(sclIDBarangUpdate3);
            sclNamaBarangUpdate2.setText(sclNamaBarangUpdate3);
            sclSatuanBarangUpdate2.setText(sclSatuanBarangUpdate3);
            sclStokBarangUpdate2.setText(sclStokBarangUpdate3);
            sclHargaBeliBarangUpdate2.setText(sclHargaBeliBarangUpdate3);
            sclHargaJualBarangUpdate2.setText(sclHargaJualBarangUpdate3);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateBarang(){
        final String sclIDBarangUpdate4 = sclIDBarangUpdate2.getText().toString().trim();
        final String sclNamaBarangUpdate4 = sclNamaBarangUpdate2.getText().toString().trim();
        final String sclSatuanBarangUpdate4 = sclSatuanBarangUpdate2.getText().toString().trim();
        final String sclStokBarangUpdate4 = sclStokBarangUpdate2.getText().toString().trim();
        final String sclHargaBeliBarangUpdate4 = sclHargaBeliBarangUpdate2.getText().toString().trim();
        final String sclHargaJualBarangUpdate4 = sclHargaJualBarangUpdate2.getText().toString().trim();

        class UpdateBarang extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuBarangUpdate.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params){
                HashMap<String,String>map = new HashMap<>();
                //ngambil  dari php        ke UpdateBarang()
                map.put("id_barang",sclIDBarangUpdate4);
                map.put("nama_barang",sclNamaBarangUpdate4);
                map.put("satuan_barang",sclSatuanBarangUpdate4);
                map.put("stok_barang",sclStokBarangUpdate4);
                map.put("harga_beli_barang",sclHargaBeliBarangUpdate4);
                map.put("harga_jual_barang",sclHargaJualBarangUpdate4);

                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendPostRequest(sclURLupdate,map);
                return s;
            }
        }
        UpdateBarang updateBarang = new UpdateBarang();
        updateBarang.execute();
    }

    private void hapusBarang(){
        class HapusBarang extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuBarangUpdate.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLhapus,sclIDBarangTampung);
                return s;
            }
        }
        HapusBarang hapusBarang = new HapusBarang();
        hapusBarang.execute();
    }

    private void KonfirmasiHapusBarang(){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Apakah Sudah Yakin Ingin Menghapus?");
        ab.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        hapusBarang();
                        startActivity(new Intent(sclMenuBarangUpdate.this,sclMenuBarang.class));
                        finish();
                    }
                });
        ab.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = ab.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v){
        if(v == sclUpdate){
            updateBarang();
            startActivity(new Intent(sclMenuBarangUpdate.this,sclMenuBarang.class));
            finish();
        }
        if (v == sclHapus) {
            KonfirmasiHapusBarang();
        }
    }
}
