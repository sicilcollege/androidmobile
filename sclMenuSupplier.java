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

public class sclMenuSupplier extends AppCompatActivity implements ListView.OnItemClickListener {
    //declare listview
    private ListView sclListViewSupplier;
    //declare php read
    private static final String URL ="http://192.168.43.19/scluasmobile/readsupplier.php";
    //declare String buat JSON
    private String JSON_STRING;
    //declare Button tambah
    Button sclbtnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //nyambung ke xml mana
        setContentView(R.layout.sclmenusupplier);
        sclbtnTambah = (Button)findViewById(R.id.tambah);
        sclbtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(sclMenuSupplier.this,sclMenuSupplierTambah.class);
                startActivity(pindah);
            }
        });
        sclListViewSupplier = (ListView)findViewById(R.id.ListViewSupplier);
        sclListViewSupplier.setOnItemClickListener(this);
        getJSON();
    }

    //buat nampung data ke JSON Array
    private void showSupplier(){
        JSONObject jsonObject = null;
        //HashMap<datakey,datavalue>objeckHashMap<datakey,datavalue>
        //maksudnya: <1,Senin>Hari<1,Senin>
        ArrayList<HashMap<String,String>>list = new ArrayList<HashMap<String, String>>();
        try{
            //                          ini yang tadi di declare diatas
            jsonObject = new JSONObject(JSON_STRING);
            //                                    sesuaikan sama php
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i=0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                //String (baru) = jo.getString("dari php")
                String sclid_supplier = jo.getString("id_supplier");
                String sclnama_supplier = jo.getString("nama_supplier");
                String scltelp_supplier = jo.getString("telp_supplier");
                String sclalamat_supplier = jo.getString("alamat_supplier");

                //ngepush coding atas
                HashMap<String,String>supplier = new HashMap<>();
                supplier.put("id_supplier",sclid_supplier);
                supplier.put("nama_supplier",sclnama_supplier);
                supplier.put("telp_supplier",scltelp_supplier);
                supplier.put("alamat_supplier",sclalamat_supplier);
                list.add(supplier);
            }
            //ngebaca error kalau ada
        }catch (JSONException e){
            //ngeprint apa error nya
            e.printStackTrace();
        }
        //ngoper dari hashmap ke listview
        ListAdapter adapter = new SimpleAdapter(
                sclMenuSupplier.this,list,R.layout.sclmenusuppliertablerow,
                new String[]{"id_supplier","nama_supplier","telp_supplier","alamat_supplier"},
                new int[]{R.id.sclIDSupplier,R.id.sclNamaSupplier,R.id.sclTelpSupplier,R.id.sclAlamatSupplier});
        sclListViewSupplier.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute(){super.onPreExecute();}
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                JSON_STRING = s;
                showSupplier();
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
        Intent intent = new Intent(this,sclMenuSupplierUpdate.class);
        HashMap<String,String>map = (HashMap)parent.getItemAtPosition(position);
        String sclid_supplier2 = map.get("id_supplier").toString();
        intent.putExtra("idsupplier",sclid_supplier2);
        startActivity(intent);
        finish();
    }
}
