package e.com.scluasmobile;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class sclMenuBarangTambah extends AppCompatActivity{
    Button sclSimpan;
    private static final String URL = "http://192.168.43.19/scluasmobile/insertbarang.php";
    //                          string baru            samain kayak php
    public static final String sclIDBarangTambah2 = "id_barang";
    public static final String sclNamaBarangTambah2 = "nama_barang";
    public static final String sclSatuanBarangTambah2 = "satuan_barang";
    public static final String sclStokBarangTambah2 = "stok_barang";
    public static final String sclHargaBeliBarangTambah2 = "harga_beli_barang";
    public static final String sclHargaJualTambah2 = "harga_jual_barang";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclmenubarangtambah);
        sclSimpan = (Button)findViewById(R.id.simpan);
        sclSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
                }
        });
    }
    public void SendData(){
        //              string baru                                      samain kayak xml
        final EditText sclIDBarangTambah3 = (EditText)findViewById(R.id.sclIDBarangTambah);
        final EditText sclNamaBarangTambah3 = (EditText)findViewById(R.id.sclNamaBarangTambah);
        final EditText sclSatuanBarangTambah3 = (EditText)findViewById(R.id.sclSatuanBarangTambah);
        final EditText sclStokBarangTambah3 = (EditText)findViewById(R.id.sclStokBarangTambah);
        final EditText sclHargaBeliBarangTambah3 = (EditText)findViewById(R.id.sclHargaBeliBarangTambah);
        final EditText sclHargaJualBarangTambah3 = (EditText)findViewById(R.id.sclHargaJualBarangTambah);

        final String id_barang = sclIDBarangTambah3.getText().toString().trim();
        final String nama_barang = sclNamaBarangTambah3.getText().toString().trim();
        final String satuan_barang = sclSatuanBarangTambah3.getText().toString().trim();
        final String stok_barang = sclStokBarangTambah3.getText().toString().trim();
        final String harga_beli_barang = sclHargaBeliBarangTambah3.getText().toString().trim();
        final String harga_jual_barang = sclHargaJualBarangTambah3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print(response);
                        Toast.makeText(sclMenuBarangTambah.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(sclMenuBarangTambah.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>params = new HashMap<String, String>();
                params.put(sclIDBarangTambah2,id_barang);
                params.put(sclNamaBarangTambah2,nama_barang);
                params.put(sclSatuanBarangTambah2,satuan_barang);
                params.put(sclStokBarangTambah2,stok_barang);
                params.put(sclHargaBeliBarangTambah2,harga_beli_barang);
                params.put(sclHargaJualTambah2,harga_jual_barang);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
