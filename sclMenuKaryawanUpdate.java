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

public class sclMenuKaryawanUpdate extends AppCompatActivity implements View.OnClickListener{
    private EditText sclIDKaryawanUpdate2, sclNamaKaryawanUpdate2, sclTelpKaryawanUpdate2, sclAlamatKaryawanUpdate2, sclPasswordKaryawanUpdate2;
    private Button sclUpdate, sclHapus;
    private static final String sclURLread = "http://192.168.43.19/scluasmobile/readkaryawanberdasarkankode.php?id_karyawan=";
    private static final String sclURLupdate = "http://192.168.43.19/scluasmobile/updatekaryawan.php";
    private static final String sclURLhapus = "http://192.168.43.19/scluasmobile/deletekaryawan.php?id_karyawan=";


    //declare string untuk nampung ID karyawan yang akan ditaruk di tampilan selanjutnya
    private String sclIDKaryawanTampung;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenukaryawanupdate);

        Intent intent = getIntent();
        sclIDKaryawanTampung = intent.getStringExtra("idkaryawan");

        sclIDKaryawanUpdate2 = (EditText)findViewById(R.id.sclIDKaryawanUpdate);
        sclIDKaryawanUpdate2.setEnabled(false);
        sclNamaKaryawanUpdate2 = (EditText)findViewById(R.id.sclNamaKaryawanUpdate);
        sclTelpKaryawanUpdate2 = (EditText)findViewById(R.id.sclTelpKaryawanUpdate);
        sclAlamatKaryawanUpdate2 = (EditText)findViewById(R.id.sclAlamatKaryawanUpdate);
        sclPasswordKaryawanUpdate2 = (EditText)findViewById(R.id.sclPasswordKaryawanUpdate);

        sclUpdate = (Button)findViewById(R.id.update);
        sclHapus = (Button)findViewById(R.id.hapus);

        sclUpdate.setOnClickListener(this);
        sclHapus.setOnClickListener(this);

        sclIDKaryawanUpdate2.setText(sclIDKaryawanTampung);

        getKaryawan();
    }

    private void getKaryawan(){
        class GetKaryawan extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                showKaryawan(s);
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLread,sclIDKaryawanTampung);
                return s;
            }
        }
        GetKaryawan getKaryawan = new GetKaryawan();
        getKaryawan.execute();
    }

    //                      parameter yang isinya String. kalo isinya int ya tambah int
    private void showKaryawan(String json){
        try {
            //json object untuk nangkap data dari array nya php
            JSONObject jsonObject = new JSONObject(json);
            //habis ditangkap di json object, ditaruk di jsonobject (kayak cursor)//      sesuaiin sama php
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            //ditaruk di json object (kayak cursor) tadi
            JSONObject c = jsonArray.getJSONObject(0);
            String sclIDKaryawanUpdate3 = c.getString("id_karyawan");
            String sclNamaKaryawanUpdate3 = c.getString("nama_karyawan");
            String sclTelpKaryawanUpdate3 = c.getString("telp_karyawan");
            String sclAlamatKaryawanUpdate3 = c.getString("alamat_karyawan");
            String sclPasswordKaryawanUpdate3 = c.getString("password_karyawan");

            //yang tadi di declare di atas, diisi sama yang barusan cursor di atas ini
            sclIDKaryawanUpdate2.setText(sclIDKaryawanUpdate3);
            sclNamaKaryawanUpdate2.setText(sclNamaKaryawanUpdate3);
            sclTelpKaryawanUpdate2.setText(sclTelpKaryawanUpdate3);
            sclAlamatKaryawanUpdate2.setText(sclAlamatKaryawanUpdate3);
            sclPasswordKaryawanUpdate2.setText(sclPasswordKaryawanUpdate3);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateKaryawan(){
        final String sclIDKaryawanUpdate4 = sclIDKaryawanUpdate2.getText().toString().trim();
        final String sclNamaKaryawanUpdate4 = sclNamaKaryawanUpdate2.getText().toString().trim();
        final String sclTelpKaryawanUpdate4 = sclTelpKaryawanUpdate2.getText().toString().trim();
        final String sclAlamatKaryawanUpdate4 = sclAlamatKaryawanUpdate2.getText().toString().trim();
        final String sclPasswordKaryawanUpdate4 = sclPasswordKaryawanUpdate2.getText().toString().trim();

        class UpdateKaryawan extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuKaryawanUpdate.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params){
                HashMap<String,String>map = new HashMap<>();
                //ngambil  dari php        ke UpdateKaryawan()
                map.put("id_karyawan",sclIDKaryawanUpdate4);
                map.put("nama_karyawan",sclNamaKaryawanUpdate4);
                map.put("telp_karyawan",sclTelpKaryawanUpdate4);
                map.put("alamat_karyawan",sclAlamatKaryawanUpdate4);
                map.put("password_karyawan",sclPasswordKaryawanUpdate4);

                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendPostRequest(sclURLupdate,map);
                return s;
            }
        }
        UpdateKaryawan updateKaryawan = new UpdateKaryawan();
        updateKaryawan.execute();
    }

    private void hapusKaryawan(){
        class HapusKaryawan extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuKaryawanUpdate.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLhapus,sclIDKaryawanTampung);
                return s;
            }
        }
        HapusKaryawan hapusKaryawan = new HapusKaryawan();
        hapusKaryawan.execute();
    }

    private void KonfirmasiHapusKaryawan(){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Apakah Sudah Yakin Ingin Menghapus?");
        ab.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        hapusKaryawan();
                        startActivity(new Intent(sclMenuKaryawanUpdate.this,sclMenuKaryawan.class));
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
            updateKaryawan();
            startActivity(new Intent(sclMenuKaryawanUpdate.this,sclMenuKaryawan.class));
            finish();
        }
        if (v == sclHapus){
            KonfirmasiHapusKaryawan();
        }
    }
}
