package e.com.scluasmobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class sclMenuBarang extends AppCompatActivity implements ListView.OnItemClickListener{
    //declare listview
    private ListView sclListViewBarang;
    //declare php read
    private static final String URL ="http://192.168.43.19/scluasmobile/readbarang.php";
    //declare String buat JSON
    private String JSON_STRING;
    //declare Button tambah
    Button sclbtnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //nyambung ke xml mana
        setContentView(R.layout.sclmenubarang);
        sclbtnTambah = (Button)findViewById(R.id.tambah);
        sclbtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuBarang.this,sclMenuBarangTambah.class);
                startActivity(pindah);
            }
        });

        sclListViewBarang = (ListView)findViewById(R.id.ListViewBarang);
        sclListViewBarang.setOnItemClickListener(this);
        getJSON();
    }
    //buat nampung data ke JSON Array
    private void showBarang(){
        JSONObject jsonObject = null;
        //HashMap<datakey,datavalue>objeckHashMap<datakey,datavalue>
        //maksudnya: <1,Senin>Hari<1,Senin>
        ArrayList<HashMap<String,String>>list = new ArrayList<HashMap<String, String>>();
        try{
            //                   ini yang tadi di declare diatas
            jsonObject = new JSONObject(JSON_STRING);
            //                          sesuaikan sama php
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i=0; i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                //String (baru) = jo.getString("dari php")
                String sclid_barang = jo.getString("id_barang");
                String sclnama_barang = jo.getString("nama_barang");
                String sclsatuan_barang = jo.getString("satuan_barang");
                String sclstok_barang = jo.getString("stok_barang");
                String sclharga_beli_barang = jo.getString("harga_beli_barang");
                String sclharga_jual_barang = jo.getString("harga_jual_barang");

                //ngepush coding atas
                HashMap<String,String>barang = new HashMap<>();
                barang.put("id_barang",sclid_barang);
                barang.put("nama_barang",sclnama_barang);
                barang.put("satuan_barang",sclsatuan_barang);
                barang.put("stok_barang",sclstok_barang);
                barang.put("harga_beli_barang",sclharga_beli_barang);
                barang.put("harga_jual_barang",sclharga_jual_barang);
                list.add(barang);
            }
            //ngebaca error kalau ada
        }catch (JSONException e){
            //ngeprint apa error nya
            e.printStackTrace();
        }
        //ngoper dari hashmap ke listview
        ListAdapter adapter = new SimpleAdapter(
                sclMenuBarang.this,list,R.layout.sclmenubarangtablerow,
                new String[]{"id_barang","nama_barang","satuan_barang","stok_barang","harga_beli_barang","harga_jual_barang"},
                new int[]{R.id.sclIDBarang,R.id.sclNamaBarang,R.id.sclSatuanBarang,R.id.sclStokBarang,R.id.sclHargaBeliBarang,R.id.sclHargaJual});
        sclListViewBarang.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){super.onPreExecute();}
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                JSON_STRING = s;
                showBarang();
            }
            @Override
            protected String doInBackground(Void... params){
                sclRequestHandler requestHandler = new sclRequestHandler();
                String a = requestHandler.sendGetRequest(URL);
                return a;
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    @Override
    public void onItemClick(AdapterView<?>parent,View view, int position, long id){
        Intent intent = new Intent(this,sclMenuBarangUpdate.class);
        HashMap<String,String>map = (HashMap)parent.getItemAtPosition(position);
        String sclid_barang2 = map.get("id_barang").toString();
        intent.putExtra("id_barang",sclid_barang2);
        startActivity(intent);
        finish();
    }
}
