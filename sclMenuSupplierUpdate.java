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

public class sclMenuSupplierUpdate extends AppCompatActivity implements View.OnClickListener {
    private EditText sclIDSupplierUpdate2, sclNamaSupplierUpdate2, sclTelpSupplierUpdate2, sclAlamatSupplierUpdate2;
    private Button sclUpdate, sclHapus;
    private static final String sclURLread = "http://192.168.43.19/scluasmobile/readsupplierberdasarkankode.php?id_supplier=";
    private static final String sclURLupdate = "http://192.168.43.19/scluasmobile/updatesupplier.php";
    private static final String sclURLhapus = "http://192.168.43.19/scluasmobile/deletesupplier.php?id_supplier=";

    //declare string untuk nampung ID karyawan yang akan ditaruk di tampilan selanjutnya
    private String sclIDSupplierTampung;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenusupplierupdate);

        Intent intent = getIntent();
        sclIDSupplierTampung = intent.getStringExtra("idsupplier");

        sclIDSupplierUpdate2 = (EditText)findViewById(R.id.sclIDSupplierUpdate);
        sclNamaSupplierUpdate2 = (EditText)findViewById(R.id.sclNamaSupplierUpdate);
        sclTelpSupplierUpdate2 = (EditText)findViewById(R.id.sclTelpSupplierUpdate);
        sclAlamatSupplierUpdate2 = (EditText)findViewById(R.id.sclAlamatSupplierUpdate);

        sclUpdate = (Button)findViewById(R.id.update);
        sclHapus = (Button)findViewById(R.id.hapus);

        sclUpdate.setOnClickListener(this);
        sclHapus.setOnClickListener(this);

        getSupplier();
    }

    private void getSupplier(){
        class GetSupplier extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                showSupplier(s);
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLread,sclIDSupplierTampung);
                return s;
            }
        }
        GetSupplier getSupplier = new GetSupplier();
        getSupplier.execute();
    }

    //                      parameter yang isinya String. kalo isinya int ya tambah int
    private void showSupplier(String json){
        try{
            //json object untuk nangkap data dari array nya php
            JSONObject jsonObject = new JSONObject(json);
            //habis ditangkap di json object, ditaruk di jsonobject (kayak cursor)//      sesuaiin sama php
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            //ditaruk di json object (kayak cursor) tadi
            JSONObject c = jsonArray.getJSONObject(0);
            String sclIDSupplierUpdate3 = c.getString("id_supplier");
            String sclNamaSupplierUpdate3 = c.getString("nama_supplier");
            String sclTelpSupplierUpdate3 = c.getString("telp_supplier");
            String sclAlamatSupplierUpdate3 = c.getString("alamat_supplier");

            //yang tadi di declare di atas, diisi sama yang barusan cursor di atas ini
            sclIDSupplierUpdate2.setText(sclIDSupplierUpdate3);
            sclNamaSupplierUpdate2.setText(sclNamaSupplierUpdate3);
            sclTelpSupplierUpdate2.setText(sclTelpSupplierUpdate3);
            sclAlamatSupplierUpdate2.setText(sclAlamatSupplierUpdate3);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateSupplier(){
        final String sclIDSupplierUpdate4 = sclIDSupplierUpdate2.getText().toString().trim();
        final String sclNamaSupplierUpdate4 = sclNamaSupplierUpdate2.getText().toString().trim();
        final String sclTelpSupplierUpdate4 = sclTelpSupplierUpdate2.getText().toString().trim();
        final String sclAlamatSupplierUpdate4 = sclAlamatSupplierUpdate2.getText().toString().trim();

        class UpdateSupplier extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuSupplierUpdate.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params){
                HashMap<String,String>map = new HashMap<>();
                //ngambil  dari php        ke UpdateSupplier()
                map.put("id_supplier",sclIDSupplierUpdate4);
                map.put("nama_supplier",sclNamaSupplierUpdate4);
                map.put("telp_supplier",sclTelpSupplierUpdate4);
                map.put("alamat_supplier",sclAlamatSupplierUpdate4);

                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendPostRequest(sclURLupdate,map);
                return s;
            }
        }
        UpdateSupplier updateSupplier = new UpdateSupplier();
        updateSupplier.execute();
    }

    private void hapusSupplier(){
        class HapusSupplier extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(sclMenuSupplierUpdate.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String s = requestHandler.sendGetRequestParam(sclURLhapus,sclIDSupplierTampung);
                return s;
            }
        }
        HapusSupplier hapusSupplier = new HapusSupplier();
        hapusSupplier.execute();
    }

    private void KonfirmasiHapusSupplier(){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Apakah Sudah Yakin Ingin Menghapus?");
        ab.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusSupplier();
                        startActivity(new Intent(sclMenuSupplierUpdate.this,sclMenuSupplier.class));
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
            updateSupplier();
            startActivity(new Intent(sclMenuSupplierUpdate.this,sclMenuSupplier.class));
            finish();
        }
        if(v == sclHapus){
            KonfirmasiHapusSupplier();
        }
    }







}
