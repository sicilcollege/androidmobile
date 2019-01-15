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

public class sclMenuKaryawan extends AppCompatActivity implements ListView.OnItemClickListener {
    //declare listview
    private ListView sclListViewKaryawan;
    //declare php read
    private static final String URL ="http://192.168.43.19/scluasmobile/readkaryawan.php";
    //declare String buat JSON
    private String JSON_STRING;
    //declare Button tambah
    Button sclbtnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //nyambung ke xml mana
        setContentView(R.layout.sclmenukaryawan);
        sclbtnTambah = (Button)findViewById(R.id.tambah);
        sclbtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuKaryawan.this,sclMenuKaryawanTambah.class);
                startActivity(pindah);
            }
        });

        sclListViewKaryawan = (ListView)findViewById(R.id.ListViewKaryawan);
        sclListViewKaryawan.setOnItemClickListener(this);
        getJSON();
    }

    //buat nampung data ke JSON Array
    private void showKaryawan(){
        JSONObject jsonObject = null;
        //HashMap<datakey,datavalue>objeckHashMap<datakey,datavalue>
        //maksudnya: <1,Senin>Hari<1,Senin>
        ArrayList<HashMap<String,String>>list = new ArrayList<HashMap<String, String>>();
        try{
            //                          ini yang tadi di declare diatas
            jsonObject = new JSONObject(JSON_STRING);
            //                                              sesuaikan sama php
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i=0; i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                //String (baru) = jo.getString("dari php")
                String sclid_karyawan = jo.getString("id_karyawan");
                String sclnama_karyawan = jo.getString("nama_karyawan");
                String scltelp_karyawan = jo.getString("telp_karyawan");
                String sclalamat_karyawan = jo.getString("alamat_karyawan");
                String sclpassword_karyawan = jo.getString("password_karyawan");

                //ngepush coding atas
                HashMap<String,String>karyawan = new HashMap<>();
                karyawan.put("id_karyawan",sclid_karyawan);
                karyawan.put("nama_karyawan",sclnama_karyawan);
                karyawan.put("telp_karyawan",scltelp_karyawan);
                karyawan.put("alamat_karyawan",sclalamat_karyawan);
                karyawan.put("password_karyawan",sclpassword_karyawan);
                list.add(karyawan);
            }
            //ngebaca error kalau ada
        }catch (JSONException e){
            //ngeprint apa error nya
            e.printStackTrace();
        }
        //ngoper dari hashmap ke listview
        ListAdapter adapter = new SimpleAdapter(
                sclMenuKaryawan.this,list,R.layout.sclmenukaryawantablerow,
                new String[]{"id_karyawan","nama_karyawan","telp_karyawan","alamat_karyawan","password_karyawan"},
                new int[]{R.id.sclIDKaryawan,R.id.sclNamaKaryawan,R.id.sclTelpKaryawan,R.id.sclAlamatKaryawan,R.id.sclPasswordKaryawan});
        sclListViewKaryawan.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){super.onPreExecute();}
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                JSON_STRING = s;
                showKaryawan();
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
    public void onItemClick(AdapterView<?>parent, View view, int position, long id){
        Intent intent = new Intent(this,sclMenuKaryawanUpdate.class);
        HashMap<String,String>map = (HashMap)parent.getItemAtPosition(position);
        String sclid_karyawan2 = map.get("id_karyawan").toString();
        intent.putExtra("idkaryawan",sclid_karyawan2);
        startActivity(intent);
        finish();
    }


}
